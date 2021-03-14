package com.freudian.bean;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    private Integer questionID;
    private String questionName;
    private Integer ceshiID;
    private LocalDateTime createDate;
    private LocalDateTime changeDate;
    private List<QuestionChoice> questionChoices;

    @Override
    public String toString() {
        return "\n\t Question{" +
                "\n\t\t questionID=" + questionID +
                ",\n\t\t questionName='" + questionName + '\'' +
                ",\n\t\t ceshiID='" + ceshiID + '\'' +
                ",\n\t\t createDate='" + createDate + '\'' +
                ",\n\t\t changeDate='" + changeDate + '\'' +
                ",\n\t\t questionChoices='" + questionChoices+ '\'' +
                "\n\t}";
    }
}
