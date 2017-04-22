package sample.demo.netty.data.service.impl;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.demo.netty.core.CommandType;
import sample.demo.netty.data.domain.Device;
import sample.demo.netty.data.service.DeviceService;
import sample.demo.netty.data.service.exception.DeviceNotFoundException;
import sample.demo.netty.data.service.impl.cache.DeviceCache;
import sample.demo.netty.data.service.impl.mybtatis.mapper.DeviceMapper;
import sample.demo.netty.data.service.impl.mybtatis.mapper.ModelMapper;

import java.util.Set;

@Component
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private DeviceMapper deviceMapper;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ModelMapper modelMapper;
    @Autowired
    private DeviceCache deviceCache;

    @Override
    public Device getDeviceById(long id) {
        return loadDevice(id);
    }

    @Override
    public Device getDeviceByUnique(String uniqueId) {
        return loadDevice(uniqueId);
    }

    @Override
    public Set<CommandType> getSupportedCommands(long deviceId) {
        Device device = deviceMapper.selectById(deviceId);
        if (null == device) {
            throw new RuntimeException(new DeviceNotFoundException("deviceId: " + deviceId));
        }

        return Sets.newHashSet(modelMapper.selectSupportedCommands(device.getProtocol(), device.getModel()));
    }

    @Override
    public Long lastPosition(long deviceId) {

        Device device = getDeviceById(deviceId);
        if (device != null) {
            return device.getLong(Device.KEY_LAST_POSITION);
        }

        return null;
    }



    private Device loadDevice(Object identify) {

        assert identify instanceof Long || identify instanceof String : "error type";

        Device device = (identify instanceof String)
                ? deviceCache.get((String) identify) : deviceCache.get((Long)identify);
        if (null != device) {
            return device;
        }

        if (identify instanceof String) {
            device = deviceMapper.selectByUnique((String) identify);
            if (device != null) {
                deviceCache.set((String)identify, device);
            }
        } else {
            device = deviceMapper.selectById(((Long)identify));
            if (device != null) {
                deviceCache.set((Long) identify, device);
            }
        }

        return  device;
    }

}


