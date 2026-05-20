package com.moneyminder.depense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DepenseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepenseApplication.class, args);
    }

}
