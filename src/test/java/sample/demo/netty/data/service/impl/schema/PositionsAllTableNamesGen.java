package sample.demo.netty.data.service.impl.schema;

import org.junit.Test;

public class PositionsAllTableNamesGen {

    @Test
    public void testGenAllPositionSql() {

        try {
            StringBuilder builder = new StringBuilder();
            builder.append("[");

            for (int i = 0; i < 1024; ++i) {
                for (int j = 1; j <= 12; ++j) {
                    builder.append(String.format("positions_%04d_m%02d,", i, j));
                }
            }

            builder.deleteCharAt(builder.length()-1);
            builder.append("]");

            System.out.println(builder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
