package sample.demo.netty.data.service.impl;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.demo.netty.core.CommandType;
import sample.demo.netty.data.domain.Device;
import sample.demo.netty.data.service.DeviceService;
import sample.demo.netty.data.service.exception.DeviceNotFoundException;
import sample.demo.netty.data.service.impl.mybtatis.mapper.DeviceMapper;
import sample.demo.netty.data.service.impl.mybtatis.mapper.ModelMapper;

import java.util.Set;

@Component
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Device getDeviceById(long id) {
        return deviceMapper.selectById(id);
    }

    @Override
    public Device getDeviceByUnique(String uniqueId) {
        return deviceMapper.selectByUnique(uniqueId);
    }

    @Override
    public Set<CommandType> getSupportedCommands(long deviceId) {
        Device device = deviceMapper.selectById(deviceId);
        if (null == device) {
            throw new RuntimeException(new DeviceNotFoundException("deviceId: " + deviceId));
        }

        return Sets.newHashSet(modelMapper.selectSupportedCommands(device.getProtocol(), device.getModel()));
    }
}
