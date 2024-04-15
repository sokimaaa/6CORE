package com._6core.lib.grpc.client.starter.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Bean
@ConfigurationProperties
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GrpcProperties {
    @AliasFor(annotation = ConfigurationProperties.class, attribute = "prefix")
    String property();
}
