package sample.demo.netty.protocol.mobile.message;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sample.demo.netty.core.Message;

@Data
@RequiredArgsConstructor
public class RegistryMessage implements Message {
    @NonNull
    private double protocolVersion;
    @NonNull
    private String uniqueId;
    @NonNull
    private double appVersion;
    @NonNull
    private String time;
    @NonNull
    // raw data
    private String body;

    @Override
    public byte[] toBytes() {
        return body.getBytes();
    }


}
