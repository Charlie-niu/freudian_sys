package com.freudian.bean;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CeshiType {
    private Integer ceshiTypeID;
    private String ceshiTypeName;
    private String ceshiTypeDesc;
    private LocalDateTime createDate;
    private LocalDateTime changeDate;

    @Override
    public String toString() {
        return "\n\t CeshiType{" +
                "\n\t\t ceshiTypeID=" + ceshiTypeID +
                ",\n\t\t ceshiTypeName='" + ceshiTypeName + '\'' +
                ",\n\t\t ceshiTypeDesc='" + ceshiTypeDesc + '\'' +
                ",\n\t\t createDate=" + createDate +
                ",\n\t\t changeDate=" + changeDate +
                "\n\t }";
    }
}

