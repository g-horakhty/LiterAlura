package com.literalura.literalura;

import com.literalura.literalura.service.MainService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
    private final MainService mainService;
    
    public LiteraluraApplication(MainService mainService) {
        this.mainService = mainService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainService.showMenu();
    }
}