package com.freudian.bean;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionChoice {
    private Integer questionChoiceID;
    private String questionChoiceName;
    private Integer questionChoiceValue;
    private LocalDateTime createDate;
    private LocalDateTime changeDate;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;  // 地址相等
//        if (o == null || getClass() != o.getClass()) return false; ////非空性：对于任意非空引用x，x.equals(null)应该返回false。
//
//        QuestionChoice other = (QuestionChoice) o;
//        //需要比较的字段相等，则这两个对象相等
//        if((this.getQuestionChoiceName()).equals(other.getQuestionChoiceName())
//                && (this.getQuestionChoiceValue()).equals(other.getQuestionChoiceValue())){
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = 17;
//        result = 31 * result + (questionChoiceName == null ? 0 : questionChoiceName.hashCode());
//        result = 31 * result + (questionChoiceValue == null ? 0 : questionChoiceValue.hashCode());
//        return result;
//    }

    @Override
    public String toString() {
        return "\n\t QuestionChoice{" +
                "\n\t\t questionChoiceID=" + questionChoiceID +
                ",\n\t\t questionChoiceName='" + questionChoiceName + '\'' +
                ",\n\t\t questionChoiceValue='" + questionChoiceValue + '\'' +
                ",\n\t\t createDate='" + createDate + '\'' +
                ",\n\t\t changeDate='" + changeDate + '\'' +
                "\n\t}";
    }
}
