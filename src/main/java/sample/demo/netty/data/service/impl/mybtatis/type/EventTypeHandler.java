package sample.demo.netty.data.service.impl.mybtatis.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import sample.demo.netty.data.domain.EventType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventTypeHandler extends BaseTypeHandler<EventType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EventType eventType, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, eventType.code);
    }

    @Override
    public EventType getNullableResult(ResultSet rs, String s) throws SQLException {
        return EventType.valueOf(rs.getInt(s));
    }

    @Override
    public EventType getNullableResult(ResultSet rs, int i) throws SQLException {
        return EventType.valueOf(rs.getInt(i));
    }

    @Override
    public EventType getNullableResult(CallableStatement cs, int i) throws SQLException {
        return EventType.valueOf(cs.getInt(i));
    }
}
