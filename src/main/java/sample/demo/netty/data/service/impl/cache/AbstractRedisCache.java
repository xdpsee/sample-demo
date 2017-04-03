package sample.demo.netty.data.service.impl.cache;


import com.google.common.primitives.Bytes;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public abstract class AbstractRedisCache<K,V> implements InitializingBean {

    @Autowired
    private RedisTemplate redisTemplate;
    private RedisSerializer<K> keySerializer;
    private RedisSerializer<V> valueSerializer;

    public abstract RedisSerializer<K> keySerializer();
    public abstract RedisSerializer<V> valueSerializer();

    public V get(K key) {
        if (null == key) {
            throw new IllegalArgumentException("null == key");
        }

        byte[] rawKey = genKey(key);
        byte[] rawValue = (byte[]) redisTemplate.opsForValue().get(rawKey);
        if (rawValue != null) {
            return valueSerializer.deserialize(rawValue);
        }

        return null;
    }
    
    public void put(K key, V value) {
        if (null == key || null == value) {
            throw new IllegalArgumentException("null == key || null == value");
        }

        byte[] rawKey = genKey(key);
        byte[] rawValue = valueSerializer.serialize(value);

        redisTemplate.opsForValue().set(rawKey, rawValue);
    }

    public void remove(K key) {
        if (null == key) {
            throw new IllegalArgumentException("null == key");
        }

        byte[] rawKey = genKey(key);
        redisTemplate.opsForValue().getOperations().delete(rawKey);

    }

    private byte[] genKey(K key) {

        byte[] suffix = keySerializer.serialize(key);

        return Bytes.concat(this.getClass().getName().getBytes()
                , new byte[]{':'}
                , suffix);
    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        keySerializer = keySerializer();
        valueSerializer = valueSerializer();
        if (null == keySerializer || null == valueSerializer) {
            throw new BeanInitializationException("null == keySerializer || null == valueSerializer");
        }
    }
}
