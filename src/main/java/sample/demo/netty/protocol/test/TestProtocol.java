package sample.demo.netty.protocol.test;

import io.netty.channel.ChannelPipeline;
import sample.demo.netty.core.*;
import sample.demo.netty.utils.AutowireUtils;

import java.util.Collection;

@SuppressWarnings("unused")
public class TestProtocol extends AbstractProtocol {

    public TestProtocol() {
        super("test");
    }

    @Override
    public Collection<CommandType> getSupportedCommands() {

        Collection<CommandType> types = super.getSupportedCommands();
        types.add(CommandType.TYPE_MODE_POWER_SAVING);

        return types;
    }

    @Override
    public void initTrackerServer(ServerManager serverManager) {

        TrackerServer server = new TrackerServer(serverManager, name(), false) {
            @Override
            public void addCustomHandlers(ChannelPipeline pipeline) {

                pipeline.addLast("frameDecoder", AutowireUtils.autowire(new TestFrameDecoder()));
                pipeline.addLast("objectDecoder", AutowireUtils.autowire(new TestProtocolDecoder()));

            }
        };
        server.setPort(Configs.INSTANCE.getInteger(name() + ".port"));
        serverManager.register(server);

    }
}

