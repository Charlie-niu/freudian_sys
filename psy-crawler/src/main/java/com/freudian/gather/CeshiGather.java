package com.freudian.gather;


import com.freudian.utils.HttpConnectionUtil;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CeshiGather {

    /**
     * 爬取心理测试的内容
     *  https://www.psy525.cn/ceshi/ceshiRequest.html
     * @param ceshiRequest
     * @return
     * @throws IOException
     */
    public Document gatherCeshiContent(String ceshiRequest) throws IOException, NoSuchAlgorithmException, KeyManagementException {

        log.debug("正在获取 " + ceshiRequest + " 的心理测试数据..."); //substring(7)
        Connection connection = HttpConnectionUtil.connectionURL(ceshiRequest, HttpConnectionUtil.REQUEST_DEFAULT_CONTENTTYPE);
        // 建立连接
        Document document = connection.get();

        return document;
    }

    /**
     * 爬取心理测试模块的首页信息，首页信息包括所有的 csType 链接，以及 第 1-15 编号的 cslist
     *  https://www.psy525.cn/ceshi/index.html
     * @return
     * @throws IOException
     */
    public Document gatherCeshiIndex() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        log.debug("正在获取 心理测试首页 的数据...");
        Connection connection = HttpConnectionUtil.connectionURL(HttpConnectionUtil.CESHI_INDEX);
        Document document = connection.get();
        return document;
    }


    /**
     * 获取某个 csType 下的所有 页面 (包括分页)
     *  https://www.psy525.cn/ceshi/csTypeRequest.html
     * @param typeName
     * @param csTypeRequest
     * @return
     * @throws IOException
     */
    public List<Document> gatherAllCeshiListByType(String typeName, String csTypeRequest) throws IOException, NoSuchAlgorithmException, KeyManagementException {

        List<Document> documentList = new ArrayList<>();
        log.debug("正在获取 " + csTypeRequest + " 下的所有页面 数据...");

        Connection connection = HttpConnectionUtil.connectionURL(csTypeRequest);

        Document document = connection.get();
        documentList.add(document);

        // 如果有分页，则获取分页内容
        Element element = document.getElementById("page");
        if (element != null) {
            Elements elements = element.getElementsByTag("a");
            for (int i = 0; i < elements.size()-2; i++) {
                String pageRequest = elements.get(i).attr("href");
                log.debug("正在获取 " + typeName + " 类别下的 第"+ (i+2) +"页，（"+ pageRequest +"） 的心理测试...");

                HttpConnectionUtil.connectionURL(pageRequest);
                Document pageDcument = connection.get();
                documentList.add(pageDcument);
            }
        }
        log.debug(typeName + " 类别下，共获取到 " + documentList.size() + " 页数据");
        return documentList;
    }

/*

    // Disable
    public List<Document> gatherCeshiList() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        log.debug("正在获取心理测试的所有数据...");
        List<Document> documentList = new ArrayList<>();
        String csListRequest = "/sys/ajax.ashx";
        int startNum = 15;
        int endNum = startNum;

        while (true) {
            // 建立连接
            Connection connection = HttpConnectionUtil.connectionURL(csListRequest);

            connection.data("start", String.valueOf(startNum))
                    .data("classID", "-1")
                    .data("isv", "0");

            if (connection.post().getElementsByClass("cslist").select("li[class=no]").text().equals("暂无测试")) {
                log.debug("共获取到" + (endNum-15) + "条数据!!!!");
                return documentList;
            }

            int amount = connection.post().getElementsByClass("cslist").select("li[class=item]").size();
            endNum = startNum + amount;
            log.debug("正在获取 " + startNum + "-" + endNum + " 条的心理测试数据");

            documentList.add(connection.post());
            startNum += 15;
        }
    }
*/

    //ToDO
    public void gatherCeshiResult() {
    }

}
