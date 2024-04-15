package com._6core.lib.grpc.client.starter.config;

import com._6core.lib.grpc.client.starter.channel.ChannelFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class GrpcClientAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ChannelFactory channelBuilderFactory() {
        return new ChannelFactory();
    }
}
