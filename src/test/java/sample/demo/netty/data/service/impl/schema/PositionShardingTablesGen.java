package sample.demo.netty.data.service.impl.schema;

import org.junit.Test;

import java.io.FileOutputStream;

public class PositionShardingTablesGen {

    @Test
    public void testShardingTablesGen() {

        try {
            for (int i = 0; i < 4; ++i) { // 4 database

                FileOutputStream outputStream = new FileOutputStream(String.format("trace-db%d.positions.sql", i));
                StringBuilder sb = new StringBuilder();
                String format =
                        "CREATE TABLE positions_%04d_m%02d(\n" +
                                "  id BIGINT UNSIGNED NOT NULL ,\n" +
                                "  gmt_create TIMESTAMP NOT NULL DEFAULT current_timestamp,\n" +
                                "  gmt_modified TIMESTAMP NOT NULL DEFAULT current_timestamp,\n" +
                                "  device_id BIGINT UNSIGNED NOT NULL ,\n" +
                                "  protocol VARCHAR(20) NOT NULL ,\n" +
                                "  outdated BOOLEAN DEFAULT FALSE ,\n" +
                                "  located BOOLEAN DEFAULT FALSE ,\n" +
                                "  latitude DOUBLE DEFAULT 0,\n" +
                                "  longitude DOUBLE DEFAULT 0,\n" +
                                "  altitude DOUBLE DEFAULT 0,\n" +
                                "  device_time TIMESTAMP DEFAULT current_timestamp NOT NULL ,\n" +
                                "  fixed_time TIMESTAMP DEFAULT current_timestamp NOT NULL ,\n" +
                                "  speed DOUBLE DEFAULT 0,\n" +
                                "  course FLOAT DEFAULT 0,\n" +
                                "  accuracy DOUBLE DEFAULT 0,\n" +
                                "  network JSON NOT NULL ,\n" +
                                "  extras JSON NOT NULL ,\n" +
                                "  PRIMARY KEY (device_id, device_time)\n" +
                                ");";

                for (int j = 0; j < 256; ++j) {
                    for (int m = 1; m <= 12; ++m) {
                        sb.append(String.format(format, i * 256 + j, m));
                    }
                }

                outputStream.write(sb.toString().getBytes());

                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
