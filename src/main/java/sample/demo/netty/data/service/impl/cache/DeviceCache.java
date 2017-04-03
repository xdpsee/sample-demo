package sample.demo.netty.data.service.impl.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import sample.demo.netty.data.domain.Device;
import sample.demo.netty.data.service.impl.cache.serializer.JsonSerializer;
import sample.demo.netty.data.service.impl.cache.serializer.LongSerializer;

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

        @Override
        public RedisSerializer<Long> keySerializer() {
            return new LongSerializer();
        }

        @Override
        public RedisSerializer<Device> valueSerializer() {
            return new JsonSerializer<>(new TypeReference<Device>(){});

        }
    }

    @Component
    public static class DeviceByUniqueCache extends AbstractRedisCache<String, Device> {
        @Override
        public RedisSerializer<String> keySerializer() {
            return new StringRedisSerializer();
        }

        @Override
        public RedisSerializer<Device> valueSerializer() {
            return new JsonSerializer<>(new TypeReference<Device>(){});
        }
    }

}
