package sample.demo.netty.data.service.impl.cache;

import org.springframework.stereotype.Component;
import sample.demo.netty.data.domain.Position;

@Component
public class LastPositionCache extends AbstractRedisCache<Long,Position> {

}


