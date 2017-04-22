package sample.demo.netty.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sample.demo.netty.data.domain.Position;
import sample.demo.netty.data.domain.support.PositionIndex;
import sample.demo.netty.data.service.DeviceService;
import sample.demo.netty.data.service.PositionService;
import sample.demo.netty.data.service.impl.cache.LastPositionCache;
import sample.demo.netty.data.service.impl.mybtatis.mapper.PositionIndexMapper;
import sample.demo.netty.data.service.impl.mybtatis.mapper.PositionMapper;

@Component
public class PositionServiceImpl implements PositionService {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private PositionMapper positionMapper;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private PositionIndexMapper positionIndexMapper;
    @Autowired
    private LastPositionCache lastPositionCache;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void savePosition(Position position) {

        positionMapper.insert(position);

        PositionIndex index = new PositionIndex(position.getId(), position.getTime());
        positionIndexMapper.insertIndex(position.getDeviceId(), index);

        if (!isOutdated(position)) {
            lastPositionCache.put(position.getDeviceId(), position);
        }
    }

    @Override
    public Position getLastPosition(long deviceId) {
        return loadLastPosition(deviceId);
    }

    @Override
    public boolean isLatestPosition(Position position) {
        Long lastPosId = deviceService.lastPosition(position.getDeviceId());
        return lastPosId != null && lastPosId.equals(position.getId());
    }

    private Position loadLastPosition(long deviceId) {
        Position pos = lastPositionCache.get(deviceId);
        if (pos != null) {
            return pos;
        }

        Long lastPosId = deviceService.lastPosition(deviceId);
        if (lastPosId != null) {
            PositionIndex index = positionIndexMapper.selectIndex(deviceId, lastPosId);
            if (index != null) {
                pos = positionMapper.select(deviceId, index);
                if (pos != null) {
                    lastPositionCache.put(deviceId, pos);
                }
            }
        }

        return pos;
    }

    private boolean isOutdated(Position position) {
        Position lastPos = lastPositionCache.get(position.getDeviceId());
        return !(null == lastPos || position.getTime().after(lastPos.getTime()));
    }
}



