package sample.demo.netty.protocol.test.handler;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import sample.demo.netty.core.handler.AbstractHandler;
import sample.demo.netty.data.domain.Device;
import sample.demo.netty.data.service.DeviceService;
import sample.demo.netty.data.service.exception.DeviceNotFoundException;
import sample.demo.netty.protocol.test.message.LoginMessage;

public class LoginMessageHandler extends AbstractHandler<LoginMessage> {

    @Autowired
    private DeviceService deviceService;

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, LoginMessage msg) throws Exception {

        Device device = deviceService.getDeviceByUnique(msg.getUniqueId());
        if (null == device) {
            throw new DeviceNotFoundException(String.format("device:%s not found", msg.getUniqueId()));
        }



    }
}
