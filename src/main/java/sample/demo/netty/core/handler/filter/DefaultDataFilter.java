package sample.demo.netty.core.handler.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sample.demo.netty.core.handler.AbstractDataHandler;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.service.PositionService;

public final class DefaultDataFilter extends AbstractDataHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDataFilter.class);

    @Autowired
    private PositionService positionService;

    private final FilterPolicy filterPolicy;

    public DefaultDataFilter(FilterPolicy filterPolicy) {
        this.filterPolicy = filterPolicy;
    }

    @Override
    protected Position handlePosition(Position position) {

        if (position != null) {
            Position lastPos = positionService.getLastPosition(position.getDeviceId());

            try {
                if (filterPolicy != null && filterPolicy.filter(position, lastPos)) {
                    return null;
                }
            } catch (Exception e) {
                logger.error("filterPolicy.filter exception", e);
            }

        }

        return position;
    }

}
