package sample.demo.netty.core.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractHandler<T> extends SimpleChannelInboundHandler<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {

        messageReceived(ctx, msg);

    }

    protected abstract void messageReceived(ChannelHandlerContext ctx, T msg) throws Exception;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        try {
            ctx.close();
        } finally {
            logger.error("", cause);
        }
    }
}

