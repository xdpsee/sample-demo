package sample.demo.netty.data.domain;

import java.security.InvalidParameterException;
import java.util.Arrays;

public enum  Category {

    ELECTRIC_BICYCLE(1, "电动自行车"),
    MOTOR(1, "摩托"),
    CAR(1, "小汽车"),
    BUS(1, "公交"),
    TRUCK(1, "货车"),
    YACHT(1, "游艇"),
    PERSONAL(1, "个人"),
    PET(1, "宠物"),
    ;

    public final int code;
    public final String comment;
    Category(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    public static Category valueOf(int code) {
        Category[] categories = values();
        return Arrays.stream(categories)
                .filter(type->type.code == code)
                .findAny()
                .orElseThrow(()->new InvalidParameterException(String.format("invalid code : %d", code)));

    }
}
