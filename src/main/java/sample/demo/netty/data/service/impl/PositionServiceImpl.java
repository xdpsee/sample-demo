package sample.demo.netty.data.service.impl;

import org.springframework.stereotype.Component;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.service.PositionService;

@Component
public class PositionServiceImpl implements PositionService {

    @Override
    public void savePosition(Position position) {

    }

    @Override
    public Position getLastPosition(long deviceId) {
        return null;
    }

    @Override
    public boolean isLatestPosition(Position position) {
        return false;
    }
}
