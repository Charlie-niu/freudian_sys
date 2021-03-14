package com.freudian.parser;

import com.freudian.bean.CeshiType;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CeshiTypeParser {
    /**
     * 解析心理测试类型的内容
     * @param document
     * @return
     */
    public CeshiType parseCeshiTypeContent(Document document){
        String csTypeNameMetadata = document.getElementsByClass("cstypedes").select("p[class=title]").text();
        String csTypeDesMetadata = document.getElementsByClass("cstypedes").select("p[class=hdes]").text();
        String csTypeName = csTypeNameMetadata.substring(0, csTypeNameMetadata.indexOf("测"));

        log.debug("正在解析" + csTypeName + "心理测试类型的数据...");

        CeshiType ceshiType = new CeshiType();
        ceshiType.setCeshiTypeName(csTypeName);
        ceshiType.setCeshiTypeDesc(csTypeDesMetadata);
        ceshiType.setCreateDate(LocalDateTime.now());
        ceshiType.setChangeDate(LocalDateTime.now());

        return ceshiType;
    }

    /**
     * 获取所有的 csType 的 URL
     * @param document
     * @return
     */
    public Map<String, String> getAllCeshiTypeURL(Document document) {
        log.debug("开始解析不同类型下的心理测试的URL...");
        Elements elementList = document.getElementById("typelist").getElementsByTag("li");

        Map<String, String> ceshiTypeURLMap = new HashMap<>();
        for (Element element : elementList) {
            String typeName = element.getElementsByTag("i").text();
            String href = element.getElementsByTag("a").attr("href");
            ceshiTypeURLMap.put(typeName, href);
        }
        return  ceshiTypeURLMap;
    }
}
