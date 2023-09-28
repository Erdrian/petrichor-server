package com.petrichor.sincerity;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

@SpringBootTest
class SincerityApplicationTests {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    void contextLoads() throws NullPointerException {
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


    //    spring.datasource.main.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
//    spring.datasource.main.url=jdbc:sqlserver://122.227.131.170:11433;DatabaseName=WorkerMSDb;
//    spring.datasource.main.username=sa
//    spring.datasource.main.password=2202@@@My
    @Test
    void t() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://122.227.131.170:11433;DatabaseName=WorkerMSDb;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "2202@@@My";

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println(connection);
    }

    @Test
    void t1() {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate);
//        restTemplate
    }
}
