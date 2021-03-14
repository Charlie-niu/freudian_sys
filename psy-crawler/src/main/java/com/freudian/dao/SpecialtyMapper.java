package com.freudian.dao;


import com.freudian.bean.Specialty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecialtyMapper {

    public void insertAllSpecialty(@Param("specialtyList") List<Specialty> specialtyList);

    public List<Specialty> selectSpecialtiesByNameList(@Param("specialtyNames") List<String> specialtyNames);
}
