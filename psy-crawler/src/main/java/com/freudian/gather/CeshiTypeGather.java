package com.freudian.gather;

import com.freudian.utils.HttpConnectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class CeshiTypeGather {

    /**
     * 获取某个 csType 的数据
     *  https://www.psy525.cn/ceshi/csTypeRequest.html
     * @param typeName
     * @param csTypeRequest
     * @return
     * @throws IOException
     */
    public Document gatherCeshiTypeIndex(String typeName, String csTypeRequest)
                            throws IOException, NoSuchAlgorithmException, KeyManagementException {

        log.debug("开始获取 " + typeName + " 的首页数据");
        Connection connection = HttpConnectionUtil.connectionURL(csTypeRequest);
        Document document = connection.get();
        return document;
    }
}
