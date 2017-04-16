package sample.demo.netty.core.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sample.demo.netty.core.Message;
import sample.demo.netty.data.domain.Device;
import sample.demo.netty.data.service.DeviceService;
import sample.demo.netty.data.service.exception.DeviceNotFoundException;
import sample.demo.netty.utils.ChannelAttribute;
import sample.demo.netty.utils.ChannelAttributesUtils;

public abstract class AbstractHandler<T extends Message> extends SimpleChannelInboundHandler<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractHandler.class);

    @Autowired
    protected DeviceService deviceService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {

        String uniqueId = msg.getUniqueId();
        Device device = deviceService.getDeviceByUnique(uniqueId);
        if (null == device) {
            throw new DeviceNotFoundException(String.format("device: %s not found", uniqueId));
        }

        ChannelAttributesUtils.setIfAbsent(ctx, ChannelAttribute.DEVICE_ID, device.getId());
        ChannelAttributesUtils.setIfAbsent(ctx, ChannelAttribute.DEVICE_UNIQUE_ID, uniqueId);

        messageReceived(ctx, msg);

    }

    protected abstract void messageReceived(ChannelHandlerContext ctx, T msg) throws Exception;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        try {
            ctx.close();
        } finally {
            logger.error("", cause);
        }
    }
}

