package sample.demo.netty.data.service.impl.mybtatis.mapper;

import org.apache.ibatis.annotations.Param;
import sample.demo.netty.core.CommandType;
import sample.demo.netty.data.domain.Model;

import java.util.List;

public interface ModelMapper {

    int insert(Model model);

    Model select(@Param("protocol") String protocol, @Param("model") String model);

    List<Model> selectAll(@Param("protocol")String protocol);

    List<CommandType> selectSupportedCommands(@Param("protocol") String protocol, @Param("model") String model);
}
