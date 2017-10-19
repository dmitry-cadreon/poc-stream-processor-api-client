package com.example.api.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

@Slf4j
@SpringBootApplication
@EnableBinding(Processor.class)
public class Application {
    @Autowired
    private CampaignService campaignService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
