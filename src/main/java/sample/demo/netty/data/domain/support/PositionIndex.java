package sample.demo.netty.data.domain.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionIndex {

    private long deviceId;

    private Date time;

    private long positionId;

}
