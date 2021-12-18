package com.bobvu.tinhong;

import com.bobvu.tinhong.cassandra.config.DataStaxAstraProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class TinhongApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinhongApplication.class, args);
    }

}
