package sample.demo.netty.data.service.impl.mybtatis.type;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import sample.demo.netty.data.domain.Network;
import sample.demo.netty.utils.JSONUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NetworkTypeHandler extends BaseTypeHandler<Network> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Network network, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JSONUtils.toJsonString(network));
    }

    @Override
    public Network getNullableResult(ResultSet rs, String s) throws SQLException {
        String value = rs.getString(s);
        return JSONUtils.fromJsonString(value, new TypeReference<Network>(){});
    }

    @Override
    public Network getNullableResult(ResultSet rs, int i) throws SQLException {
        String value = rs.getString(i);
        return JSONUtils.fromJsonString(value, new TypeReference<Network>(){});
    }

    @Override
    public Network getNullableResult(CallableStatement cs, int i) throws SQLException {
        String value = cs.getString(i);
        return JSONUtils.fromJsonString(value, new TypeReference<Network>(){});
    }

}

