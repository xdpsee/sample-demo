package sample.demo.netty.core;

import io.netty.channel.*;
import sample.demo.netty.core.event.*;
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

        server.addCustomHandlers(channel.pipeline());

        channel.pipeline().addLast("dataHandler"
                , AutowireUtils.autowire(new DefaultDataHandler()));

        channel.pipeline().addLast("AlertEventHandler"
                , AutowireUtils.autowire(new AlertEventHandler()));
        channel.pipeline().addLast("IgnitionEventHandler"
                , AutowireUtils.autowire(new IgnitionEventHandler()));
        channel.pipeline().addLast("MotionEventHandler"
                , AutowireUtils.autowire(new MotionEventHandler()));
        channel.pipeline().addLast("OverspeedEventHandler"
                , AutowireUtils.autowire(new OverspeedEventHandler()));
        channel.pipeline().addLast("MaintenanceEventHandler"
                , AutowireUtils.autowire(new MaintenanceEventHandler()));
        channel.pipeline().addLast("CommandResultEventHandler"
                , AutowireUtils.autowire(new CommandResultEventHandler()));
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
