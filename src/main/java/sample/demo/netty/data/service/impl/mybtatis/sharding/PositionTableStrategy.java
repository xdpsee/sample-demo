package sample.demo.netty.data.service.impl.mybtatis.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.MultipleKeysTableShardingAlgorithm;

import java.time.ZoneId;
import java.util.*;

public class PositionTableStrategy implements MultipleKeysTableShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue<?>> shardingValues) {

        final List<String> results = new ArrayList<>();

        Long deviceId = (Long) getShardingValue(shardingValues, "device_id");
        Date deviceTime = (Date) getShardingValue(shardingValues, "time");

        final String suffix = String.format("_%04d_m%02d", deviceId % 1024, month(deviceTime));
        String actual = "positions" + suffix;
        if (availableTargetNames.contains(actual)) {
            results.add(actual);
        }

        return results;
    }

    private Object getShardingValue(final Collection<ShardingValue<?>> shardingValues, final String shardingKey) {

        ShardingValue<?> shardingValue = shardingValues.stream()
                .filter(value -> value.getColumnName().equals(shardingKey))
                .findAny().orElseThrow(IllegalArgumentException::new);
        return shardingValue.getValue();
    }

    private int month(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT")));
        calendar.setTime(date);

        return calendar.get(Calendar.MONTH) + 1;
    }

}
