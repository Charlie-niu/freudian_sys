package com.freudian.dao;

import com.freudian.bean.*;
import com.freudian.gather.DoctorGather;
import com.freudian.parser.DoctorParser;
import com.freudian.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DaoTest {
    static SqlSession sqlSession = null;
    @Test
    public void testCeshiTypeDao(){
        sqlSession = MyBatisUtil.openSession(true);
        CeshiTypeMapper ceshiTypeMapper = sqlSession.getMapper(CeshiTypeMapper.class);
        CeshiType ceshiType = new CeshiType(null, "testname", "testDesc", LocalDateTime.now(), LocalDateTime.now());
        ceshiTypeMapper.insertCeshiType(ceshiType);

    }

    @Test
    public void testQuestionChoiceDao(){
        sqlSession = MyBatisUtil.openSession(true);
        QuestionChoiceMapper questionChoiceMapper = sqlSession.getMapper(QuestionChoiceMapper.class);
        QuestionChoice questionChoice = new QuestionChoice(null, "questionChoiceName", 1, LocalDateTime.now(), LocalDateTime.now());
        Integer id = questionChoiceMapper.insertQuestionChoice(questionChoice);
        System.out.println("id: " + questionChoice.getQuestionChoiceID());

        List<QuestionChoice> questionChoiceList = questionChoiceMapper.selectAllQuestionChoices();
        for (QuestionChoice choice : questionChoiceList) {
            System.out.println(choice.toString());
        }
    }

    @Test
    public void testQuestionDao(){
        sqlSession = MyBatisUtil.openSession(true);
        QuestionMapper questionMapper = sqlSession.getMapper(QuestionMapper.class);
//        Question question = new Question(null, "questionName", LocalDateTime.now(), LocalDateTime.now(), null);
//        questionMapper.insertQuestion(question);
//        System.out.println("questionID: " + question.getQuestionID());
//
//        QuestionChoiceMapper questionChoiceMapper = sqlSession.getMapper(QuestionChoiceMapper.class);
//        QuestionChoice questionChoice = new QuestionChoice(null, "questionChoiceName", 1, LocalDateTime.now(), LocalDateTime.now());
//        questionChoiceMapper.insertQuestionChoice(questionChoice);
//        System.out.println("questionChoiceID: " + questionChoice.getQuestionChoiceID());
//
//        questionMapper.insertQQChoice(question.getQuestionID(), questionChoice.getQuestionChoiceID(), LocalDateTime.now(), LocalDateTime.now());

        List<Question> questionList = questionMapper.selectAllQuestionCascadeQuestionChoice();
        for (Question question : questionList) {
            System.out.println(question.toString());
        }
    }

    @Test
    public void testCeshiDao(){
        sqlSession = MyBatisUtil.openSession(true);
        CeshiMapper ceshiMapper = sqlSession.getMapper(CeshiMapper.class);
        CeshiTypeMapper ceshiTypeMapper = sqlSession.getMapper(CeshiTypeMapper.class);
        QuestionMapper questionMapper = sqlSession.getMapper(QuestionMapper.class);
        QuestionChoiceMapper questionChoiceMapper = sqlSession.getMapper(QuestionChoiceMapper.class);

        Integer ceshiTypeID = ceshiTypeMapper.selectAllCeshiType().get(1).getCeshiTypeID();

        Ceshi ceshi = new Ceshi(null, "TestceshiName", ceshiTypeID, null, "TestceshiDesc", "TestceshiPrice",
                "CeshiEStimatedTime",10, 12, LocalDateTime.now(), LocalDateTime.now(), null);

        ceshiMapper.insertCeshi(ceshi);
        Integer ceshiID = ceshi.getCeshiID();

        Question question1 = new Question(null, "questionName", ceshiID, LocalDateTime.now(), LocalDateTime.now(), null);
        Question question2 = new Question(null, "questionName2", ceshiID, LocalDateTime.now(), LocalDateTime.now(), null);

        List<Question> questionList = new ArrayList<>();
        questionList.add(question1);
        questionList.add(question2);

        for (Question question : questionList) {
            QuestionChoice questionChoice1 = new QuestionChoice(null, "questionChoiceName", 1, LocalDateTime.now(), LocalDateTime.now());
            QuestionChoice questionChoice2 = new QuestionChoice(null, "questionChoiceName2", 1, LocalDateTime.now(), LocalDateTime.now());
            QuestionChoice questionChoice3 = new QuestionChoice(null, "questionChoiceName3", 1, LocalDateTime.now(), LocalDateTime.now());
            List<QuestionChoice> questionChoiceList = new ArrayList<>();
            questionChoiceList.add(questionChoice1);
            questionChoiceList.add(questionChoice2);
            questionChoiceList.add(questionChoice3);

            questionMapper.insertQuestion(question);
            Integer questionID = question.getQuestionID();

            for (QuestionChoice questionChoice : questionChoiceList) {
                questionChoiceMapper.insertQuestionChoice(questionChoice);
                Integer questionChoiceID = questionChoice.getQuestionChoiceID();
                questionMapper.insertQQChoice(questionID, questionChoiceID, LocalDateTime.now(), LocalDateTime.now());
            }
        }
    }

    @Test
    public void testAreaDao_SelectAreaCascadeChildrenByAreaName() {
        sqlSession = MyBatisUtil.openSession(true);
        AreaMapper areaMapper = sqlSession.getMapper(AreaMapper.class);
        Area area = areaMapper.selectAreaCascadeChildrenByAreaName("重庆市");
        System.out.println(area);
    }

    @Test
    public void testInsertDoctor() {
        sqlSession = MyBatisUtil.openSession(true);
        DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);

        List<Area> areaList = new ArrayList<>();
        areaList.add(new Area(null, "北京市", 0, 110000, 0));
        areaList.add(new Area(null, "东城区", 1, 110101, 110000));

        List<Specialty> specialtyList = new ArrayList<>();
        specialtyList.add(new Specialty(null, "婚恋情感", 1, 100023, 100020));
        specialtyList.add(new Specialty(null, "恐婚", 1, 100085, 100020));

        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor( null, "doctorName1", "phoneNumber1", "doctorImgURL1", "男", "29",
                "level1", "levelID1", "description1", areaList, specialtyList, LocalDateTime.now(), LocalDateTime.now()));
        doctorList.add(new Doctor( null, "doctorName2", "phoneNumber2", "doctorImgURL2", "女", "19",
                "level2", "levelID2", "description2", areaList, specialtyList, LocalDateTime.now(), LocalDateTime.now()));

        doctorMapper.insertAllDoctor(doctorList);
    }
    /**
     * /d14596/bio.html
     */
    @Test
    public void testInsertDoctor_error() throws NoSuchAlgorithmException, IOException, KeyManagementException {
        sqlSession = MyBatisUtil.openSession(true);
        DoctorMapper doctorMapper = sqlSession.getMapper(DoctorMapper.class);

        List<Doctor> doctorList = new ArrayList<>();
        DoctorGather doctorGather =new DoctorGather();
        DoctorParser doctorParse = new DoctorParser();
        Doctor doctor = doctorParse.parseDoctorBio(doctorGather.gatherDoctorBio( "/d10247/bio.html"));
        System.out.println(doctor.toString());
        doctorList.add(doctor);
        doctorMapper.insertAllDoctor(doctorList);
    }
}
