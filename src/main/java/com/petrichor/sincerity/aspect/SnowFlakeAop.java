package com.petrichor.sincerity.aspect;

import com.petrichor.sincerity.util.SnowflakeIdWorker;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
* 在标记了SnowFlake注解的方法下，给目标插入雪花id
* */
@Aspect
@Component
public class SnowFlakeAop {

    SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1);

    @Pointcut(value = "@annotation(com.petrichor.sincerity.annotation.SnowFlakeId)")
    public void pointCut() {

    }


    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws NoSuchFieldException, IllegalAccessException {
        Object[] args = joinPoint.getArgs();
        Object entity = args[0];
        Class<?> clazz = entity.getClass();
        Field field = clazz.getDeclaredField("id");
        field.setAccessible(true);
        field.set(entity, snowflakeIdWorker.nextId());
    }
}
