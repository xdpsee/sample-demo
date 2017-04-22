package sample.demo.netty.core.handler.filter;

import sample.demo.netty.data.domain.Position;

public interface FilterPolicy {

    boolean filter(Position currPos, Position lastPos);

}
