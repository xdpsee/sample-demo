package sample.demo.netty.data.service.impl.cache.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import sample.demo.netty.utils.JSONUtils;

import java.io.UnsupportedEncodingException;

public class JsonSerializer<T> implements RedisSerializer<T> {

    private Class<T> clazz = null;
    private TypeReference<T> typeRef = null;

    public JsonSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public JsonSerializer(TypeReference<T> typeRef) {
        this.typeRef = typeRef;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        try {
            return JSONUtils.toJsonString(t).getBytes("UTF-8");
        } catch (NullPointerException|UnsupportedEncodingException e) {
            throw new SerializationException("serialize error", e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {

        try {
            String value = new String(bytes, "UTF-8");

            if (typeRef != null) {
                return JSONUtils.fromJsonString(value, typeRef);
            } else {
                return JSONUtils.fromJsonString(value, clazz);
            }

        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("deserialize error", e);
        }
    }
}
