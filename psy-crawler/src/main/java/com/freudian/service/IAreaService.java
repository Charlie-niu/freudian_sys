package com.freudian.service;

import com.freudian.bean.Area;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IAreaService {
    public void crawlAllAreaData() throws IOException;

    public List<String> crawlAreaURL() throws IOException, NoSuchAlgorithmException, KeyManagementException;

    public List<Area> getDoctortAreaListByArray(String[] areaNames);
}
