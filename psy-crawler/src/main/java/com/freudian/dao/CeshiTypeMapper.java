package com.freudian.dao;

import com.freudian.bean.CeshiType;

import java.util.List;

public interface CeshiTypeMapper {

    public void insertCeshiType(CeshiType ceshiType);

    public List<CeshiType> selectAllCeshiType();
}
