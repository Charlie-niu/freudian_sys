package com.freudian.parser;

import com.freudian.bean.Area;
import com.freudian.bean.Doctor;
import com.freudian.bean.Specialty;
import com.freudian.service.IAreaService;
import com.freudian.service.ISpecialtyService;
import com.freudian.service.impl.AreaServiceImpl;
import com.freudian.service.impl.SpecialtyServiceImpl;
import com.freudian.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DoctorParser {
    IAreaService areaService = new AreaServiceImpl();
    ISpecialtyService specialtyService = new SpecialtyServiceImpl();
    /**
     *
     * @param document
     * @return
     */
    public List<String> getAllDoctorURLList(Document document)  {
        List<String> requestList = new ArrayList<>();
        Elements elements = document.getElementsByClass("dr-list").select("span[class=txt]");
        for (Element element : elements) {
            String request = element.select("a").get(1).attr("href");
            if (!request.equals("javascript:void(0);")) {
                requestList.add(request);
            }
        }
        return requestList;
    }

    /**
     * @param documnet
     * @return
     */
    public Doctor parseDoctorBio(Document documnet) {
        Doctor doctor = new Doctor();
        String imgURL = (String) CommonUtil.setValue(
                                documnet.getElementsByClass("dr-topinfo")
                                        .select("img").attr("src"));
        String phoneNumber =  (String) CommonUtil.setValue(
                                documnet.getElementsByClass("dr-topinfo")
                                        .select(".dask em").text());
        doctor.setDoctorImgURL(imgURL);
        doctor.setPhoneNumber(phoneNumber);
        Elements elements = documnet.getElementById("biolist").getElementsByTag("li");
        for (Element element : elements) {
            if ((element.attr("class").equals("item"))){
                String doctorMetadata = element.getElementsByTag("em").text();
                switch (element.getElementsByTag("i").text()) {
                    case "咨询师" : doctor.setDoctorName((String) CommonUtil.setValue(doctorMetadata)); break;
                    case "性别" : doctor.setGender((String) CommonUtil.setValue(doctorMetadata)); break;
                    case "年龄" : doctor.setAge((String) CommonUtil.setValue(doctorMetadata)); break;
                    case "专业资质" : doctor.setLevelText((String) CommonUtil.setValue(doctorMetadata)); break;
                    case "资质证号" : doctor.setLevelID((String) CommonUtil.setValue(doctorMetadata)); break;
                    case "所在地区" :
                            String[] areaArray = doctorMetadata.split(" ");
                            doctor.setAreaList((List<Area>) CommonUtil.setValue(areaService.getDoctortAreaListByArray(areaArray)));
                            break;
                }
            }
            if ((element.attr("class").equals("zclist"))){
                Elements eles = element.getElementsByTag("a");
                List<String> specialtyNameList = new ArrayList<>();
                for (Element ele : eles) {
                    specialtyNameList.add(ele.text());
                }
                if (specialtyNameList.size() == 0) {
                    doctor.setSpecialtyList(null);
                } else {
                    doctor.setSpecialtyList(
                            (List<Specialty>) CommonUtil.setValue(specialtyService.getSpecialtiesByNameList(specialtyNameList)));
                }
            }
            if ((element.attr("class").equals("des"))){
                doctor.setDescription((String) CommonUtil.setValue(element.text()));
            }
        }
        doctor.setCreateDate(LocalDateTime.now());
        doctor.setChangeDate(LocalDateTime.now());
        return doctor;
    }


}
