package site.rentofficevn.utils;

import java.util.Collection;

public class ValidateUtils {
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
