package com.jf.api;

import java.io.IOException;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.jf.api")
public class ApiApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
