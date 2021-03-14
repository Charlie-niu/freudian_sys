package com.freudian.parser;

import com.freudian.bean.Specialty;
import com.freudian.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
public class SpecialtyParser {

    /**
     * 获取所有的 specialty 对象
     * @param document
     * @return
     */
    public List<Specialty> parseAllSpecialty(Document document) {
        log.debug("开始解析所有 专长 ...");
        List<Specialty> specialtyList = new ArrayList<>();

        Elements elementList = document.getElementById("afind").getElementsByTag("p");
        Integer specialtyCode = 100000;
        Integer parentSpecialtyCode = specialtyCode;
        for (Element element : elementList) {
            if (element.getElementById("afind-spe-more") !=  null) {
                return specialtyList;
            }

            Elements aELE = element.getElementsByClass("type");
            Elements bELE = element.getElementsByClass("i-city");

            parentSpecialtyCode += (int)(Math.random()*100);
            specialtyCode = parentSpecialtyCode;
            specialtyList.add(new Specialty(null, aELE.get(0).text(),
                    0, specialtyCode, parentSpecialtyCode, LocalDateTime.now(), LocalDateTime.now()));

            for (Element ele : bELE) {
                specialtyCode += (int)(Math.random()*10);
                specialtyList.add(new Specialty(null, CommonUtil.getSubString(ele.text(), "("),
                        1, specialtyCode, parentSpecialtyCode, LocalDateTime.now(), LocalDateTime.now()));
            }
        }

        return specialtyList;
    }

    /**.。。。。
     * 获取所有的 specialty 的 URL
     * @param document
     * @return
     */
    public Map<String, String> getAllSpecialtyURL(Document document) {
        log.debug("开始解析心理专长的URL...");
        Map<String, String> specialtyURLMap = new HashMap<>();
        Elements elementList = document.getElementById("afind").getElementsByTag("p");

        for (Element element : elementList) {
            if (element.getElementById("afind-spe-more") !=  null) {
                return specialtyURLMap;
            }
            Elements eles = element.getElementsByClass("i-city");
            for (Element ele : eles) {
                specialtyURLMap.put(CommonUtil.getSubString(ele.text(), "("), ele.attr("href"));
            }
        }
        return specialtyURLMap;
    }
}
