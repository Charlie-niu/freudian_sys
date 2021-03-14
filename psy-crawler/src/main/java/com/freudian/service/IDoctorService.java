package com.freudian.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


public interface IDoctorService {

    public void crawlAllDoctorData() throws IOException, NoSuchAlgorithmException, KeyManagementException;

}
