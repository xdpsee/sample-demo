/*
 * Copyright 2016 Anton Tananaev (anton@traccar.org)
 * Copyright 2016 Andrey Kunitsyn (andrey@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.demo.netty.core.event;


import org.springframework.beans.factory.annotation.Autowired;
import sample.demo.netty.core.handler.AbstractEventHandler;
import sample.demo.netty.data.domain.Device;
import sample.demo.netty.data.domain.Event;
import sample.demo.netty.data.domain.EventType;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.service.DeviceService;
import sample.demo.netty.data.service.PositionService;

import java.util.Collection;
import java.util.Collections;

public class IgnitionEventHandler extends AbstractEventHandler {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private PositionService positionService;

    @Override
    protected Collection<Event> analyzePosition(Position position) {
        Device device = deviceService.getDeviceById(position.getDeviceId());
        if (device == null || !positionService.isLatestPosition(position)) {
            return null;
        }

        Collection<Event> result = null;

        if (position.hasKey(Position.KEY_IGNITION)) {
            boolean ignition = position.getBoolean(Position.KEY_IGNITION);

            Position lastPosition = positionService.getLastPosition(position.getDeviceId());
            if (lastPosition != null && lastPosition.hasKey(Position.KEY_IGNITION)) {
                boolean oldIgnition = lastPosition.getBoolean(Position.KEY_IGNITION);

                if (ignition && !oldIgnition) {
                    result = Collections.singleton(
                            new Event(EventType.TYPE_IGNITION_ON, position.getDeviceId(), position.getId()));
                } else if (!ignition && oldIgnition) {
                    result = Collections.singleton(
                            new Event(EventType.TYPE_IGNITION_OFF, position.getDeviceId(), position.getId()));
                }
            }
        }
        return result;
    }

}
