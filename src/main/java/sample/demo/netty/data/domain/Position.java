package sample.demo.netty.data.domain;

import lombok.Getter;
import lombok.Setter;
import sample.demo.netty.core.ExtensibleObject;
import sample.demo.netty.core.Message;

import java.util.Date;

@SuppressWarnings("unused")
public class Position extends ExtensibleObject implements Message {

    public static final String KEY_ORIGINAL = "raw";
    public static final String KEY_INDEX = "index";
    public static final String KEY_HDOP = "hdop";
    public static final String KEY_SATELLITES = "sat";
    public static final String KEY_RSSI = "rssi";
    public static final String KEY_GPS = "gps";
    public static final String KEY_EVENT = "event";
    public static final String KEY_ALARM = "alarm";
    public static final String KEY_STATUS = "status";
    public static final String KEY_ODOMETER = "odometer"; // meters
    public static final String KEY_TRIP_ODOMETER = "trip-odometer";
    public static final String KEY_HOURS = "hours";
    public static final String KEY_INPUT = "input";
    public static final String KEY_OUTPUT = "output";
    public static final String KEY_POWER = "power";
    public static final String KEY_BATTERY = "battery";
    public static final String KEY_FUEL = "fuel";
    public static final String KEY_FUEL_CONSUMPTION = "fuel-consumption";
    public static final String KEY_RFID = "rfid";
    public static final String KEY_VERSION = "version";
    public static final String KEY_TYPE = "type";
    public static final String KEY_IGNITION = "ignition";
    public static final String KEY_FLAGS = "flags";
    public static final String KEY_CHARGE = "charge";
    public static final String KEY_IP = "ip";
    public static final String KEY_ARCHIVE = "archive";
    public static final String KEY_DISTANCE = "distance"; // meters
    public static final String KEY_TOTAL_DISTANCE = "total-distance";
    public static final String KEY_RPM = "rpm";
    public static final String KEY_VIN = "vin";
    public static final String KEY_APPROXIMATE = "approximate";
    public static final String KEY_THROTTLE = "throttle";
    public static final String KEY_MOTION = "motion";
    public static final String KEY_ARMED = "armed";
    public static final String KEY_ACCURACY = "accuracy";
    public static final String KEY_GEOFENCE = "geofence";

    public static final String KEY_DTCS = "dtcs";
    public static final String KEY_OBD_SPEED = "obd-speed";
    public static final String KEY_OBD_ODOMETER = "obd-odometer";

    public static final String KEY_RESULT = "result";

    public static final String ALARM_GENERAL = "general";
    public static final String ALARM_SOS = "sos";
    public static final String ALARM_VIBRATION = "vibration";
    public static final String ALARM_MOVEMENT = "movement";
    public static final String ALARM_LOW_SPEED = "low-speed";
    public static final String ALARM_OVERSPEED = "over-speed";
    public static final String ALARM_FALL_DOWN = "fall-down";
    public static final String ALARM_LOW_POWER = "low-power";
    public static final String ALARM_LOW_BATTERY = "low-battery";
    public static final String ALARM_FAULT = "fault";
    public static final String ALARM_POWER_OFF = "power-fff";
    public static final String ALARM_POWER_ON = "power-on";
    public static final String ALARM_DOOR = "door";
    public static final String ALARM_GEOFENCE = "geofence";
    public static final String ALARM_GEOFENCE_ENTER = "geofence-enter";
    public static final String ALARM_GEOFENCE_EXIT = "geofence-exit";
    public static final String ALARM_GPS_ANTENNA_CUT = "gps-antenna-cut";
    public static final String ALARM_ACCIDENT = "accident";
    public static final String ALARM_TOW = "tow";
    public static final String ALARM_ACCELETATION = "hard-acceleration";
    public static final String ALARM_BREAKING = "hard-breaking";
    public static final String ALARM_FATIGUE_DRIVING = "fatigue-driving";
    public static final String ALARM_POWER_CUT = "power-cut";
    public static final String ALARM_JAMMING = "jamming";
    public static final String ALARM_TEMPERATURE = "temperature";
    public static final String ALARM_PARKING = "parking";
    public static final String ALARM_SHOCK = "shock";
    public static final String ALARM_BONNET = "bonnet";
    public static final String ALARM_FOOT_BRAKE = "foot-brake";
    public static final String ALARM_OIL_LEAK = "oil-leak";
    public static final String ALARM_TAMPERING = "tampering";

    // Starts with 1 not 0
    public static final String PREFIX_TEMP = "temp";
    public static final String PREFIX_ADC = "adc";
    public static final String PREFIX_IO = "io";
    public static final String PREFIX_COUNT = "count";


    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String protocol;
    @Getter @Setter
    private Long deviceId;
    @Getter @Setter
    private boolean outdated;
    @Getter @Setter
    private boolean valid;
    @Getter @Setter
    private double latitude;
    @Getter @Setter
    private double longitude;
    @Getter @Setter
    private double altitude;
    @Getter @Setter
    private double speed; // value in knots
    @Getter @Setter
    private double course;
    @Getter @Setter
    private String address;
    @Getter @Setter
    private double accuracy;
    @Getter @Setter
    private Network network;

    private Date serverTime;
    private Date deviceTime;
    private Date fixTime;

    public Date getServerTime() {
        if (serverTime != null) {
            return new Date(serverTime.getTime());
        } else {
            return null;
        }
    }

    public void setServerTime(Date serverTime) {
        if (serverTime != null) {
            this.serverTime = new Date(serverTime.getTime());
        } else {
            this.serverTime = null;
        }
    }

    public Date getDeviceTime() {
        if (deviceTime != null) {
            return new Date(deviceTime.getTime());
        } else {
            return null;
        }
    }

    public void setDeviceTime(Date deviceTime) {
        if (deviceTime != null) {
            this.deviceTime = new Date(deviceTime.getTime());
        } else {
            this.deviceTime = null;
        }
    }

    public Date getFixTime() {
        if (fixTime != null) {
            return new Date(fixTime.getTime());
        } else {
            return null;
        }
    }

    public void setFixTime(Date fixTime) {
        if (fixTime != null) {
            this.fixTime = new Date(fixTime.getTime());
        } else {
            this.fixTime = null;
        }
    }

    public void setTime(Date time) {
        setDeviceTime(time);
        setFixTime(time);
    }

}
