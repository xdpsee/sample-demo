package sample.demo.netty.core;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import sample.demo.netty.data.service.DeviceService;

import javax.xml.bind.DatatypeConverter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractProtocol implements Protocol {

    private final String name;

    @Autowired
    private DeviceService deviceService;

    protected AbstractProtocol(String name) {
        this.name = name;
    }

    @Override
    public final String name() {
        return this.name;
    }

    @Override
    public Collection<CommandType> getSupportedCommands() {
        Set<CommandType> commands = new HashSet<>();

        commands.add(CommandType.TYPE_CUSTOM);

        return commands;
    }

    @Override
    public void sendCommand(Connection connection, Command command) {
        Set<CommandType> supportedCommands = deviceService.getSupportedCommands(connection.getDeviceId());
        if (supportedCommands.contains(command.getType())) {
            connection.write(command);
        } else if (command.getType().equals(CommandType.TYPE_CUSTOM)) {
            String data = command.getString(Command.KEY_DATA);
            if (connection.getChannel().pipeline().get(StringEncoder.class) != null) {
                connection.write(data);
            } else {
                connection.write(Unpooled.wrappedBuffer(DatatypeConverter.parseHexBinary(data)));
            }
        } else {
            throw new RuntimeException("Command " + command.getType() + " is not supported in protocol " + name());
        }
    }
}
