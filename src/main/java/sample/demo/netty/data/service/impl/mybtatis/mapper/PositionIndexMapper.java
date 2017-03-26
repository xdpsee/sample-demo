package sample.demo.netty.data.service.impl.mybtatis.mapper;

import org.apache.ibatis.annotations.Param;
import sample.demo.netty.data.domain.support.PositionIndex;

import java.util.Date;
import java.util.List;

public interface PositionIndexMapper {

    int insertIndex(PositionIndex index);

    PositionIndex selectIndex(@Param("deviceId") long deviceId
            , @Param("positionId") long positionId);

    int countIndices(@Param("deviceId") long deviceId
            , @Param("startDate") Date startDate
            , @Param("endDate") Date endDate);

    List<PositionIndex> batchIndices(@Param("deviceId") long deviceId
            , @Param("startDate") Date startDate
            , @Param("limit") int limit);

}


