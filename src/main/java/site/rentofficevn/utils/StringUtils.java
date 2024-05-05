package site.rentofficevn.utils;

public class StringUtils {
    public static boolean isNullOrEmpty(Object obj) {
        if((obj != null) && (obj != "")) {
            return false;
        }
        return true;
    }
}