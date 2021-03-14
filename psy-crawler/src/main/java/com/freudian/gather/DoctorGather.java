package com.freudian.gather;


import com.freudian.utils.CommonUtil;
import com.freudian.utils.HttpConnectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


@Slf4j
public class DoctorGather {

    /**
     * 根据地域爬取医生信息，获取所有的一级地区名
     *  https://www.psy525.cn/find/area.html
     * @return
     * @throws IOException
     */
    public Document gatherDoctorByArea(String request) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        return gatherDoctorByArea(request, HttpConnectionUtil.FIND_DOCTOR);
    }

    public Document gatherDoctorByArea(String areaRequest, String doctorRequest) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        String request = CommonUtil.getSubString(areaRequest, ".")  + doctorRequest;
        //log.debug("正在获取"+ request +"下的所有 医生 的数据...");

        Connection connection = HttpConnectionUtil.connectionURL(request);
        Document document = connection.get();
        return document;
    }

    public Document gatherDoctorBio(String doctorRequest) throws IOException, NoSuchAlgorithmException, KeyManagementException {

        log.debug("正在获取"+ doctorRequest +"下的 医生 的数据...");
        Connection connection = HttpConnectionUtil.connectionURL(doctorRequest);
        Document document = connection.get();
        return document;
    }

}
