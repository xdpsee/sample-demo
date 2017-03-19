package sample.demo.netty.data.service;

import sample.demo.netty.data.domain.Position;

public interface PositionService {

    void savePosition(Position position);

    Position getLastPosition(long deviceId);

    boolean isLatestPosition(Position position);

}
