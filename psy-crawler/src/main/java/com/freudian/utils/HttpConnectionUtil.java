package com.freudian.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.net.ssl.*;
import java.net.URL;
import java.security.KeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class HttpConnectionUtil {

    public static final String HTTPSSL = "https://";
    public static final String HOST = "www.psy525.cn";
    public static final String CESHI_INDEX = "/ceshi/index.html";
    public static final String FIND_DOCTOR = "/doctor.html";
    public static final String FIND_BY_AREA = "/find/area.html";
    public static final String FIND_BY_SPECIALTY = "/find/specialty.html";


    // 设置 accept, accept-Language 和 accept-Encoding
    public static final String REQUEST_ACCEPT = "text/html, */*; q=0.01";;
    public static final String REQUEST_ACCEPT_LANGUAGE = "zh-CN,zh;q=0.9";
    public static final String REQUEST_ACCEPT_ENCODING = "gzip, deflate, br";
    public static final String REQUEST_CONNECTION = "keep-alive";
    public static final String REQUEST_X_REQUEST_WITH = "XMLHttpRequest";
    public static final String REQUEST_DEFAULT_CONTENTTYPE = "text/html; charset=utf-8";
    public static final String REQUEST_FORM_CONTENTTYPE = "application/x-www-form-urlencoded; charset=UTF-8";

    // 设置 User-Agent 库，读者根据需求添加更多 User-Agent
    private static final String[] REQUEST_USER_AGENT_STRING = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36",
    };

    private static int trustEveryoneLabel = 0;
    private static Map<String, String> headers = null;

    public static Connection connectionURL(String request) throws KeyManagementException, NoSuchAlgorithmException {
       return connectionURL(request,REQUEST_DEFAULT_CONTENTTYPE);
    }

    public static Connection connectionURL(String request, String contentType) throws KeyManagementException, NoSuchAlgorithmException {
        trustEveryone();
        return Jsoup.connect(HTTPSSL + HOST + request)
                .headers(initHeader(contentType))
                .timeout(500000);
    }

    public static Map<String, String> initHeader(String contentType) {
        headers = new HashMap<>();
        headers.put("Host", HOST);
        headers.put("Accept", REQUEST_ACCEPT);
        headers.put("Accept-Language", REQUEST_ACCEPT_LANGUAGE);
        headers.put("Accept-Encoding", REQUEST_ACCEPT_ENCODING);
        headers.put("Connection", REQUEST_CONNECTION);
        headers.put("Content-Type", contentType);
        headers.put("User-Agent", getUserAgentList().get(new Random().nextInt(getUserAgentSize())));
        headers.put("X-Requested-With", REQUEST_X_REQUEST_WITH);
        headers.put("Origin", "https://www.psy525.cn");
        //headers.put("Referer", "https://www.psy525.cn/ceshi/index.html");

        return headers;
    }

    public static List<String> getUserAgentList() {
        return Arrays.asList(REQUEST_USER_AGENT_STRING);
    }

    public static int getUserAgentSize(){
        return getUserAgentList().size();
    }

    /**
     * 信任任何站点，实现https页面的正常访问
     */
    public static void trustEveryone() throws NoSuchAlgorithmException, KeyManagementException {
        if (trustEveryoneLabel == 1) {
            return;
        } else {
            trustEveryoneLabel = 1;
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[] { new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        }
    }
}
