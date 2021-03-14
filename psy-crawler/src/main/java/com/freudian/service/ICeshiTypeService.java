package com.freudian.service;

import com.freudian.bean.CeshiType;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ICeshiTypeService {

    public void crawlAllCeshiTypeData() throws IOException, KeyManagementException, NoSuchAlgorithmException;

    public List<CeshiType> getAllCeshiType();
}
