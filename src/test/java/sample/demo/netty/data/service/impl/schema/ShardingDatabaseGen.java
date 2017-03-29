package sample.demo.netty.data.service.impl.schema;

import org.junit.Test;

import java.io.FileOutputStream;

public class ShardingDatabaseGen {

    @Test
    public void testShardingGen() {

        try {
            for (int i = 0; i < 4; ++i) { // 4 database

                FileOutputStream outputStream = new FileOutputStream(String.format("trace-db%d.schema.sql", i));
                StringBuilder sb = new StringBuilder();
                String dbFormat = "DROP DATABASE IF EXISTS `trace-db%d`;\n" +
                        "CREATE DATABASE `trace-db%d`;\n" +
                        "USE `trace-db%d`;\n\n";

                String format =
                        "CREATE TABLE positions_%04d_m%02d(\n" +
                                "  id BIGINT UNSIGNED NOT NULL ,\n" +
                                "  gmt_create DATETIME NOT NULL,\n" +
                                "  gmt_modified DATETIME NOT NULL,\n" +
                                "  device_id BIGINT UNSIGNED NOT NULL ,\n" +
                                "  outdated BOOLEAN DEFAULT FALSE ,\n" +
                                "  located BOOLEAN DEFAULT FALSE ,\n" +
                                "  latitude DOUBLE DEFAULT 0,\n" +
                                "  longitude DOUBLE DEFAULT 0,\n" +
                                "  altitude DOUBLE DEFAULT 0,\n" +
                                "  time DATETIME NOT NULL ,\n" +
                                "  speed DOUBLE DEFAULT 0,\n" +
                                "  course FLOAT DEFAULT 0,\n" +
                                "  accuracy DOUBLE DEFAULT 0,\n" +
                                "  fixed_time DATETIME ,\n" +
                                "  network JSON NOT NULL ,\n" +
                                "  extras JSON NOT NULL ,\n" +
                                "  PRIMARY KEY (device_id, time)\n" +
                                ");\n";

                outputStream.write(String.format(dbFormat, i, i, i).getBytes());

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

