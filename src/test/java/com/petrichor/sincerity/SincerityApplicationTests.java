package com.petrichor.sincerity;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class SincerityApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(this.getClass().getResource("").getPath());
    }

    @Test
    void file() throws IOException {
        File file = new File("1.txt");
        FileInputStream in = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder S = new StringBuilder();
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            S.append(s).append("\r\n");
        }
        System.out.println(S);
//        bufferedReader.readLine()
//        System.out.println(bufferedReader.toString());
        for (int i = 0; i < 100; i++) {


//            FileOutputStream out = new FileOutputStream(file);
//            OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
//            writer.append(s).append("测试\r\n");
//            writer.close();
//            out.close();
        }
    }
}
