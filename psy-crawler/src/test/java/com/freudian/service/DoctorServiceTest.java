package com.freudian.service;

import com.freudian.service.impl.DoctorServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


public class DoctorServiceTest {

    IDoctorService doctorService = new DoctorServiceImpl();

    @Test
    public void testCrawlAllDoctorData() throws NoSuchAlgorithmException, IOException, KeyManagementException {
        doctorService.crawlAllDoctorData();

    }
}
