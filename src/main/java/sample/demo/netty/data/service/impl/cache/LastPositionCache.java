package sample.demo.netty.data.service.impl.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.service.impl.cache.serializer.JsonSerializer;
import sample.demo.netty.data.service.impl.cache.serializer.LongSerializer;

@Component
public class LastPositionCache extends AbstractRedisCache<Long,Position> {
    @Override
    public RedisSerializer<Long> keySerializer() {
        return new LongSerializer();
    }

    @Override
    public RedisSerializer<Position> valueSerializer() {
        return new JsonSerializer<>(new TypeReference<Position>(){});
    }
}


