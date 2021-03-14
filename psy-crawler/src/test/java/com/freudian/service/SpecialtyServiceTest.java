package com.freudian.service;

import com.freudian.service.impl.SpecialtyServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class SpecialtyServiceTest {

    ISpecialtyService specialtyService = new SpecialtyServiceImpl();

    @Test
    public void testInsertSpecialty() throws NoSuchAlgorithmException, IOException, KeyManagementException {
        specialtyService.crawlAllSpecialtyData();
    }

    @Test
    public void testGetSpecialtyURL() throws NoSuchAlgorithmException, IOException, KeyManagementException {
        Map<String, String> specialtyURLMap = specialtyService.crawlAllSpecialtyURL();
        for (Map.Entry<String, String> entry : specialtyURLMap.entrySet()) {
            System.out.println("specialtyName : " + entry.getKey() + "; specialtyURL : " + entry.getValue());
        }
    }


}
