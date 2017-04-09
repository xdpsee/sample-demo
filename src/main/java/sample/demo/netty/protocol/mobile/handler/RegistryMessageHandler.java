package sample.demo.netty.protocol.mobile.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.springframework.beans.factory.annotation.Autowired;
import sample.demo.netty.core.handler.AbstractHandler;
import sample.demo.netty.data.domain.Device;
import sample.demo.netty.data.service.DeviceService;
import sample.demo.netty.data.service.exception.DeviceNotFoundException;
import sample.demo.netty.protocol.mobile.message.RegistryMessage;

public class RegistryMessageHandler extends AbstractHandler<RegistryMessage> {

    @Autowired
    private DeviceService deviceService;

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, RegistryMessage msg) throws Exception {

        Device device = deviceService.getDeviceByUnique(msg.getUniqueId());
        if (null == device) {
            throw new DeviceNotFoundException(String.format("device:%s not found", msg.getUniqueId()));
        }

        ctx.channel().attr(AttributeKey.newInstance("uniqueId")).set(device.getId());



    }
}
