package com.wy.demo.mybatis.byme;

import com.wy.demo.mybatis.byme.dto.Name;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Name.class)
//自定义的类型不会在JavaType中有，就没有配置@MappedTypes()来指定处理目标（处理器网上有很多，此处就不放源码了）
public class NameHandler implements TypeHandler<Name> {
    @Override
    public void setParameter(PreparedStatement ps, int i, Name parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.getFirstName()+" "+parameter.getLastName());
    }

    @Override
    public Name getResult(ResultSet rs, String columnName) throws SQLException {
        String name=rs.getString(columnName);
        Name result=new Name();
        String[] array=name.split(" ");
        result.setFirstName(array[0]);
        result.setLastName(array[1]);
        return result;
    }

    @Override
    public Name getResult(ResultSet rs, int columnIndex) throws SQLException {
        String name=rs.getString(columnIndex);
        Name result=new Name();
        String[] array=name.split(" ");
        result.setFirstName(array[0]);
        result.setLastName(array[1]);
        return result;
    }

    @Override
    public Name getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}

