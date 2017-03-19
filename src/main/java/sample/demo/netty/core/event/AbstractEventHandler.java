package sample.demo.netty.core.event;

import org.springframework.beans.factory.annotation.Autowired;
import sample.demo.netty.core.AbstractDataHandler;
import sample.demo.netty.data.domain.Event;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.service.EventService;

import java.util.Collection;

public abstract class AbstractEventHandler extends AbstractDataHandler {

    @Autowired
    private EventService eventService;

    @Override
    protected Position handlePosition(Position position) {

        Collection<Event> events = analyzePosition(position);
        if (events != null) {
            for (Event event : events) {
                eventService.saveEvent(event);
            }
        }

        return position;
    }

    protected abstract Collection<Event> analyzePosition(Position position);


}
