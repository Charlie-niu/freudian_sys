package com.freudian.service.impl;

import com.freudian.bean.Specialty;
import com.freudian.dao.AreaMapper;
import com.freudian.dao.SpecialtyMapper;
import com.freudian.gather.SpecialtyGather;
import com.freudian.parser.SpecialtyParser;
import com.freudian.service.ISpecialtyService;
import com.freudian.utils.MyBatisUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Slf4j
public class SpecialtyServiceImpl implements ISpecialtyService {
    SpecialtyGather specialtyGather = new SpecialtyGather();
    SpecialtyParser specialtyParser = new SpecialtyParser();
    SpecialtyMapper specialtyMapper = MyBatisUtil.openSession(true).getMapper(SpecialtyMapper.class);


    @Override
    public void crawlAllSpecialtyData() throws IOException, KeyManagementException, NoSuchAlgorithmException {
        List<Specialty> specialtyList = specialtyParser.parseAllSpecialty(specialtyGather.gatherSpecialtyIndex());
        specialtyMapper.insertAllSpecialty(specialtyList);
    }

    @Override
    public Map<String, String> crawlAllSpecialtyURL() throws IOException, KeyManagementException, NoSuchAlgorithmException {
        return specialtyParser.getAllSpecialtyURL(specialtyGather.gatherSpecialtyIndex());
    }

    @Override
    public List<Specialty> getSpecialtiesByNameList(List<String> specialtyNameList) {

        return specialtyMapper.selectSpecialtiesByNameList(specialtyNameList);
    }
}
