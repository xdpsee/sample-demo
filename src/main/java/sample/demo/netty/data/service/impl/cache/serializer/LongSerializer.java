package sample.demo.netty.data.service.impl.cache.serializer;

import com.google.common.primitives.Longs;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class LongSerializer implements RedisSerializer<Long>{

    @Override
    public byte[] serialize(Long data) throws SerializationException {
        return Longs.toByteArray(data);
    }

    @Override
    public Long deserialize(byte[] bytes) throws SerializationException {
        try {
            return Longs.fromByteArray(bytes);
        } catch (IllegalArgumentException e) {
            throw new SerializationException("deserialize error", e);
        }
    }
}
