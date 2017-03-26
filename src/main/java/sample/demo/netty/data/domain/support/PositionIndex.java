package sample.demo.netty.data.domain.support;

import lombok.Data;

import java.util.Date;

@Data
public class PositionIndex {

    private long deviceId;

    private long positionId;

    private Date deviceTime;

}
