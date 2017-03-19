package sample.demo.netty.data.domain;

import lombok.Data;
import sample.demo.netty.core.Configs;

@Data
public class CellTower {

    public static CellTower from(int mcc, int mnc, int lac, long cid) {
        CellTower cellTower = new CellTower();
        cellTower.setMobileCountryCode(mcc);
        cellTower.setMobileNetworkCode(mnc);
        cellTower.setLocationAreaCode(lac);
        cellTower.setCellId(cid);
        return cellTower;
    }

    public static CellTower from(int mcc, int mnc, int lac, long cid, int rssi) {
        CellTower cellTower = CellTower.from(mcc, mnc, lac, cid);
        cellTower.setSignalStrength(rssi);
        return cellTower;
    }

    public static CellTower fromLacCid(int lac, long cid) {
        return from(
                Configs.INSTANCE.getInteger("geolocation.mcc"),
                Configs.INSTANCE.getInteger("geolocation.mnc"), lac, cid);
    }

    public static CellTower fromCidLac(long cid, int lac) {
        return fromLacCid(lac, cid);
    }

    private String radioType;

    private Long cellId;

    private Integer locationAreaCode;

    private Integer mobileCountryCode;

    private Integer mobileNetworkCode;

    private Integer signalStrength;

}
