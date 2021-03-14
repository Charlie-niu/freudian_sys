package com.freudian.bean;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Specialty {
    private Integer specialtyID;
    private String specialtyName;
    private Integer specialtyLevel;
    private Integer specialtyCode;
    private Integer parentCode;
    private LocalDateTime createDate;
    private LocalDateTime changeDate;

    public Specialty(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public Specialty(Integer specialtyID, String specialtyName, Integer specialtyLevel, Integer specialtyCode, Integer parentCode) {
        this.specialtyID = specialtyID;
        this.specialtyName = specialtyName;
        this.specialtyLevel = specialtyLevel;
        this.specialtyCode = specialtyCode;
        this.parentCode = parentCode;
    }

}
