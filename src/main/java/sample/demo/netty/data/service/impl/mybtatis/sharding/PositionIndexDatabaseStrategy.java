package sample.demo.netty.data.service.impl.mybtatis.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;

import java.util.Collection;

public class PositionIndexDatabaseStrategy implements SingleKeyDatabaseShardingAlgorithm<Long> {

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
        throw new UnsupportedOperationException("");
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        throw new UnsupportedOperationException("");
    }
}
