package sample.demo.netty.data.domain;

import lombok.Data;
import sample.demo.netty.core.CommandType;
import sample.demo.netty.core.Entity;

import java.util.ArrayList;
import java.util.List;

@Data
public class Model extends Entity {

    private String protocol;

    private String model;

    private List<CommandType> supportedCommands = new ArrayList<>();
}


