package sample.demo.netty.data.service.impl.decoder;

import org.junit.Test;
import sample.demo.netty.protocol.mobile.decoder.MobileProtocolDecoder;
import sample.demo.netty.utils.Parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MobileProtocolDecodeTest {

    @Test
    public void testRegistryDecode() {

        /**
         * ##1,{protocol_version},{uuid},{app_version},{gmt_time}#
         */
        String message = "##1,1.0,888888888888888,1.0,20170318060354#";
        Parser parser = new Parser(MobileProtocolDecoder.PATTERN_LOGIN, message);
        assertTrue(parser.matches());

        assertEquals(1, parser.nextInt());
        assertEquals(1.0f, parser.nextDouble(), 0);
        assertEquals("888888888888888", parser.next());
        assertEquals(1.0f, parser.nextDouble(), 0);
        assertEquals("20170318060354", parser.next());

    }

    @Test
    public void testPositionDecode() {

        /**
         * ##2,{protocol_version},{uuid},{N2240.8887E11359.2994},{alt},{speed},{gmt_time}#
         */

        String message = "##2,1.0,888888888888888,N2240.888E11359.2994,326.8,21.6,20170318060354#";
        Parser parser = new Parser(MobileProtocolDecoder.PATTERN_POSITION, message);
        assertTrue(parser.matches());

        assertEquals(2, parser.nextInt());
        assertEquals(1.0f, parser.nextDouble(), 0);
        assertEquals("888888888888888", parser.next());

        double latitude = parser.nextCoordinate(Parser.CoordinateFormat.HEM_DEG_MIN);
        double longitude = parser.nextCoordinate(Parser.CoordinateFormat.HEM_DEG_MIN);
        System.out.println(String.format("%f,%f", latitude, longitude));

        assertEquals(22.681467, latitude, 0.000001);
        assertEquals(113.988323, longitude, 0.000001);

        double altitude = parser.nextDouble();
        double speed = parser.nextDouble();

        String timeStr = parser.next();

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = dateFormat.parse(timeStr);

            System.out.println(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
