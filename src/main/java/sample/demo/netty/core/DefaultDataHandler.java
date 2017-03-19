package sample.demo.netty.core;

import org.springframework.beans.factory.annotation.Autowired;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.service.PositionService;

public class DefaultDataHandler extends AbstractDataHandler {

    @Autowired
    private PositionService positionService;

    @Override
    protected Position handlePosition(Position position) {

        try {
            positionService.savePosition(position);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return position;
    }

}
