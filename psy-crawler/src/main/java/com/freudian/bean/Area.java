package com.freudian.bean;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    private Integer areaID;
    private String areaName;
    private Integer areaLevel;
    private Integer areaCode;
    private Integer parentCode;
    private LocalDateTime createDate;
    private LocalDateTime changeDate;

    private List<Area> childAreas;

    public Area(String areaName) {
        this.areaName = areaName;
    }

    public Area(Integer areaID, String areaName, Integer areaLevel, Integer areaCode, Integer parentCode) {
        this.areaID = areaID;
        this.areaName = areaName;
        this.areaLevel = areaLevel;
        this.areaCode = areaCode;
        this.parentCode = parentCode;
    }

    @Override
    public String toString() {
        return "\n\t Area{" +
                "\n\t\t areaID=" + areaID +
                ",\n\t\t areaName='" + areaName + '\'' +
                ",\n\t\t areaLevel='" + areaLevel + '\'' +
                ",\n\t\t areaCode='" + areaCode + '\'' +
                ",\n\t\t parentCode='" + parentCode + '\'' +
                ",\n\t\t createDate=" + createDate +
                ",\n\t\t changeDate=" + changeDate +
                ",\n\t\t childAreas=" + childAreas +
                "\n\t}";
    }

}
