package sample.demo.netty.data.service.impl.mybtatis.mapper;

import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.domain.support.PositionIndex;

import java.util.List;

public interface PositionMapper {

    int insert(Position position);

    Position select(PositionIndex index);

    List<Position> batchSelect(List<PositionIndex> indices);

}



