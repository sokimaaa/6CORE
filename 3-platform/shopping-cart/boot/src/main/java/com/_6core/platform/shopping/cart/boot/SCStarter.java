package com._6core.platform.shopping.cart.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com._6core.platform.shopping.cart")
public class SCStarter {
  public static void main(String[] args) {
    SpringApplication.run(SCStarter.class, args);
  }
}
