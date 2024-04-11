package com._6core.platform.order.orderboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com._6core.platform.order")
public class OrderServiceStarter {

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceStarter.class, args);
  }
}
