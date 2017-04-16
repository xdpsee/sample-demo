package sample.demo.netty.data.service.impl.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sample.demo.netty.data.domain.Position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRES_NEW)
public class LastPositionCacheTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private LastPositionCache lastPositionCache;

    @Test
    public void testCache() {
        try {
            Position position = lastPositionCache.get(1L);
            assertEquals(null, position);

            position = Position.create("test-unique-id");
            position.setId(1024L);
            lastPositionCache.put(1L, position);

            position = lastPositionCache.get(1L);
            assertNotNull(position);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            lastPositionCache.remove(1L);
        }
    }
}
