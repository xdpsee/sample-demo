package sample.demo.netty.core.handler;

import io.netty.channel.ChannelHandlerContext;
import sample.demo.netty.data.domain.Position;

public abstract class AbstractDataHandler extends AbstractHandler<Position> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Position position) throws Exception {
        Position result = handlePosition(position);
        if (result != null) {
            ctx.fireChannelRead(result);
        }
    }

    protected abstract Position handlePosition(Position position);
}
