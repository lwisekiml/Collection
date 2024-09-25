package com.example.testproject.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptTest {

    @Test
    void encryptTest() {
        String id = "root";
        String password = "1234";

        System.out.println(jasyptEncoding(id));
        System.out.println(jasyptEncoding(password));
    }

    private String jasyptEncoding(String value) {
        String key = "testproject"; // stringEncryptor() 에 있는 password 값과 같은 값으로 사용
        StandardPBEStringEncryptor pbe = new StandardPBEStringEncryptor();
        pbe.setAlgorithm("PBEWithMD5AndDES");
        pbe.setPassword(key);
        return pbe.encrypt(value);
    }
}
