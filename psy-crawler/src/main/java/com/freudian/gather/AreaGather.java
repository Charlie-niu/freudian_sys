package com.freudian.gather;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.freudian.bean.Area;
import com.freudian.utils.FileStreamUtil;
import com.freudian.utils.HttpConnectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AreaGather {

    String jsonPath = "src/main/resources/level.json";

    public JSONArray gatherAreaData() throws IOException {
        log.debug("正在获取 三级关联地区 数据...");
        List<Area> areaList = new ArrayList<Area>();

        String areaString = FileStreamUtil.readJsonFile(jsonPath);
        JSONArray areasJson = JSONArray.parseArray(areaString);
        return areasJson;
    }

    /**
     * 根据地域爬取医生信息，获取所有的一级地区名
     *  https://www.psy525.cn/find/area.html
     * @return
     * @throws IOException
     */
    public Document gatherAreaIndex() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        log.debug("正在获取 地区查找首页 的数据...");
        Connection connection = HttpConnectionUtil.connectionURL(HttpConnectionUtil.FIND_BY_AREA);
        Document document = connection.get();
        return document;
    }
}
