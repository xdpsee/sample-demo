package sample.demo.netty.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import sample.demo.netty.data.domain.Position;

public abstract class AbstractDataHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Position) {
            Position result = handlePosition((Position) msg);
            if (result != null) {
                ctx.fireChannelRead(result);
            } else {
                ctx.fireChannelRead(msg);
            }
        }
    }

    protected abstract Position handlePosition(Position position);
}
