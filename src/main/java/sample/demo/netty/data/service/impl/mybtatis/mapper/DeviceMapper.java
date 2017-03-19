package sample.demo.netty.data.service.impl.mybtatis.mapper;

import org.apache.ibatis.annotations.Param;
import sample.demo.netty.data.domain.Device;

public interface DeviceMapper {

    int insert(Device device);

    Device selectById(@Param("id") long deviceId);

    Device selectByUnique(@Param("uniqueId") String uniqueId);

}
