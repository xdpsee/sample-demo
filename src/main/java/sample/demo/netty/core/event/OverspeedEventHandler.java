/*
 * Copyright 2016 Anton Tananaev (anton@traccar.org)
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
import sample.demo.netty.core.Configs;
import sample.demo.netty.data.domain.Device;
import sample.demo.netty.data.domain.Event;
import sample.demo.netty.data.domain.EventType;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.service.DeviceService;
import sample.demo.netty.data.service.PositionService;

import java.util.Collection;
import java.util.Collections;

public class OverspeedEventHandler extends AbstractEventHandler {

    public static final String ATTRIBUTE_SPEED_LIMIT = "speedLimit";

    private boolean notRepeat;

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private PositionService positionService;

    public OverspeedEventHandler() {
        notRepeat = Configs.INSTANCE.getBoolean("event.overspeed.notRepeat");
    }

    @Override
    protected Collection<Event> analyzePosition(Position position) {

        Device device = deviceService.getDeviceById(position.getDeviceId());
        if (null == device) {
            return null;
        }

        if (!positionService.isLatestPosition(position) || !position.isValid()) {
            return null;
        }

        double speed = position.getSpeed();
        double speedLimit = device.getDouble(ATTRIBUTE_SPEED_LIMIT);
        if (speedLimit <= 0.0) {
            return null;
        }

        double oldSpeed = 0;
        if (notRepeat) {
            Position lastPosition = positionService.getLastPosition(position.getDeviceId());
            if (lastPosition != null) {
                oldSpeed = lastPosition.getSpeed();
            }
        }

        if (speed > speedLimit && oldSpeed <= speedLimit) {
            Event event = new Event(EventType.TYPE_DEVICE_OVERSPEED, position.getDeviceId(), position.getId());
            event.set("speed", speed);
            event.set(ATTRIBUTE_SPEED_LIMIT, speedLimit);
            return Collections.singleton(event);
        }

        return null;
    }

}


