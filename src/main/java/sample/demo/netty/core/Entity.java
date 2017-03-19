package sample.demo.netty.core;

import lombok.Data;

import java.util.Date;

@Data
public class Entity extends ExtensibleObject {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

}
