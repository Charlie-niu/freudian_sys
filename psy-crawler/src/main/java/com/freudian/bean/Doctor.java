package com.freudian.bean;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private Integer doctorID;
    private String doctorName;
    private String phoneNumber;
    private String doctorImgURL;
    private String gender;
    private String age;
    private String levelText;
    private String levelID;
    private String description;

    private List<Area> areaList;
    private List<Specialty> specialtyList;

    private LocalDateTime createDate;
    private LocalDateTime changeDate;

}
