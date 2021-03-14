package com.freudian.service;

import com.freudian.service.impl.CeshiServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class CeshiServiceTest {

    ICeshiService ceshiService = new CeshiServiceImpl();

    @Test
    public void testCrawlAllCeshiData() {
        try {
            ceshiService.crawlAllCeshiData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
