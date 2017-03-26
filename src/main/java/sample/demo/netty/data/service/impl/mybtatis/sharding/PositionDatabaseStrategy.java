package sample.demo.netty.data.service.impl.mybtatis.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * positions 4个库, 每个库256长表
 */
public class PositionDatabaseStrategy implements SingleKeyDatabaseShardingAlgorithm<Long> {

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {

        long deviceId = shardingValue.getValue();
        long locate = (deviceId % 1024) / 256;

        for (String targetName : availableTargetNames) {
            if (targetName.endsWith(String.valueOf(locate))) {
                return targetName;
            }
        }

        throw new UnsupportedOperationException("");
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {

        List<String> results = new ArrayList<>();

        shardingValue.getValues().forEach(value ->
                availableTargetNames.forEach(targetName -> {
                    long locate = (value % 1024) / 256;
                    if (targetName.endsWith(String.valueOf(locate))) {
                        results.add(targetName);
                    }
                })
        );

        return results;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        throw new UnsupportedOperationException();
    }
}
