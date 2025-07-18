package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.example.service.ZooSimulationService;

@SpringBootApplication(scanBasePackages = {"org.example"})
public class Main implements CommandLineRunner {

    @Autowired
    private ZooSimulationService zooSimulationService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        zooSimulationService.execute();
    }
}