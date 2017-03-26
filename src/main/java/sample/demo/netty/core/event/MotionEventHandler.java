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
import sample.demo.netty.core.handler.AbstractEventHandler;
import sample.demo.netty.data.domain.Device;
import sample.demo.netty.data.domain.Event;
import sample.demo.netty.data.domain.EventType;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.service.DeviceService;
import sample.demo.netty.data.service.PositionService;

import java.util.Collection;
import java.util.Collections;

public class MotionEventHandler extends AbstractEventHandler {

    private double speedThreshold;

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private PositionService positionService;

    public MotionEventHandler() {
        speedThreshold = Configs.INSTANCE.getDouble("event.motion.speedThreshold", 0.01);
    }

    @Override
    protected Collection<Event> analyzePosition(Position position) {

        Device device = deviceService.getDeviceById(position.getDeviceId());
        if (null == device) {
            return null;
        }
        if (!positionService.isLatestPosition(position) || !position.isLocated()) {
            return null;
        }

        double speed = position.getSpeed(), oldSpeed = 0;
        Position lastPosition = positionService.getLastPosition(position.getDeviceId());
        if (lastPosition != null) {
            oldSpeed = lastPosition.getSpeed();
        }

        if (speed > speedThreshold && oldSpeed <= speedThreshold) {
            return Collections.singleton(
                    new Event(EventType.TYPE_DEVICE_MOVING, position.getDeviceId(), position.getId()));
        } else if (speed <= speedThreshold && oldSpeed > speedThreshold) {
            return Collections.singleton(
                    new Event(EventType.TYPE_DEVICE_STOPPED, position.getDeviceId(), position.getId()));
        }

        return null;
    }

}
