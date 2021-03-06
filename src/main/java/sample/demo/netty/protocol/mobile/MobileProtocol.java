package sample.demo.netty.protocol.mobile;

import io.netty.channel.ChannelPipeline;
import sample.demo.netty.core.*;
import sample.demo.netty.core.handler.filter.DefaultDataFilter;
import sample.demo.netty.core.handler.DefaultDataHandler;
import sample.demo.netty.protocol.mobile.decoder.MobileFrameDecoder;
import sample.demo.netty.protocol.mobile.decoder.MobileProtocolDecoder;
import sample.demo.netty.protocol.mobile.handler.RegistryMessageHandler;
import sample.demo.netty.utils.AutowireUtils;

import java.util.Collection;

@SuppressWarnings("unused")
public class MobileProtocol extends AbstractProtocol {

    public MobileProtocol() {
        super("mobile");
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
            public void initPipeline(ChannelPipeline pipeline) {

                pipeline.addLast("frameDecoder", AutowireUtils.autowire(new MobileFrameDecoder()));
                pipeline.addLast("objectDecoder", AutowireUtils.autowire(new MobileProtocolDecoder()));

                pipeline.addLast("loginHandler", AutowireUtils.autowire(new RegistryMessageHandler()));
                pipeline.addLast("filterHandler", AutowireUtils.autowire(
                        new DefaultDataFilter(new MobileFilterPolicy())
                ));
                pipeline.addLast("dataHandler", AutowireUtils.autowire(new DefaultDataHandler()));

            }
        };

        server.setPort(Configs.INSTANCE.getInteger(name() + ".port"));
        serverManager.register(server);

    }
}

