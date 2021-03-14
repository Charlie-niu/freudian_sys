package com.freudian.dao;

import com.freudian.bean.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AreaMapper {

    public void insertAllArea(@Param("areaList") List<Area> areaList);

    public Area selectAreaCascadeChildrenByAreaName(String areaName);


    public List<Area> cascadeSelectAreaListByName(String areaName);
}
