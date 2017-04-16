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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

        Position position = Position.create("test-unique-id");
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

        Long positionId = position.getId();

        Position pr = positionMapper.select(1L, new PositionIndex(positionId, position.getTime()));
        assertNotNull(pr);

    }

    @Test
    public void testBatchSelect() {
        Position position = Position.create("test-unique-id");
        position.setGmtCreate(new Date());
        position.setGmtModified(new Date());
        position.setDeviceId(1L);
        position.setLocated(true);
        position.setLatitude(0);
        position.setLongitude(0);
        position.setTime(GMT.date(2017,1, 1, 1, 0, 0));
        position.setNetwork(new Network());
        assertEquals(1, positionMapper.insert(position));

        Long posId1 = position.getId();

        position.setGmtCreate(new Date());
        position.setGmtModified(new Date());
        position.setDeviceId(1L);
        position.setLocated(true);
        position.setLatitude(0);
        position.setLongitude(0);
        position.setTime(GMT.date(2017,2, 1, 1, 0, 0));
        position.setNetwork(new Network());
        assertEquals(1, positionMapper.insert(position));

        Long posId2 = position.getId();

        List<Position> positions = positionMapper.batchSelect(1L
                , Arrays.asList(new PositionIndex(posId1, GMT.date(2017,1, 1, 1, 0, 0))
                        , new PositionIndex(posId2, GMT.date(2017,2, 1, 1, 0, 0))));
        assertEquals(2, positions.size());
    }

}