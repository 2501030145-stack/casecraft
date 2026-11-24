package com.casecraft;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
@SpringBootApplication @EnableAsync
public class CaseCraftApplication { public static void main(String[] args) { SpringApplication.run(CaseCraftApplication.class, args); } }
