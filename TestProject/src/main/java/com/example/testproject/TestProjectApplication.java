package com.example.testproject;

import com.example.testproject.config.ProfileManager;
import com.example.testproject.config.env.EnvConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestProjectApplication {

    private final Logger LOGGER = LoggerFactory.getLogger(TestProjectApplication.class);

    @Autowired
    public TestProjectApplication(EnvConfiguration envConfiguration, ProfileManager profileManager) {
        LOGGER.info(envConfiguration.getMessage());
        profileManager.printActiveProfiles();
    }

    public static void main(String[] args) {
        SpringApplication.run(TestProjectApplication.class, args);
    }

}
