package com.freudian.parser;


import com.freudian.bean.Ceshi;
import com.freudian.bean.CeshiType;
import com.freudian.bean.Question;
import com.freudian.bean.QuestionChoice;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CeshiParser {

    /**
     * 获取所有的 csType 的 URL
     * @param document
     * @return
     */
    public Map<String, String> getAllCeshiTypeURL(Document document) {
        log.debug("正在解析 所有心理测试类型 的URL...");
        Elements elementList = document.getElementById("typelist").getElementsByTag("li");

        Map<String, String> ceshiTypeURLMap = new HashMap<>();
        for (Element element : elementList) {
            String typeName = element.getElementsByTag("i").text();
            String href = element.getElementsByTag("a").attr("href");
            ceshiTypeURLMap.put(typeName, href);
        }
        return  ceshiTypeURLMap;
    }

    /**
     * 获取所有的 心理测试 的 URL
     * @param documentList
     * @return
     */
    public Map<String, String> getAllCeshiURL(List<Document> documentList) {
        log.debug("开始解析心理测试的URL...");
        Map<String, String> ceshiURLMap = new HashMap<>();
        for (Document document : documentList) {
            Elements lis = null;
            boolean bool = document.getElementsByClass("-1").size() == 0;
            if (!bool) {
                lis = document.getElementsByClass("-1").select("li[class=item]");
            } else {
                lis = document.getElementsByClass("cslist").select("li[class=item]");
            }
            for (Element li : lis) {
                String title = li.getElementsByTag("b").text();
                String href = li.getElementsByTag("a").attr("href");
                ceshiURLMap.put(title, href);
            }
        }
        return ceshiURLMap;
    }


    public Map<String, String> getCeshiURLByType(List<Document> documentList) {
        return  null;
    }


    /**
     * 解析测试内容，默认 cstypeID为 9
     * @param document
     * @return
     */
    public Ceshi parseCeshiContent(Document document) {
       return this.parseCeshiContent(document, 9);
    }

    /**
     * 解析测试内容，并封装该测试的 cstypeID
     * @param document
     * @param csTypeID
     * @return
     */
    public Ceshi parseCeshiContent(Document document, Integer csTypeID) {
        Ceshi ceshi;
        Question question;
        QuestionChoice questionChoice;
        List<Question> questionList = new ArrayList<Question>();
        List<QuestionChoice> questionChoiceList;

        log.debug("开始解析心理测试的内容...");

        Element ceshiInfoMetadata = document.getElementById("ceshi-page");
        Elements ceshiQuestionMetadatas = document.getElementsByClass("question");

        String ceshiNameMetadata = ceshiInfoMetadata.select(".title b").text();
        String viewAndDateMetadata = ceshiInfoMetadata.select(".title i").text();
        String imgURLMetadata = ceshiInfoMetadata.getElementsByTag("img").attr("src");
        String descMetadata = ceshiInfoMetadata.getElementsByClass("hdes").text();
        String questionAmountMetadata = ceshiInfoMetadata.getElementById("fun").getElementsByTag("em").get(0).text();
        String estimatedTimeMetadata = ceshiInfoMetadata.getElementById("fun").getElementsByTag("em").get(1).text();
        String priceMetadata = ceshiInfoMetadata.getElementById("fun").getElementsByTag("em").get(2).text();

        for (Element ceshiQuestionMetadata : ceshiQuestionMetadatas) {
            String questionNameMetadata = ceshiQuestionMetadata.getElementsByTag("legend").text();
            String questionChoiceString = ceshiQuestionMetadata.getElementsByTag("label").text();

            questionChoiceList =  new ArrayList<QuestionChoice>();

            String[] questionChoiceMetadatas = questionChoiceString.split(" ");
            for (int i = 0; i < questionChoiceMetadatas.length; i++) {
                String questionChoiceName = questionChoiceMetadatas[i];
                questionChoice = new QuestionChoice();
                questionChoice.setQuestionChoiceName(questionChoiceName);
                questionChoice.setQuestionChoiceValue(1);
                questionChoice.setCreateDate(LocalDateTime.now());
                questionChoice.setChangeDate(LocalDateTime.now());

                questionChoiceList.add(questionChoice);
            }

            question = new Question();
            question.setQuestionName(questionNameMetadata.substring(2));
            question.setCreateDate(LocalDateTime.now());
            question.setChangeDate(LocalDateTime.now());
            question.setQuestionChoices(questionChoiceList);

            questionList.add(question);
        }

        Integer viewAmount = Integer.valueOf(viewAndDateMetadata.substring(0, viewAndDateMetadata.indexOf("人")));
        Integer year = Integer.valueOf(viewAndDateMetadata.substring(viewAndDateMetadata.indexOf("于") +1, viewAndDateMetadata.indexOf("年")));
        Integer month = Integer.valueOf(viewAndDateMetadata.substring(viewAndDateMetadata.indexOf("年") +1, viewAndDateMetadata.indexOf("月")));
        Integer day = Integer.valueOf(viewAndDateMetadata.substring(viewAndDateMetadata.indexOf("月") +1, viewAndDateMetadata.indexOf("日")));
        LocalDateTime createDate = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.now());
        Integer questionAmount = Integer.valueOf(questionAmountMetadata.substring(0, questionAmountMetadata.indexOf("题")));

        ceshi = new Ceshi();
        ceshi.setCeshiName(ceshiNameMetadata);
        ceshi.setCeshiTypeID(csTypeID);
        ceshi.setCeshiImgURL(imgURLMetadata);
        ceshi.setCeshiDesc(descMetadata);
        ceshi.setCeshiPrice(priceMetadata);
        ceshi.setCeshiEstimatedTime(estimatedTimeMetadata);
        ceshi.setViewAmount(viewAmount);
        ceshi.setQuestionAmount(questionAmount);
        ceshi.setCreateDate(createDate);
        ceshi.setChangeDate(createDate);
        ceshi.setQuestions(questionList);
        log.debug("正在解析 -- "+ ceshi.getCeshiName() +" -- 的数据...");

        return ceshi;
    }
}
