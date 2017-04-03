package sample.demo.netty.data.service.impl.cache;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public abstract class AbstractRedisCache<K,V> implements InitializingBean {

    @Autowired
    private RedisTemplate<String,V> redisTemplate;

    public V get(K key) {
        if (null == key) {
            throw new IllegalArgumentException("null == key");
        }

        String rawKey = genKey(key);
        return redisTemplate.opsForValue().get(rawKey);
    }

    public void put(K key, V value) {
        if (null == key || null == value) {
            throw new IllegalArgumentException("null == key || null == value");
        }

        String rawKey = genKey(key);
        redisTemplate.opsForValue().set(rawKey, value);
    }

    private String genKey(K key) {

        String clazzName = this.getClass().getName();
        System.out.println(clazzName);

        return String.format("%s-%s", clazzName, key.toString());
    }

    @Override
    public final void afterPropertiesSet() throws Exception {

    }
}
