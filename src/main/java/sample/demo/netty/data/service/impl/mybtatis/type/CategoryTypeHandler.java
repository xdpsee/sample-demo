package sample.demo.netty.data.service.impl.mybtatis.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import sample.demo.netty.data.domain.Category;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryTypeHandler extends BaseTypeHandler<Category> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Category category, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, category.code);
    }

    @Override
    public Category getNullableResult(ResultSet rs, String s) throws SQLException {
        return Category.valueOf(rs.getInt(s));
    }

    @Override
    public Category getNullableResult(ResultSet rs, int i) throws SQLException {
        return Category.valueOf(rs.getInt(i));
    }

    @Override
    public Category getNullableResult(CallableStatement cs, int i) throws SQLException {
        return Category.valueOf(cs.getInt(i));
    }
}
