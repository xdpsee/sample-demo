package sample.demo.netty.core;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SuppressWarnings("unused")
public final class Configs {

    private ImmutableMap<String, String> properties;

    public static Configs INSTANCE;

    public static void load() throws IOException {
        INSTANCE = new Configs();
    }

    private Configs() throws IOException {
        Properties prop = new Properties();

        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("conf/configs.xml");

            prop.loadFromXML(inputStream);

            this.properties = Maps.fromProperties(prop);
        } finally {

        }
    }

    public boolean hasKey(String key) {
        return properties.containsKey(key);
    }

    public String getString(String key) {
        return properties.get(key);
    }

    public String getString(String key, String defaultValue) {
        if (hasKey(key)) {
            return getString(key);
        } else {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(getString(key));
    }

    public int getInteger(String key) {
        return getInteger(key, 0);
    }

    public int getInteger(String key, int defaultValue) {
        if (hasKey(key)) {
            return Integer.parseInt(getString(key));
        } else {
            return defaultValue;
        }
    }

    public long getLong(String key) {
        return getLong(key, 0);
    }

    public long getLong(String key, long defaultValue) {
        if (hasKey(key)) {
            return Long.parseLong(getString(key));
        } else {
            return defaultValue;
        }
    }

    public double getDouble(String key) {
        return getDouble(key, 0.0);
    }

    public double getDouble(String key, double defaultValue) {
        if (hasKey(key)) {
            return Double.parseDouble(getString(key));
        } else {
            return defaultValue;
        }
    }
}
