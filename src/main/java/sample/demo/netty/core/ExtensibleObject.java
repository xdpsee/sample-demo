package sample.demo.netty.core;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@Data
public abstract class ExtensibleObject implements Serializable {

    private static final long serialNumberUID = -1L;

    private Map<String, Object> extras = new HashMap<>();

    public boolean hasKey(String key) {
        return extras.containsKey(key);
    }

    public String getString(String key) {
        if (extras.containsKey(key)) {
            return (String) extras.get(key);
        } else {
            return null;
        }
    }

    public double getDouble(String key) {
        if (extras.containsKey(key)) {
            return ((Number) extras.get(key)).doubleValue();
        } else {
            return 0.0;
        }
    }

    public boolean getBoolean(String key) {
        if (extras.containsKey(key)) {
            return Boolean.parseBoolean(extras.get(key).toString());
        } else {
            return false;
        }
    }

    public int getInteger(String key) {
        if (extras.containsKey(key)) {
            return ((Number) extras.get(key)).intValue();
        } else {
            return 0;
        }
    }

    public long getLong(String key) {
        if (extras.containsKey(key)) {
            return ((Number) extras.get(key)).longValue();
        } else {
            return 0;
        }
    }

    public void set(String key, boolean value) {
        extras.put(key, value);
    }

    public void set(String key, int value) {
        extras.put(key, value);
    }

    public void set(String key, long value) {
        extras.put(key, value);
    }

    public void set(String key, double value) {
        extras.put(key, value);
    }

    public void set(String key, String value) {
        extras.put(key, value);
    }

}

