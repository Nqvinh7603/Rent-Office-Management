package site.rentofficevn.utils;

import java.util.Collection;

public class ValidateUtils {
    /*public static boolean isValid(Object obj) {
        if (obj == null) {
            return false;
        } else if (!obj.toString().equals("")) {
            return true;
        }
        return false;
    }

    public static int parseInteger(String input) {
        if (input != null) {
            return Integer.parseInt(input);
        } else {
            return Integer.valueOf(input);
        }
    }

    public static boolean checkNullEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }*/
    public static boolean isValid(Object obj){
        boolean isTrue = null != obj && !"".equals(obj.toString());
        if(isTrue){
            if(obj instanceof String){
                return true;
            }else if(obj instanceof Integer){
                return 0 <= Integer.parseInt(obj.toString());
            }else if(obj instanceof Long){
                return 0 <= Long.parseLong(obj.toString());
            }else if(obj instanceof Collection){
                return !((Collection<?>) obj).isEmpty();
            }
        }
        return  false;
    }
}
