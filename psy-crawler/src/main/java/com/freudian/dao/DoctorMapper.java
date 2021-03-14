package com.freudian.dao;

import com.freudian.bean.Area;
import com.freudian.bean.Doctor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorMapper {
    public void insertAllDoctor(@Param("doctorList") List<Doctor> doctorList);
}
