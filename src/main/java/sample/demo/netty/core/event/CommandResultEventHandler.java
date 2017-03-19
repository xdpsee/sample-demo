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


import sample.demo.netty.data.domain.Event;
import sample.demo.netty.data.domain.EventType;
import sample.demo.netty.data.domain.Position;

import java.util.Collection;
import java.util.Collections;

public class CommandResultEventHandler extends AbstractEventHandler {

    @Override
    protected Collection<Event> analyzePosition(Position position) {
        String commandResult = position.getString(Position.KEY_RESULT);
        if (commandResult != null) {
            Event event = new Event(EventType.TYPE_COMMAND_RESULT, position.getDeviceId(), position.getId());
            event.set(Position.KEY_RESULT, commandResult);
            return Collections.singleton(event);
        }

        return null;
    }

}
