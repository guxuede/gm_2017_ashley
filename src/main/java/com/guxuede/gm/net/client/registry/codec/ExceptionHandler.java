package com.guxuede.gm.net.client.registry.codec;

// Source - https://stackoverflow.com/a/31073723
// Posted by Ben Evans, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-22, License - CC BY-SA 4.0

import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

@Slf4j
public class ExceptionHandler extends ChannelDuplexHandler {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Uncaught exceptions from inbound handlers will propagate up to this handler
    }

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
        ctx.connect(remoteAddress, localAddress, promise.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (!future.isSuccess()) {
                    // Handle connect exception here...
                    Throwable failureCause = future.cause();
                    log.error("connect error", failureCause);
                }
            }
        }));
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        ctx.write(msg, promise.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (!future.isSuccess()) {
                    // Handle write exception here...
                    Throwable failureCause = future.cause();
                    log.error("write error", failureCause);
                }
            }
        }));
    }

    // ... override more outbound methods to handle their exceptions as well
}
