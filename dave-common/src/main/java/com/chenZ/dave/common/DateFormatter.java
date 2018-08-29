package com.chenZ.dave.common;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author chen.z
 * @date 2018/7/10
 */
public class DateFormatter implements Formatter<Date> {

    public DateFormatter() {
    }

    @Override
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

    @Override
    public String print(Date object, Locale locale) {
        return null;
    }
}
