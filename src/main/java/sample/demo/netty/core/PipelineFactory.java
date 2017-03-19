package sample.demo.netty.core;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import sample.demo.netty.utils.AutowireUtils;

import java.nio.channels.DatagramChannel;

public final class PipelineFactory<C extends Channel> extends ChannelInitializer<C>{

    private final TrackerServer server;

    public PipelineFactory(TrackerServer server) {
        this.server = server;
    }

    @Override
    protected void initChannel(C channel) throws Exception {

        channel.pipeline().addLast("channel-open"
                , AutowireUtils.autowire(new OpenChannelHandler(server)));

        server.initPipeline(channel.pipeline());

    }

    private class OpenChannelHandler extends ChannelInboundHandlerAdapter {
        private final TrackerServer server;
        OpenChannelHandler(TrackerServer server) {
            this.server = server;
        }
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            if (!(ctx.channel() instanceof DatagramChannel)) {
                this.server.getChannelGroup().add(ctx.channel());
            }
        }
    }
}
