package sample.demo.netty.data.service;

import sample.demo.netty.core.CommandType;
import sample.demo.netty.data.domain.Device;

import java.util.Set;

public interface DeviceService {

    Device getDeviceById(long id);

    Device getDeviceByUnique(String uniqueId) throws Exception;

    Set<CommandType> getSupportedCommands(long deviceId);

    Long lastPosition(long deviceId);

    void updateLastPosition(long deviceId, long positionId);
}

