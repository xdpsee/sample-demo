package sample.demo.netty.data.service.impl.mybtatis.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sample.demo.netty.data.domain.support.PositionIndex;
import sample.demo.netty.utils.GMT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRES_NEW)
public class PositionIndexMapperTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private PositionIndexMapper positionIndexMapper;

    @Test
    public void testInsert() {


        PositionIndex index = new PositionIndex(1L, GMT.date(2017,01,01,0,0,0));

        int ret = positionIndexMapper.insertIndex(1L, index);
        assertEquals(1, ret);

        PositionIndex result = positionIndexMapper.selectIndex(1L, 1L);
        assertNotNull(result);
    }

    @Test
    public void testCount() {

        PositionIndex index = new PositionIndex(1L, GMT.date(2017,01,01,0,0,0));

        int ret = positionIndexMapper.insertIndex(1L, index);
        assertEquals(1, ret);


        int count = positionIndexMapper.countIndices(1L
                , GMT.date(2017,01,01,0,0,0)
                , GMT.date(2017,01,01,0,0,0));

        assertEquals(1, count);
    }


}