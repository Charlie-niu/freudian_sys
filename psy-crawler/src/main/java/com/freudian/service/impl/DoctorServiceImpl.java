package com.freudian.service.impl;

import com.freudian.bean.Doctor;
import com.freudian.dao.AreaMapper;
import com.freudian.dao.DoctorMapper;
import com.freudian.gather.DoctorGather;
import com.freudian.parser.DoctorParser;
import com.freudian.service.IAreaService;
import com.freudian.service.IDoctorService;
import com.freudian.utils.MyBatisUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DoctorServiceImpl implements IDoctorService {

    IAreaService areaService = new AreaServiceImpl();
    DoctorGather doctorGather = new DoctorGather();
    DoctorParser doctorParser = new DoctorParser();
    DoctorMapper doctorMapper = MyBatisUtil.openSession(true).getMapper(DoctorMapper.class);

    @Override
    public void crawlAllDoctorData() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        List<String> doctorURLList = new ArrayList<>();
        List<String> areaURLList = areaService.crawlAreaURL();
        for (String areaRequest : areaURLList) {
            if(!areaRequest.equals("/HW525.html")) {
                Document doctorDocumnet = doctorGather.gatherDoctorByArea(areaRequest);
                doctorURLList.addAll(doctorParser.getAllDoctorURLList(doctorDocumnet));
                Elements pageElements = doctorDocumnet.getElementById("page").getElementsByTag("a");
                if (pageElements.size() > 0) {
                    for (int i = 0; i < pageElements.size()-1; i++) {
                        String pageRequest = "/" + pageElements.get(i).attr("href");
                        doctorURLList.addAll(doctorParser.getAllDoctorURLList(doctorGather.gatherDoctorByArea(areaRequest, pageRequest)));
                    }
                }
            }
        }
        log.debug("一共 获取到 " + doctorURLList.size() + " 条 request !!!!");
        List<Doctor> doctorList = new ArrayList<>();
        for (String doctorRequest : doctorURLList) {
            Doctor doctor = doctorParser.parseDoctorBio(doctorGather.gatherDoctorBio(doctorRequest));
            doctorList.add(doctor);
            if (doctorList.size()%100 == 0 || doctorList.size() == doctorURLList.size()) {
                log.debug("正在插入 第 " + (doctorList.size()-100) + " - " + doctorList.size() + "条 的数据");
                doctorMapper.insertAllDoctor(doctorList.subList(doctorList.size()-100, doctorList.size()));
            }
        }
        log.debug("doctor 数据爬取完毕 !!!!! ");
    }
}
