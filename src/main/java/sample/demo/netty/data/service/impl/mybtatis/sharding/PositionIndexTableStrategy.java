package sample.demo.netty.data.service.impl.mybtatis.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.MultipleKeysTableShardingAlgorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PositionIndexTableStrategy implements MultipleKeysTableShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue<?>> shardingValues) {
        final Set<String> results = new HashSet<>();

        Long deviceId = getShardingDeviceId(shardingValues);

        final String suffix = String.format("_%04d", deviceId % 1024);
        String actual = "position_indices" + suffix;
        if (availableTargetNames.contains(actual)) {
            results.add(actual);
        }
        
        return results;
    }

    private Long getShardingDeviceId(final Collection<ShardingValue<?>> shardingValues) {

        ShardingValue<?> shardingValue = shardingValues.stream()
                .filter(value -> value.getColumnName().equals("device_id"))
                .findAny().orElseThrow(IllegalArgumentException::new);
        return (Long) shardingValue.getValue();
    }
}
