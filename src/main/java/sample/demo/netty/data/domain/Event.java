package sample.demo.netty.data.domain;

import lombok.Getter;
import lombok.Setter;
import sample.demo.netty.core.ExtensibleObject;
import sample.demo.netty.core.Message;

import java.util.Date;

@SuppressWarnings("unused")
public class Event extends ExtensibleObject implements Message {

    @Getter @Setter
    private Long id;
    @Getter @Setter
    private EventType type;
    @Getter @Setter
    private Long deviceId;
    @Getter @Setter
    private Long positionId;
    @Getter @Setter
    private Date time;

    public Event(EventType type, Long deviceId, Long positionId) {
        this.type = type;
        this.positionId = positionId;
        this.time = new Date();
        this.deviceId = deviceId;
    }

    public Event(EventType type, long deviceId) {
        this.type = type;
        this.time = new Date();
        this.deviceId = deviceId;
    }

    public Event() {

    }
}
