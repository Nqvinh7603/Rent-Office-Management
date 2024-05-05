package site.rentofficevn.utils;

public class NumberUtils {
    public static boolean isInteger(String value) {
        if(value == null){
            return false;
        }
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
