package com.freudian.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface ICeshiService {

    public void crawlAllCeshiData() throws IOException, KeyManagementException, NoSuchAlgorithmException;
}
