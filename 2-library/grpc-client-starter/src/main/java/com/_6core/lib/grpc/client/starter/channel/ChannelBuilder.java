package com._6core.lib.grpc.client.starter.channel;

import com._6core.lib.grpc.client.starter.holder.GrpcClientHolder;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ChannelBuilder {
    private final GrpcClientHolder grpcClientHolder;

    ManagedChannel buildNettyChannel() {
        log.debug("Building Managed Channel: {}", grpcClientHolder);

        final var host = Optional.ofNullable(grpcClientHolder.getHost()).orElseThrow(
                () -> new BuildChannelException("The host field is required to set.")
        );
        final var port = Optional.ofNullable(grpcClientHolder.getPort()).orElseThrow(
                () -> new BuildChannelException("The port field is required to set.")
        );
        final var connectionTimeout = Optional.ofNullable(grpcClientHolder.getConnectionTimeout()).orElseThrow(
                () -> new BuildChannelException("The connectionTimeout field is required to set.")
        );
        final var deadline = Optional.ofNullable(grpcClientHolder.getDeadline()).orElseThrow(
                () -> new BuildChannelException("The deadline field is required to set.")
        );
        final var idleTimeout = Optional.ofNullable(grpcClientHolder.getIdleTimeout()).orElseThrow(
                () -> new BuildChannelException("The deadline field is required to set.")
        );

        return NettyChannelBuilder.forAddress(host, port)
                .withOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
                .idleTimeout(idleTimeout, TimeUnit.MILLISECONDS)
                .usePlaintext()
                .build();
    }

    private static class BuildChannelException extends RuntimeException {
        BuildChannelException(String message) {
            super(message);
        }
    }
}
