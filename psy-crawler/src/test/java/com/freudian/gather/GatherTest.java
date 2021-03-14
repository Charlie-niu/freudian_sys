package com.freudian.gather;

import com.alibaba.fastjson.JSONArray;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class GatherTest {
    CeshiGather ceshiGather = new CeshiGather();
    CeshiTypeGather ceshiTypeGather = new CeshiTypeGather();
    AreaGather areaGather = new AreaGather();

    @Test
    public void testCeshiGather() throws NoSuchAlgorithmException, IOException, KeyManagementException {
        Document document = ceshiGather.gatherCeshiIndex();

        List<Document> documentList = ceshiGather.gatherAllCeshiListByType("jiankang", "/ceshi/jiankang.html");
        for (Document document1 : documentList) {
            System.out.println(document.toString());
        }
    }

    @Test
    public void testCeshiTypeGather() throws NoSuchAlgorithmException, IOException, KeyManagementException {
        //Document document = ceshiTypeGather.gatherCeshiTypeIndex("jiankang","/ceshi/jiankang.html");

        List<Document> documentList = ceshiGather.gatherAllCeshiListByType("jiankang", "/ceshi/jiankang.html");
        for (Document document1 : documentList) {
            System.out.println(document1.toString());
        }

    }

    @Test
    public void testAreaGather() throws IOException, KeyManagementException, NoSuchAlgorithmException {
//        JSONArray areaJSONList = areaGather.gatherAreaData();
//        System.out.println(areaJSONList.size());
//        Document areaDocument = areaGather.gatherAreaIndex();
//        Element areaRequestElement = areaDocument.getElementById("afind").getElementsByClass("item").get(1);
//        System.out.println(areaRequestElement.getElementsByTag("a").attr("href"));
    }
}
