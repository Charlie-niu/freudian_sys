package com.freudian.service;


import com.freudian.service.impl.AreaServiceImpl;
import org.junit.Test;

import java.io.IOException;


public class AreaServiceTest {
   IAreaService areaService = new AreaServiceImpl();

    @Test
    public void insertAreaTest() throws IOException {
        areaService.crawlAllAreaData();
    }
}
