package com.freudian.service.impl;

import com.freudian.bean.Ceshi;
import com.freudian.bean.CeshiType;
import com.freudian.bean.Question;
import com.freudian.bean.QuestionChoice;
import com.freudian.dao.CeshiMapper;
import com.freudian.dao.CeshiTypeMapper;
import com.freudian.dao.QuestionChoiceMapper;
import com.freudian.dao.QuestionMapper;
import com.freudian.gather.CeshiGather;
import com.freudian.parser.CeshiParser;
import com.freudian.service.ICeshiService;
import com.freudian.service.ICeshiTypeService;
import com.freudian.utils.FileStreamUtil;
import com.freudian.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CeshiServiceImpl implements ICeshiService {

    CeshiGather ceshiGather = new CeshiGather();
    CeshiParser ceshiParser = new CeshiParser();

    ICeshiTypeService ceshiTypeService = new CeshiTypeServiceImpl();

    static SqlSession sqlSession = MyBatisUtil.openSession();
    CeshiMapper ceshiMapper = sqlSession.getMapper(CeshiMapper.class);
    QuestionChoiceMapper questionChoiceMapper = sqlSession.getMapper(QuestionChoiceMapper.class);
    QuestionMapper questionMapper = sqlSession.getMapper(QuestionMapper.class);

    @Override
    public void crawlAllCeshiData() throws IOException, KeyManagementException, NoSuchAlgorithmException {
        List<Document> allCeshiList = new ArrayList<>();

        List<CeshiType> ceshiTypeList = ceshiTypeService.getAllCeshiType();
        for (int i = 0; i < ceshiTypeList.size(); i++) {
            int ceshiTypeID = ceshiTypeList.get(i).getCeshiTypeID();
            String ceshiTypeName = ceshiTypeList.get(i).getCeshiTypeName();
            String ceshiTypeRequest;
            switch (ceshiTypeName){
                case "健康" : ceshiTypeRequest = "/ceshi/jiankang.html"; break;
                case "能力" : ceshiTypeRequest = "/ceshi/nengli.html"; break;
                case "人际" : ceshiTypeRequest = "/ceshi/renji.html"; break;
                case "职场" : ceshiTypeRequest = "/ceshi/zhichang.html"; break;
                case "亲子" : ceshiTypeRequest = "/ceshi/qinzi.html"; break;
                case "婚恋" : ceshiTypeRequest = "/ceshi/hunlian.html"; break;
                case "人格" : ceshiTypeRequest = "/ceshi/xingge.html"; break;
                case "性心理" : ceshiTypeRequest = "/ceshi/xingxinli.html"; break;
                default:
                    throw new IllegalStateException("Unexpected value: " + ceshiTypeList.get(i).getCeshiTypeName());
            }
            List<Document> ceshiDocumentByTypeList = ceshiGather.gatherAllCeshiListByType(ceshiTypeName, ceshiTypeRequest);
            Map<String, String> ceshiURLMap = ceshiParser.getAllCeshiURL(ceshiDocumentByTypeList);
            for (Map.Entry<String, String> entry : ceshiURLMap.entrySet()) {
                Document ceshiDocument = ceshiGather.gatherCeshiContent(entry.getValue());
                Ceshi ceshi = ceshiParser.parseCeshiContent(ceshiDocument, ceshiTypeID);
                ceshiMapper.insertCeshi(ceshi);
                // 遍历 ceshi 下的所有 question
                List<Question>  questionList = ceshi.getQuestions();
                for (Question question : questionList) {
                    question.setCeshiID(ceshi.getCeshiID());
                    // 插入 question 数据
                    Integer questionID = questionMapper.insertQuestion(question);
                    // 遍历 question 下的所有 questionChoice
                    List<QuestionChoice> questionChoiceList = question.getQuestionChoices();
                    for (QuestionChoice questionChoice : questionChoiceList) {
                        QuestionChoice result = questionChoiceMapper.selectQuestionChoiceByValue(questionChoice);
                        // 如果 questionChoice 已经存在，则直接将数据库中的 questionChoice 和当前的 question 关联起来
                        // 如果 不存在，则插入新的 questionChoice， 并将其与 当前的 question 关联起来
                        if (result == null) {
                            Integer questionChoiceID = questionChoiceMapper.insertQuestionChoice(questionChoice);
                            questionMapper.insertQQChoice(questionID, questionChoiceID, LocalDateTime.now(), LocalDateTime.now());
                            sqlSession.commit();
                        } else {
                            questionMapper.insertQQChoice(questionID, result.getQuestionChoiceID(), LocalDateTime.now(), LocalDateTime.now());
                            sqlSession.commit();
                        }
                    }
                }

//                PrintWriter writer = FileOutputUtil.openOutputStream();
//                writer.println(ceshi.toString());
            }
        }
    }
}
