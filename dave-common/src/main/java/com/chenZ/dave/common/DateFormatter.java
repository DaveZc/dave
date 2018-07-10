package com.chenZ.dave.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author chen.z
 * @date 2018/7/10
 */
public class DateFormatter {

    public DateFormatter() {
    }

    public String print(Date object, Locale locale) {
        return null;
    }

    public Date parse(String text, Locale locale) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = format.parse(text);
        } catch (Exception var6) {
            format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(text);
        }

        return date;
    }
}
