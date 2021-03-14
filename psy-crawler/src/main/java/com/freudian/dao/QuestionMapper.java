package com.freudian.dao;

import com.freudian.bean.Question;
import com.freudian.bean.QuestionChoice;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface  QuestionMapper {

    public Integer insertQuestion(Question question);

    public void insertQQChoice(@Param("questionID") Integer questionID,
                               @Param("questionChoiceID") Integer questionChoiceID,
                               @Param("createDate") LocalDateTime createDate,
                               @Param("changeDate") LocalDateTime changeDate);

    public List<Question> selectAllQuestionCascadeQuestionChoice();
}
