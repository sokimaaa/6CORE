package com._6core.platform.order.orderboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.r2dbc.R2dbcDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.r2dbc.R2dbcRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
        R2dbcAutoConfiguration.class,
        R2dbcDataAutoConfiguration.class,
        R2dbcRepositoriesAutoConfiguration.class,
        R2dbcTransactionManagerAutoConfiguration.class
})
@ComponentScan("com._6core.platform.order")
public class OrderServiceStarter {

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceStarter.class, args);
  }
}
