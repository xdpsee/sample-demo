package sample.demo.netty.protocol.test.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.Arrays;
import java.util.List;

public class TestFrameDecoder extends ByteToMessageDecoder {

    private static final byte[] MAGIC_CHUNK= new byte[] {'#', '#'};

    /**
     * ##1,{protocol_version},{uuid},{app_version},{gmt_time}#
     * ##2,{protocol_version},{sequence},{uuid},{lat},{lng},{alt},{speed},{gmt_time}#
     * ##3,{protocol_version},{uuid},{gmt_time}#
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        final int bytes = byteBuf.readableBytes();
        if (!byteBuf.isReadable() || bytes < 4) {
            return;
        }

        final int start = byteBuf.readerIndex();
        byteBuf.markReaderIndex();

        byte[] magic = new byte[2];
        byteBuf.getBytes(start, magic);// 读取2字节

        if (!Arrays.equals(magic, MAGIC_CHUNK)) {
            byteBuf.skipBytes(1);
            return;
        }

        final byte cat = byteBuf.getByte(start + MAGIC_CHUNK.length);
        if (cat != '1' && cat != '2' && cat != '3') {
            byteBuf.skipBytes(1);
            return;
        }

        int curr = start + MAGIC_CHUNK.length + 1;
        byte currByte;
        do {
            currByte = byteBuf.getByte(curr++);
        } while (byteBuf.isReadable() && currByte != '#');

        if (currByte == '#') {
            final byte[] body = new byte[curr - start];
            byteBuf.getBytes(start, body);
            byteBuf.skipBytes(body.length);

            out.add(Unpooled.wrappedBuffer(body));
        }
    }
}


