package com.freudian.service;

import com.freudian.bean.CeshiType;
import com.freudian.service.impl.CeshiTypeServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CeshiTypeServiceTest {
    ICeshiTypeService ceshiTypeService = new CeshiTypeServiceImpl();

    @Test
    public void testSelectCeshiType() {
        //查找
        List<CeshiType> ceshitypeList = ceshiTypeService.getAllCeshiType();
        for (CeshiType ceshiType : ceshitypeList) {
            System.out.println(ceshiType.toString());
        }
    }


    @Test
    public void testInsertCeshiType(){
        // 插入
        try {
            ceshiTypeService.crawlAllCeshiTypeData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
