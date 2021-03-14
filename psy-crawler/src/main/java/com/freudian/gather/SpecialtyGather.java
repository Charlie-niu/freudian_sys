package com.freudian.gather;

import com.freudian.utils.HttpConnectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class SpecialtyGather {

    public Document gatherSpecialtyIndex()
            throws IOException, NoSuchAlgorithmException, KeyManagementException {

        log.debug("开始获取 Specialty 的所有数据");
        Connection connection = HttpConnectionUtil.connectionURL(HttpConnectionUtil.FIND_BY_SPECIALTY);
        Document document = connection.get();
        return document;
    }
}
