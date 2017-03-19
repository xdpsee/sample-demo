package sample.demo.netty.core;

import lombok.Data;

@SuppressWarnings("unused")
@Data
public class Command extends ExtensibleObject implements Message {

    private CommandType type;

    private long deviceId;

    public static final String KEY_UNIQUE_ID = "unique-id";
    public static final String KEY_FREQUENCY = "frequency";
    public static final String KEY_TIMEZONE = "timezone";
    public static final String KEY_DEVICE_PASSWORD = "device-password";
    public static final String KEY_RADIUS = "radius";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_ENABLE = "enable";
    public static final String KEY_DATA = "data";
    public static final String KEY_INDEX = "index";
    public static final String KEY_PHONE = "phone";

}
