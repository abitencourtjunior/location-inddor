package br.com.location.indoor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LocationIndoorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationIndoorApplication.class, args);
    }

}
