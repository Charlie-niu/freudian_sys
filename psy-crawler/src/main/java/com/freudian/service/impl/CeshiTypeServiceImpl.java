package com.freudian.service.impl;

import com.freudian.bean.CeshiType;
import com.freudian.dao.CeshiTypeMapper;
import com.freudian.gather.CeshiGather;
import com.freudian.gather.CeshiTypeGather;
import com.freudian.parser.CeshiParser;
import com.freudian.parser.CeshiTypeParser;
import com.freudian.service.ICeshiTypeService;
import com.freudian.utils.MyBatisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Slf4j
public class CeshiTypeServiceImpl implements ICeshiTypeService {

    CeshiTypeParser ceshiTypeParser = new CeshiTypeParser();
    CeshiParser ceshiParse = new CeshiParser();

    CeshiTypeGather ceshiTypeGather = new CeshiTypeGather();
    CeshiGather ceshiGather = new CeshiGather();

    CeshiTypeMapper ceshiTypeMapper = MyBatisUtil.openSession(true)
                                        .getMapper(CeshiTypeMapper.class);

    @Override
    public void crawlAllCeshiTypeData() throws IOException, KeyManagementException, NoSuchAlgorithmException {

        Document ceshiIndexDocumnet =  ceshiGather.gatherCeshiIndex();
        Map<String, String> ceshiTypeMapURL = ceshiParse.getAllCeshiTypeURL(ceshiIndexDocumnet);
        for (Map.Entry<String, String> entry : ceshiTypeMapURL.entrySet()) {
            CeshiType ceshiType = ceshiTypeParser.parseCeshiTypeContent(
                                    ceshiTypeGather.gatherCeshiTypeIndex(entry.getKey(), entry.getValue()));

            ceshiTypeMapper.insertCeshiType(ceshiType);
        }
        //ceshiTypeMapper.insertAll(ceshiTypeList);
    }

    @Override
    public List<CeshiType> getAllCeshiType() {
        return ceshiTypeMapper.selectAllCeshiType();
    }
}
