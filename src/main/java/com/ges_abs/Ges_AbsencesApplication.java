package com.ges_abs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Ges_AbsencesApplication {
    public static void main(String[] args) {
        SpringApplication.run(Ges_AbsencesApplication.class, args);
    }

}
