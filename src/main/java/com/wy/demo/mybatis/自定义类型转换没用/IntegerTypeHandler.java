package com.wy.demo.mybatis.自定义类型转换没用;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerTypeHandler extends BaseTypeHandler<Integer> {
 
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Integer parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setInt(i, parameter);
  }
 
  @Override
  public Integer getNullableResult(ResultSet rs, String columnName)
      throws SQLException {
    return rs.getInt(columnName);
  }
 
  @Override
  public Integer getNullableResult(ResultSet rs, int columnIndex)
      throws SQLException {
    return rs.getInt(columnIndex);
  }
 
  @Override
  public Integer getNullableResult(CallableStatement cs, int columnIndex)
      throws SQLException {
    return cs.getInt(columnIndex);
  }
}