package sample.demo.netty.core.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public abstract class AbstractHandler<T> extends SimpleChannelInboundHandler<T> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {

        messageReceived(ctx, msg);

    }

    protected abstract void messageReceived(ChannelHandlerContext ctx, T msg) throws Exception;

}
