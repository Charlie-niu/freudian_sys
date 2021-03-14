package com.freudian.dao;

import com.freudian.bean.CeshiType;
import com.freudian.bean.QuestionChoice;

import java.util.List;

public interface QuestionChoiceMapper {

    public Integer insertQuestionChoice(QuestionChoice questionChoice);

    public List<QuestionChoice> selectAllQuestionChoices();

    public QuestionChoice selectQuestionChoiceByValue(QuestionChoice questionChoice);
}
