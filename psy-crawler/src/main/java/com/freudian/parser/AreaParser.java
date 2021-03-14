package com.freudian.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.freudian.bean.Area;
import com.freudian.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AreaParser {

    public static List<Area> areaList = new ArrayList<>();

    public List<String> getFirstLevelAreaURL(Document document) {
        log.debug("开始解析所有 一级地区 的URL...");
        List<String> areaURLList = new ArrayList<>();
        Elements elementList = document.getElementById("afind").getElementsByTag("p");

        Elements aEles = elementList.get(1).getElementsByClass("i-city");
        Elements bEles = elementList.get(2).getElementsByClass("i-city");

        for (int i = 0; i < 3; i++) {
            areaURLList.add(aEles.get(i).attr("href"));
        }
        for (Element ele : bEles) {
            if (ele.text().equals("海外"))
                return areaURLList;
            areaURLList.add(ele.attr("href"));
        }
        return areaURLList;
    }

    public List<Area> parserAllArea(JSONArray areasJson) {
        log.debug("开始解析 Area 数据...");
        for (int i = 0; i < areasJson.size(); i++) {
            JSONObject areaObject = (JSONObject) areasJson.get(i);
            areaList.add(recurveParseArea(areaObject, 0,0));
        }
        return areaList;
    }

    public static Area recurveParseArea(JSONObject areaObject,Integer areaLevel, Integer parentCode) {
        String areaName = (String) areaObject.get("name");
        Integer areaCode = Integer.valueOf((String) areaObject.get("code"));

        JSONArray areaChildrenJson = (JSONArray) areaObject.get("children");
        if (areaChildrenJson != null) {
            for (int j = 0; j < areaChildrenJson.size(); j++) {
                areaList.add(recurveParseArea((JSONObject) areaChildrenJson.get(j),areaLevel+1, areaCode));
            }
        }
        Area area = new Area(null, areaName, areaLevel, areaCode, parentCode, LocalDateTime.now(), LocalDateTime.now(), null);
        log.debug("Area : " + area.getAreaName());

        return area;

    }
}
