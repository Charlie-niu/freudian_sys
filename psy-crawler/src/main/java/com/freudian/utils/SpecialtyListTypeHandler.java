package com.freudian.utils;

import com.freudian.bean.Area;
import com.freudian.bean.Specialty;
import com.freudian.service.ISpecialtyService;
import com.freudian.service.impl.SpecialtyServiceImpl;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class SpecialtyListTypeHandler extends BaseTypeHandler<List<Specialty>> {
    ISpecialtyService specialtyService = new SpecialtyServiceImpl();
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<Specialty> specialties, JdbcType jdbcType) throws SQLException {
        //1.List集合转字符串
        StringBuffer sb = new StringBuffer();
        for (Specialty specialty : specialties) {
            sb.append(specialty.getSpecialtyName()).append(" ");
        }
        //2.设置给ps
        preparedStatement.setString(i, sb.toString().substring(0, sb.toString().length() - 1));
    }

    @Override
    public List<Specialty> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String[] split = resultSet.getString(s).split(" ");
        return CommonUtil.getListByString(split, new Specialty());
    }

    @Override
    public List<Specialty> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String[] split = resultSet.getString(i).split(" ");
        return CommonUtil.getListByString(split, new Specialty());
    }

    @Override
    public List<Specialty> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String[] split = callableStatement.getString(i).split(" ");
        return CommonUtil.getListByString(split, new Specialty());
    }
}
