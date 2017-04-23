package sample.demo.netty.data.service.impl.mybtatis.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sample.demo.netty.data.domain.Category;
import sample.demo.netty.data.domain.Device;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/application-context.xml")
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRES_NEW)
public class DeviceMapperTest extends AbstractTransactionalJUnit4SpringContextTests {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private DeviceMapper deviceMapper;

    @Test(expected = MyBatisSystemException.class)
    public void testInsert() {

        Device device = new Device();
        device.setCategory(Category.CAR);
        device.setProtocol("mobile");
        device.setModel("TEST-1");
        device.setName("test");
        device.setPhone("13402022080");
        device.setUniqueId("123456789123456");
        device.setGmtCreate(new Date());
        device.setGmtModified(new Date());

        deviceMapper.insert(device);
        deviceMapper.insert(device);
    }

    @Test
    public void testSelect() {
        Device device = new Device();
        device.setCategory(Category.CAR);
        device.setProtocol("mobile");
        device.setModel("TEST-1");
        device.setName("test");
        device.setPhone("13402022080");
        device.setUniqueId("123456789123456");
        device.setGmtCreate(new Date());
        device.setGmtModified(new Date());

        deviceMapper.insert(device);

        device = deviceMapper.selectById(device.getId());
        assertNotNull(device);

        device = deviceMapper.selectByUnique(device.getUniqueId());
        assertNotNull(device);
    }


}
