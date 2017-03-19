package sample.demo.netty.protocol.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import sample.demo.netty.core.AbstractProtocolDecoder;
import sample.demo.netty.data.domain.Position;

import java.net.SocketAddress;

public class TestProtocolDecoder extends AbstractProtocolDecoder {

    /**
     * ##1,{protocol_version},{uuid},{app_version},{gmt_time}#
     * ##2,{protocol_version},{sequence},{uuid},{lat},{lng},{alt},{speed},{gmt_time}#
     * ##3,{protocol_version},{uuid},{gmt_time}#
     */
    @Override
    public Object decode(Channel channel, SocketAddress remoteAddress, Object msg) {

        if (msg instanceof ByteBuf) {

            byte[] bytes = new byte[((ByteBuf) msg).readableBytes()];
            ((ByteBuf) msg).getBytes(0, bytes);
            String message = new String(bytes);

            return handle(message);
        }

        return null;
    }


    private Position handle(String message) {

        if (message.startsWith("##1")) {

        }

        if (message.startsWith("##2")) {

        }

        if (message.startsWith("##3")) {

        }

        return null;
    }


}


