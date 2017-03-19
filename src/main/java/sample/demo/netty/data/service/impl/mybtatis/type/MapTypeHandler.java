package sample.demo.netty.data.service.impl.mybtatis.type;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import sample.demo.netty.utils.JSONUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MapTypeHandler extends BaseTypeHandler<Map> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map map, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSONUtils.toJsonString(map));
    }

    @Override
    public Map getNullableResult(ResultSet rs, String s) throws SQLException {
        String value = rs.getString(s);
        return JSONUtils.fromJsonString(value, new TypeReference<HashMap>(){});
    }

    @Override
    public Map getNullableResult(ResultSet rs, int i) throws SQLException {
        String value = rs.getString(i);
        return JSONUtils.fromJsonString(value, new TypeReference<HashMap>(){});
    }

    @Override
    public Map getNullableResult(CallableStatement cs, int i) throws SQLException {
        String value = cs.getString(i);
        return JSONUtils.fromJsonString(value, new TypeReference<HashMap>(){});
    }
}
