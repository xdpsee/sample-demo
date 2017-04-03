package sample.demo.netty.data.domain;

import lombok.Data;
import sample.demo.netty.core.Entity;

@Data
public class Device extends Entity {

    public static final String KEY_LAST_POSITION = "lastPos";


    private String uniqueId; // IMEI

    private Category category;

    private String protocol;

    private String model;

    private String name;

    private String phone;

    private String contacts;



}
