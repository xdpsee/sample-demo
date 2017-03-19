package sample.demo.netty.core;

import java.util.Collection;

public interface Protocol {

    String name();

    Collection<CommandType> getSupportedCommands();

    void sendCommand(Connection connection, Command command);

    void initTrackerServer(ServerManager serverManager);

}

