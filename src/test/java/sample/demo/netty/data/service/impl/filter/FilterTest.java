package sample.demo.netty.data.service.impl.filter;


import org.junit.Test;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.utils.GMT;

public class FilterTest {

    @Test
    public void testFilter() {

        Position lastPos = Position.create("test-device-1");
        Position currPos = Position.create("test-device-1");

        lastPos.setDeviceId(1L);
        lastPos.setLocated(true);
        lastPos.setLatitude(22.00000);
        lastPos.setLongitude(113.00000);
        lastPos.setTime(GMT.date(2017,1,2,1,1,20));
        lastPos.setSpeed(21.0);

        currPos.setDeviceId(1L);
        currPos.setLocated(true);
        currPos.setLatitude(22.00000);
        currPos.setLongitude(113.00000);
        currPos.setTime(GMT.date(2017,1,2,1,1,20));
        currPos.setSpeed(21.0);





    }

}
