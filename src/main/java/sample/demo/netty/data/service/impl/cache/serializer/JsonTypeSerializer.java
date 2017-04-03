package sample.demo.netty.data.service.impl.cache.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import sample.demo.netty.utils.JSONUtils;

import java.io.UnsupportedEncodingException;

public class JsonTypeSerializer<T> implements RedisSerializer<T>{

    @Override
    public byte[] serialize(T t) throws SerializationException {

        try {
            String str = JSONUtils.toJsonString(t);
            return str.getBytes("UTF-8");
        } catch (NullPointerException|UnsupportedEncodingException e) {
            throw new SerializationException("serialize error", e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {

        try {
            String str = new String(bytes, "UTF-8");
            return JSONUtils.fromJsonString(str, new TypeReference<T>(){});
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("deserialize error", e);
        }
    }
}
