package sample.demo.netty.data.service.impl.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AbstractEhCache<K extends Serializable,V extends Serializable> {

    private final String cacheName;

    protected AbstractEhCache(String name) {
        this.cacheName = name;
    }

    @Autowired
    private CacheManager cacheManager;

    public V get(K key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (null != cache) {
            Element element = cache.get(key);
            if (element != null) {
                return (V) element.getObjectValue();
            }

            return null;
        }

        throw new RuntimeException(String.format("cacheName: %s", cacheName));
    }

    public void put(K key, V value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (null != cache) {
           cache.put(new Element(key, value));
        } else {
            throw new RuntimeException(String.format("cacheName: %s", cacheName));
        }
    }
}
