package com.petrichor.sincerity.util;

import org.springframework.stereotype.Component;

@Component
public class SnowflakeIdWorker {

    private final long workerIdBits = 10;

    /**
     * 合并了机器ID和数据标示ID，统称业务ID，10位
     */
    private long workerId = 7;

    /**
     * 毫秒内序列，12位，2^12 = 4096个数字
     */
    private long sequence = 0L;

    /**
     * 上一次生成的ID的时间戳，同一个worker中
     */
    private long lastTimestamp = -1L;

    public SnowflakeIdWorker() {
    }

    public SnowflakeIdWorker(long workerId) {
        // 2^10 - 1 = 1023
        long maxWorkerId = ~(-1L << workerIdBits);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("WorkerId必须大于或等于0且小于或等于%d", maxWorkerId));
        }

        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long ts = System.currentTimeMillis();
        if (ts < lastTimestamp) {
            throw new RuntimeException(String.format("系统时钟回退了%d毫秒", (lastTimestamp - ts)));
        }

        // 同一时间内，则计算序列号
        long sequenceBits = 12;
        if (ts == lastTimestamp) {
            // 序列号溢出
            // 2^12 - 1 = 4095
            long maxSequence = ~(-1L << sequenceBits);
            if (++sequence > maxSequence) {
                ts = tilNextMillis(lastTimestamp);
                sequence = 0L;
            }
        } else {
            // 时间戳改变，重置序列号
            sequence = 0L;
        }

        lastTimestamp = ts;

        // 0 - 00000000 00000000 00000000 00000000 00000000 0 - 00000000 00 - 00000000 0000
        // 左移后，低位补0，进行按位或运算相当于二进制拼接
        // 本来高位还有个0<<63，0与任何数字按位或都是本身，所以写不写效果一样
        // 时间戳左移22位
        long timestampLeftOffset = workerIdBits + sequenceBits;
        // 开始时间：2020-01-01 00:00:00
        long beginTs = 1577808000000L;
        // 业务ID左移12位
        return (ts - beginTs) << timestampLeftOffset | workerId << sequenceBits | sequence;
    }

    /**
     * 阻塞到下一个毫秒
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {
        long ts = System.currentTimeMillis();
        while (ts <= lastTimestamp) {
            ts = System.currentTimeMillis();
        }
        return ts;
    }
}
