package sample.demo.netty.data.service.impl.mybtatis.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sample.demo.netty.data.domain.Network;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.domain.support.PositionIndex;
import sample.demo.netty.utils.GMT;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRES_NEW)
public class PositionMapperTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private PositionMapper positionMapper;

    @Test
    public void testInsert() throws Exception {

        Position position = new Position();
        position.setId(1L);
        position.setGmtCreate(new Date());
        position.setGmtModified(new Date());
        position.setDeviceId(1L);
        position.setLocated(true);
        position.setLatitude(0);
        position.setLongitude(0);
        position.setTime(GMT.date(2017,1, 1, 1, 0, 0));
        position.setNetwork(new Network());
        int ret = positionMapper.insert(position);

        assertEquals(1, ret);

        Position pr = positionMapper.select(new PositionIndex(1L, position.getTime(), 1L));
        assertNotNull(pr);

    }


}