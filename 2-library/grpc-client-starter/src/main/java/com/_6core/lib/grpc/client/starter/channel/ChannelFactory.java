package com._6core.lib.grpc.client.starter.channel;

import com._6core.lib.grpc.client.starter.holder.GrpcClientHolder;
import io.grpc.ManagedChannel;

public class ChannelFactory {
    public ManagedChannel newNettyChannel(final GrpcClientHolder grpcClientHolder) {
        final var channelBuilder = new ChannelBuilder(grpcClientHolder);
        return channelBuilder.buildNettyChannel();
    }
}
