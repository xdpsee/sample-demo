package sample.demo.netty.data.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Network {
    
    public Network() {}
    public Network(CellTower cellTower) {
        cellTowers.add(cellTower);
    }

    private Integer homeMobileCountryCode;

    private Integer homeMobileNetworkCode;

    private String radioType = "gsm";

    private String carrier;

    private Boolean considerIp = false;

    private final List<CellTower> cellTowers = new ArrayList<>();

    private final List<WifiAccessPoint> wifiAccessPoints = new ArrayList<>();



}
