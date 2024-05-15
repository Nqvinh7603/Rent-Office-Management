package site.rentofficevn.utils;
import java.util.*;
public class QueryBuilderUtils {
    public static String withLike(String column, String value){
        return (!ValidateUtils.isValid(value)) ? "" : String.format("%nAND lower(%s) LIKE %s", column,"'%" + value.toLowerCase() + "%'");
    }
    public static String withOperator(String column, Object value, String operator){
        if(!ValidateUtils.isValid(value))  {
            return "";
        }
        return (value instanceof  String) ? String.format("%nAND %s %s '%s'", column, operator, value) : String.format("%nAND %s %s %s", column, operator,value);
    }
    public static String withBetween(String column, Object from, Object to) {
        if (null == from || null == to) {
            return "";
        } else {
            if (null == from) {
                from = 0;
            }
            if (null == to) {
                if (from instanceof Integer) {
                    to = Integer.MAX_VALUE;
                } else if (from instanceof Double) {
                    to = Double.MAX_VALUE;
                }
            }
            return String.format("%nAND %s BETWEEN %s AND %s", column, from, to);
        }
    }
    public static <T> String withIn(String column, List<T> values) {
        List<Object> convertedValues = new ArrayList<>();
        String joinValues;
        if(values.get(0) instanceof String){
            for(Object item : values){
                StringBuilder convertedItem = new StringBuilder();
                convertedItem.append("'").append(item.toString()).append("'");
                convertedValues.add(convertedItem.toString());
            }
            joinValues = String.join(",",(List<String>) (Object) convertedValues);
        }else{
            String listString = values.toString();
            joinValues = listString.substring(1, listString.length() - 1);
        }
        return String.format("%nAND %s IN (%s)", column, joinValues);
    }
    public static <T> String withOrAndLike(String column, List<T> values) {
        List<Object> convertedValues = new ArrayList<>();
        String joinValues;
        if(values.get(0) instanceof String){
            for(Object item : values){
                StringBuilder convertedItem = new StringBuilder();
                convertedItem.append(" LIKE lower('%").append(item.toString()).append("%')");
                convertedValues.add(convertedItem.toString());
            }
            joinValues = String.join(" OR ",(List<String>) (Object) convertedValues);
        }else{
            String listString = values.toString();
            joinValues = listString.substring(1, listString.length() - 1);
        }
        return String.format("%nAND (%s)", column, joinValues);
    }
}
