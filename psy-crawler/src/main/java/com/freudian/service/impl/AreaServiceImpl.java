package com.freudian.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.freudian.bean.Area;
import com.freudian.dao.AreaMapper;
import com.freudian.gather.AreaGather;
import com.freudian.parser.AreaParser;
import com.freudian.service.IAreaService;
import com.freudian.utils.MyBatisUtil;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class AreaServiceImpl implements IAreaService {
    AreaGather areaGather = new AreaGather();
    AreaParser areaParser = new AreaParser();
    AreaMapper areaMapper = MyBatisUtil.openSession(true).getMapper(AreaMapper.class);

    @Override
    public void crawlAllAreaData() throws IOException {
        JSONArray areaJsonArray = areaGather.gatherAreaData();
        List<Area> areaList = areaParser.parserAllArea(areaJsonArray);
        System.out.println(areaList.size());
        areaMapper.insertAllArea(areaList);
    }

    @Override
    public List<String> crawlAreaURL() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        return areaParser.getFirstLevelAreaURL(areaGather.gatherAreaIndex());
    }

    /**
     * 从前往后遍历 areaNames 级联获取 areas 对象，看是否和 数组中的一一对应，
     * 如果一样，则结束遍历，如果不一一样，则获取一样的那几位。
     * @param areaNames
     * @return
     */
    @Override
    public List<Area> getDoctortAreaListByArray(String[] areaNames) {
        List<Area> areaList = new ArrayList<>();

        Area result = areaMapper.selectAreaCascadeChildrenByAreaName(areaNames[0]);
        if (result != null && areaNames.length >= 1 && result.getAreaName().equals(areaNames[0])) {
            areaList.add(new Area(result.getAreaID(), result.getAreaName(), result.getAreaLevel(),
                    result.getAreaCode(), result.getParentCode()));

            List<Area> seconedAreas = result.getChildAreas();
            if (areaNames.length >= 2 && seconedAreas != null ) {
                for (Area seconedArea : seconedAreas) {
                    if (seconedArea.getAreaName().equals(areaNames[1])) {
                        areaList.add(new Area(seconedArea.getAreaID(), seconedArea.getAreaName(), seconedArea.getAreaLevel(),
                                seconedArea.getAreaCode(), seconedArea.getParentCode()));

                        List<Area> thirdAreas = result.getChildAreas();
                        if (areaNames.length >= 3 && thirdAreas != null) {
                            for (Area thirdArea : thirdAreas) {
                                if (thirdArea.getAreaName().equals(areaNames[2])) {
                                    areaList.add(new Area(thirdArea.getAreaID(), thirdArea.getAreaName(), thirdArea.getAreaLevel(),
                                            thirdArea.getAreaCode(), thirdArea.getParentCode()));
                                }
                            }
                        }
                    }
                }
            }
        }
        return areaList;
    }
}
