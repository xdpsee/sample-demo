package sample.demo.netty.core;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.EventExecutor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.demo.netty.utils.ClassUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ServerManager {

    private static final Logger logger = LoggerFactory.getLogger(ServerManager.class);

    private final Map<String, TrackerServer> servers = new LinkedHashMap<>(16);
    @Setter
    private Integer numberOfThreads = 4;
    @Getter
    private EventLoopGroup bossGroup;
    @Getter
    private EventLoopGroup workerGroup;
    @Getter
    private EventExecutor executor;

    public ServerManager() {
        this.bossGroup = new NioEventLoopGroup(numberOfThreads);
        this.workerGroup = new NioEventLoopGroup(numberOfThreads);
        this.executor = new DefaultEventExecutor();

        try {
            init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    protected void init() throws Exception {

        String packageName = "sample.demo.netty.protocol";
        Collection<Class<?>> classes = ClassUtils.getClasses(packageName, Protocol.class);
        for (Class<?> clazz : classes) {
            if (AbstractProtocol.class.isAssignableFrom(clazz)) {

                logger.info(String.format("protocol class : %s found.", clazz.getName()));

                AbstractProtocol protocol = (AbstractProtocol) clazz.newInstance();
                if (Configs.INSTANCE.hasKey(protocol.name() + ".port")) {
                    protocol.initTrackerServer(this);
                }
            }
        }

    }

    public void startup() {
        servers.entrySet().forEach(entry -> entry.getValue().start());
    }

    public void shutdown() {

        servers.entrySet().forEach(entry -> entry.getValue().stop());

        bossGroup.shutdownGracefully().syncUninterruptibly();
        workerGroup.shutdownGracefully().syncUninterruptibly();
    }

    public void register(TrackerServer server) {
        if (!servers.containsKey(server.getProtocol())) {
            servers.put(server.getProtocol(), server);
        } else {

        }
    }

}
