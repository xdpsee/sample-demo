package sample.demo.netty.protocol.mobile.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import sample.demo.netty.core.Message;
import sample.demo.netty.core.decoder.AbstractProtocolDecoder;
import sample.demo.netty.protocol.mobile.message.RegistryMessage;
import sample.demo.netty.utils.Parser;
import sample.demo.netty.utils.PatternBuilder;

import java.net.SocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class MobileProtocolDecoder extends AbstractProtocolDecoder {


    public static final Pattern PATTERN_LOGIN = new PatternBuilder()
            .text("##")
            .number("(d{1}),")
            .number("(d+.d+),")
            .expression("(\\d{15}),")
            .number("(d+.d+),")
            .expression("(\\d{14})#").compile();

    public static final Pattern PATTERN_POSITION = new PatternBuilder()
            .text("##")
            .number("(d{1}),")
            .number("(d+.d+),")                 // protocol_version
            .expression("([0-9A-Za-z]{15}),")   // IMEI
            .expression("([NS])")
            .number("(dd)(dd.d+)")               // latitude
            .expression("([WE])")
            .number("(ddd)(dd.d+),")             // longitude
            .number("(d+.d+),")                  // altitude
            .number("(d+.d+),")                  // speed
            .expression("(\\d{14})#").compile();

    private static final ThreadLocal<DateFormat> DATE_FORMAT = ThreadLocal.withInitial(()->{
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return format;
    });

    /**
     * ##1,{protocol_version},{uuid},{app_version},{gmt_time}#
     * ##2,{protocol_version},{uuid},{N2240.8887E11359.2994},{alt},{speed},{gmt_time}#
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

    private Message handle(String message) {

        if (message.startsWith("##1")) {
            Parser parser = new Parser(PATTERN_LOGIN, message);
            if (parser.matches()) {
                parser.nextInt(); // skip cmd
                return new RegistryMessage(parser.nextDouble()
                        , parser.next()
                        , parser.nextDouble()
                        , parser.next()
                        , message);
            }
        }

        if (message.startsWith("##2")) {
            Parser parser = new Parser(PATTERN_LOGIN, message);
            if (parser.matches()) {
                parser.nextInt(); // skip cmd



            }
        }

        if (message.startsWith("##3")) {

        }

        return null;
    }


}


