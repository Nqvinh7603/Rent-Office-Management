package site.rentofficevn.utils;

import java.util.Map;

public class MapUtils {
    public static <T> T getObject(Map<String, Object> maps, String key, Class<T> tClass) {
        try {
            //getOrDefault nếu key không tồn tại thì trả về null
            Object obj = maps.getOrDefault(key, null);
            if (obj != null) {
                if (tClass.getTypeName().equals("java.lang.Long")) {
                    obj = obj != null ? Long.valueOf(obj.toString()) : null;
                } else if (tClass.getTypeName().equals("java.lang.Integer")) {
                    obj = obj != null ? Integer.valueOf(obj.toString()) : null;
                }else if(tClass.getTypeName().equals("java.lang.String")) {
                    obj = obj.toString();
                }
                return tClass.cast(obj);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
