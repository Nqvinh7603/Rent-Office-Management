package site.rentofficevn.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String convertDateToString(Date date){
        if(date == null){
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        return dateFormat.format(date);
    }
    public static Date convertStringToDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            // Xử lý ngoại lệ nếu không thể chuyển đổi chuỗi thành ngày
            e.printStackTrace();
            return null;
        }
    }
}