package com._6core.platform.warehouse.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com._6core.platform")
public class WarehouseServiceStarter {
  public static void main(String[] args) {
    SpringApplication.run(WarehouseServiceStarter.class, args);
  }
}
