package sample.demo.netty.data.service.impl.mybtatis.mapper;

import org.apache.ibatis.annotations.Param;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.domain.support.PositionIndex;

import java.util.List;

public interface PositionMapper {

    int insert(Position position);

    Position select(@Param("deviceId") long deviceId, @Param("index") PositionIndex index);

    List<Position> batchSelect(@Param("deviceId") long deviceId
            , @Param("indices") List<PositionIndex> indices);

}



