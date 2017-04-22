package sample.demo.netty.protocol.mobile;

import sample.demo.netty.core.handler.filter.FilterPolicy;
import sample.demo.netty.data.domain.Position;

public class MobileFilterPolicy implements FilterPolicy {

    @Override
    public boolean filter(Position currPos, Position lastPos) {
        if (null == lastPos) {
            return false;
        }



        return true;
    }

}
