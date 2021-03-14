package com.freudian.utils;

import com.freudian.bean.Area;
import com.freudian.bean.Specialty;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
    public static String getSubString(String text, String symbol) {
        return text.substring(0, text.indexOf(symbol));
    }

    public static <T> List<T> getListByString(String[] textArray, T t) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < textArray.length; i++) {
            if (t instanceof Area)
                list.add((T) new Area(textArray[i]));
            if (t instanceof Specialty)
                list.add((T)new Specialty(textArray[i]));
        }
        return list;
    }

    public static Object setValue(Object object){
        if (object == null || object.equals(""))
            return null;
        else if (object instanceof List && ((List)object).size() == 0 )
            return null;
        else
            return object;
    }

}
