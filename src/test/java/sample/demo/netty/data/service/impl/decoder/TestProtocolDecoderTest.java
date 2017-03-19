package sample.demo.netty.data.service.impl.decoder;

import org.junit.Test;
import sample.demo.netty.utils.Parser;
import sample.demo.netty.utils.PatternBuilder;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestProtocolDecoderTest {

    @Test
    public void testLoginDecoder() {

        /**
         * ##1,{protocol_version},{uuid},{app_version},{gmt_time}#
         */
        String message = "##1,1.0,123456789123456,1.0,20170318060354#";

        Pattern pattern = new PatternBuilder().text("##")
                .number("(d{1}),")
                .number("(d+.d+),")
                .expression("(\\d{15}),")
                .number("(d+.d+),")
                .expression("(\\d{14})#").compile();

        Parser parser = new Parser(pattern, message);
        assertTrue(parser.matches());

        assertEquals(1, parser.nextInt());
        assertEquals(1.0f, parser.nextDouble(), 0);
        assertEquals("123456789123456", parser.next());
        assertEquals(1.0f, parser.nextDouble(), 0);
        assertEquals("20170318060354", parser.next());


    }


}
