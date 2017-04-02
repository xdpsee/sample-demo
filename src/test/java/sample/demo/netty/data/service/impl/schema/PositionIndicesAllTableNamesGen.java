package sample.demo.netty.data.service.impl.schema;

import org.junit.Test;

public class PositionIndicesAllTableNamesGen {

    @Test
    public void testGenAllPositionIndexSql() {

        try {
            StringBuilder builder = new StringBuilder();
            builder.append("[");

            for (int i = 0; i < 1024; ++i) {
                builder.append(String.format("position_indices_%04d,", i));
            }

            builder.deleteCharAt(builder.length() - 1);
            builder.append("]");

            System.out.println(builder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
