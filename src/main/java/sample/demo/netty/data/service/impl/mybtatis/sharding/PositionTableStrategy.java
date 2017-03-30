package sample.demo.netty.data.service.impl.mybtatis.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.MultipleKeysTableShardingAlgorithm;
import org.springframework.util.CollectionUtils;

import java.time.ZoneId;
import java.util.*;

public class PositionTableStrategy implements MultipleKeysTableShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue<?>> shardingValues) {

        final Set<String> results = new HashSet<>();

        Long deviceId = getShardingDeviceId(shardingValues);
        List<Date> times =  getShardingTime(shardingValues);

        for (Date time : times) {
            final String suffix = String.format("_%04d_m%02d", deviceId % 1024, month(time));
            String actual = "positions" + suffix;
            if (availableTargetNames.contains(actual)) {
                results.add(actual);
            }
        }

        return results;
    }

    private Long getShardingDeviceId(final Collection<ShardingValue<?>> shardingValues) {

        ShardingValue<?> shardingValue = shardingValues.stream()
                .filter(value -> value.getColumnName().equals("device_id"))
                .findAny().orElseThrow(IllegalArgumentException::new);
        return (Long) shardingValue.getValue();
    }

    private List<Date> getShardingTime(final Collection<ShardingValue<?>> shardingValues) {

        final List<Date> results = new ArrayList<>(16);
        for (ShardingValue<?> shardingValue : shardingValues) {
            if (shardingValue.getColumnName().equals("time")) {
                if (shardingValue.getValue() != null) {
                    results.add((Date) shardingValue.getValue());
                }
                if (!CollectionUtils.isEmpty(shardingValue.getValues())) {
                    results.addAll((Collection<? extends Date>) shardingValue.getValues());
                }
            }
        }

        return results;
    }

    private int month(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT")));
        calendar.setTime(date);

        return calendar.get(Calendar.MONTH) + 1;
    }

}
