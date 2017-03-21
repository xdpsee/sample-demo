package sample.demo.netty.data.service.impl.mybtatis.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sample.demo.netty.core.CommandType;
import sample.demo.netty.data.domain.Model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRES_NEW)
public class ModelMapperTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testInsert() {

        Model model = new Model();
        model.setGmtCreate(new Date());
        model.setGmtModified(new Date());
        model.setProtocol("mobile");
        model.setModel("TEST-1");
        model.setSupportedCommands(Arrays.asList(CommandType.TYPE_ALARM_CLOCK));

        modelMapper.insert(model);

        modelMapper.insert(model);

    }

    @Test
    public void testSelectXXX() {

        Model model = new Model();
        model.setGmtCreate(new Date());
        model.setGmtModified(new Date());
        model.setProtocol("mobile");
        model.setModel("TEST-1");
        model.setSupportedCommands(Arrays.asList(CommandType.TYPE_ALARM_CLOCK));

        modelMapper.insert(model);

        model = modelMapper.select("mobile", "TEST-1");
        assertNotNull(model);

        List<Model> models = modelMapper.selectAll("mobile");
        assertEquals(1, models.size());

    }

}