package sample.demo.netty.core;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public abstract class TrackerServer {

    private static final Logger logger = LoggerFactory.getLogger(TrackerServer.class);

    @Getter
    private final String protocol;
    @Getter
    private final boolean connectionless;
    @Setter
    private String address;
    @Setter
    private Integer port;
    @Getter
    private ChannelGroup channelGroup;

    private ServerBootstrap bootstrap;


    public TrackerServer(ServerManager serverManager, String protocol, boolean connectionless) {
        this.protocol = protocol;
        this.connectionless = connectionless;
        this.channelGroup = new DefaultChannelGroup(serverManager.getExecutor());

        bootstrap = new ServerBootstrap();
        bootstrap.group(serverManager.getBossGroup(), serverManager.getWorkerGroup())
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, false);
        if (connectionless) {
            bootstrap.childHandler(new PipelineFactory<DatagramChannel>(this));
        } else {
            bootstrap.childHandler(new PipelineFactory<SocketChannel>(this));
        }
    }

    public abstract void initPipeline(ChannelPipeline pipeline);

    void start() {
        InetSocketAddress endpoint;
        if (address == null) {
            endpoint = new InetSocketAddress(port);
        } else {
            endpoint = new InetSocketAddress(address, port);
        }

        bootstrap.bind(endpoint)
                .addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                channelGroup.add(future.channel());
                logger.info(String.format("%s server start at port %d okay.", protocol, port));
            } else {
                logger.error(String.format("%s server start at port %d failed.", protocol, port));
                future.channel().close();
            }
        });
    }

    void stop() {
        ChannelGroupFuture future = channelGroup.close();
        future.awaitUninterruptibly();
    }

}
