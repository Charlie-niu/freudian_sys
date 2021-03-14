package com.freudian.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class FileStreamUtil {
    private static PrintWriter printWriter;
    private static Reader reader;

    private static String loggerURL = "src/main/resources/crawlerCeshilogger.txt";

    public static PrintWriter openWriterStream() throws FileNotFoundException {
        if (printWriter == null) {
            printWriter = new PrintWriter( new OutputStreamWriter(
                            new FileOutputStream(loggerURL), StandardCharsets.UTF_8), true);
        }
        return printWriter;
    }

    public static Reader openReaderStream(String fileName) throws FileNotFoundException {
        if (reader == null) {
            reader = new InputStreamReader(
                    new FileInputStream(new File(fileName)),StandardCharsets.UTF_8);
        }
        return reader;
    }

    public static void closeWriterStream() throws FileNotFoundException {
        if (printWriter != null) printWriter.close();
    }

    public static void closeReaderStream() throws IOException {
        if (reader != null) reader.close();
    }


    //读取json文件
    public static String readJsonFile(String fileName) throws IOException {
        String jsonStr = "";
        openReaderStream(fileName);

        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        closeReaderStream();
        jsonStr = sb.toString();
        return jsonStr;
    }

}
