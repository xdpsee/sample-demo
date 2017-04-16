package sample.demo.netty.core.handler;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.service.PositionService;

import java.util.HashMap;
import java.util.Map;

public final class DefaultDataFilter extends AbstractDataHandler {

    private Expression exp;

    @Autowired
    private PositionService positionService;

    public DefaultDataFilter(String expression) {
        exp = AviatorEvaluator.compile(expression);
    }

    @Override
    protected Position handlePosition(Position position) {

        if (position != null && !acceptable(position)) {
            return null;
        }

        return position;
    }

    private boolean acceptable(Position position) {

        Position lastPos = positionService.getLastPosition(position.getDeviceId());

        Map<String, Object> env = new HashMap<>();
        env.put("lastPos", lastPos);
        env.put("currPos", position);

        return (Boolean) exp.execute(env);
    }


}
