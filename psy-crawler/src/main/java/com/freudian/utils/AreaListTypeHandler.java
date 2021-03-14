package com.freudian.utils;

import com.freudian.bean.Area;
import com.freudian.service.IAreaService;
import com.freudian.service.impl.AreaServiceImpl;
import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes({JdbcType.VARCHAR})
public class AreaListTypeHandler extends BaseTypeHandler<List<Area>> {

    IAreaService areaService = new AreaServiceImpl();


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<Area> areaList, JdbcType jdbcType) throws SQLException {
        //1.List集合转字符串
        StringBuffer sb = new StringBuffer();
        for (Area area : areaList) {
            sb.append(area.getAreaName()).append(" ");
        }
        //2.设置给ps
        preparedStatement.setString(i, sb.toString().substring(0, sb.toString().length() - 1));
    }

    @Override
    public List<Area> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String[] split = resultSet.getString(s).split(" ");
        return CommonUtil.getListByString(split, new Area());
    }

    @Override
    public List<Area> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String[] split = resultSet.getString(i).split(" ");
        return CommonUtil.getListByString(split, new Area());
    }

    @Override
    public List<Area> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String[] split = callableStatement.getString(i).split(",");
        return CommonUtil.getListByString(split, new Area());
    }
}
