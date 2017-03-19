package sample.demo.netty.core;

import io.netty.channel.Channel;
import lombok.Getter;

import java.net.SocketAddress;

public class Connection extends ExtensibleObject {

    @Getter
    private final long deviceId;
    @Getter
    private final Protocol protocol;
    @Getter
    private final SocketAddress remoteAddress;
    @Getter
    private final Channel channel;

    public Connection(long deviceId, Protocol protocol, Channel channel, SocketAddress remoteAddress) {
        this.deviceId = deviceId;
        this.protocol = protocol;
        this.channel = channel;
        this.remoteAddress = remoteAddress;
    }

    public void sendCommand(Command command) {
        protocol.sendCommand(this, command);
    }

    public void write(Object message) {
        channel.writeAndFlush(message);
    }
}
