package sample.demo.netty.data.service.impl.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.demo.netty.data.domain.Device;

@Component
public class DeviceCache {

    @Autowired
    private DeviceByIdCache deviceByIdCache;
    @Autowired
    private DeviceByUniqueCache deviceByUniqueCache;

    public Device get(long deviceId) {
        return deviceByIdCache.get(deviceId);
    }

    public Device get(String uniqueId) {
        return deviceByUniqueCache.get(uniqueId);
    }

    public void set(long deviceId, Device device) {
        deviceByIdCache.put(deviceId, device);
        deviceByUniqueCache.put(device.getUniqueId(), device);
    }

    public void set(String uniqueId, Device device) {
        deviceByUniqueCache.put(uniqueId, device);
        deviceByIdCache.put(device.getId(), device);
    }

    @Component
    public static class DeviceByIdCache extends AbstractRedisCache<Long, Device> {

    }

    @Component
    public static class DeviceByUniqueCache extends AbstractRedisCache<String, Device> {

    }

}
