package com.freudian.service;

import com.freudian.bean.Specialty;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public interface ISpecialtyService {

    public void crawlAllSpecialtyData() throws IOException, KeyManagementException, NoSuchAlgorithmException;

    public Map<String, String> crawlAllSpecialtyURL() throws IOException, KeyManagementException, NoSuchAlgorithmException;

    public List<Specialty> getSpecialtiesByNameList(List<String> specialtyNameList);
}
