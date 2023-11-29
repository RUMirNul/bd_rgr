package edu.ansvistunov;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private static final Pattern DATA_PATTERN = Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{4}$");
    public static String dateFormat(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    public static Date stringToDate(String date) throws Exception {
        Matcher matcher = DATA_PATTERN.matcher(date);
        try {
            return  new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException e) {
            throw new Exception("Не правильная дата или формат. Требуемый формат: дд.мм.гггг");
        }
    }

    public static Date sqlDateToDate(java.sql.Date date) {
        return new Date(date.getTime());
    }
}
