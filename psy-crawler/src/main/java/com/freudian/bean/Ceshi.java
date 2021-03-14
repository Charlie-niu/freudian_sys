package com.freudian.bean;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ceshi {
    private Integer ceshiID;
    private String ceshiName;
    private Integer ceshiTypeID;
    private String ceshiImgURL;
    private String ceshiDesc;
    private String ceshiPrice;
    private String ceshiEstimatedTime;
    private Integer viewAmount;
    private Integer questionAmount;
    private LocalDateTime createDate;
    private LocalDateTime changeDate;
    private List<Question> questions;

    @Override
    public String toString() {
        return "\n\t Ceshi{" +
                "\n\t\t ceshiID=" + ceshiID +
                ",\n\t\t ceshiName='" + ceshiName + '\'' +
                ",\n\t\t ceshiTypeID='" + ceshiTypeID + '\'' +
                ",\n\t\t imgURL='" + ceshiImgURL + '\'' +
                ",\n\t\t desc='" + ceshiDesc + '\'' +
                ",\n\t\t price='" + ceshiPrice + '\'' +
                ",\n\t\t estimatedTime='" + ceshiEstimatedTime + '\'' +
                ",\n\t\t viewAmount=" + viewAmount +
                ",\n\t\t questionAmount=" + questionAmount +
                ",\n\t\t createDate=" + createDate +
                ",\n\t\t changeDate=" + changeDate +
                ",\n\t\t questions=" + questions +
                "\n\t}";
    }
}
