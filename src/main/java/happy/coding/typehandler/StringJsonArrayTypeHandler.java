package happy.coding.typehandler;

import happy.coding.util.JacksonUtil;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// ["hello","world"]
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(String[].class)
public class StringJsonArrayTypeHandler implements TypeHandler<String[]> {
    // marketGoods
    // insert into market_goods (goods_sn,name,category_id,gallery,...) values (?,?,?,?,...)
    // preparedStatement.setString(4,marketGoods.getGallery())
    // 介入了jdbc过程
    @Override
    public void setParameter(PreparedStatement ps, int index, String[] parameter, JdbcType jdbcType) throws SQLException {
        // 给预编译的sql语句提供参数
        ps.setString(index, JacksonUtil.write(parameter));
    }
    // 查询marketGoods
    // MarketGoods marketGoods = new MarketGoods();
    // marketGoods.setGallery(resultSet.getString("gallery"))
    @Override
    public String[] getResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        if (string == null || string.length() == 0) {
            return new String[0];
        }
        return JacksonUtil.read(string,String[].class);
    }

    @Override
    public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        if (string == null || string.length() == 0) {
            return new String[0];
        }
        return JacksonUtil.read(string,String[].class);
    }

    @Override
    public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        if (string == null || string.length() == 0) {
            return new String[0];
        }
        return JacksonUtil.read(string,String[].class);
    }
}
