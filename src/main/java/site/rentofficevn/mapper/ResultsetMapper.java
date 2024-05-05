package site.rentofficevn.mapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import site.rentofficevn.annotation.Column;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
public class ResultsetMapper<T> {
	public List<T> maprow(ResultSet rs, Class<T> tClass) {
		List<T> results = new ArrayList<>();
		try {
			Field[] fields = tClass.getDeclaredFields();
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			while (rs.next()) {
				T object = tClass.newInstance();
				for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
					String columnName = resultSetMetaData.getColumnName(i + 1);
					Object columnValue = rs.getObject(i + 1);
					for (Field field : fields) {
						if (field.isAnnotationPresent(Column.class)) {
							Column column = field.getAnnotation(Column.class);
							if (column.name().equals(columnName) && columnValue != null) {
								BeanUtils.setProperty(object, field.getName(), columnValue);
								break;
							}
						}
					}
					// VÒNG LẶP Ở TRÊN THÌ NÓ CHỈ CHẠY XONG CÁC CÁI FIELD THUỘC TÍNH CỦA BẢNG
					// CÁC DÒNG Ở DƯỚI KIỂM TRA XEM CÁC FIELD TRÊN CÓ PARENT KO
					Class<?> parenttClass = tClass.getSuperclass();
					while (parenttClass != null) {
						for (Field field : parenttClass.getDeclaredFields()) {
							if (field.isAnnotationPresent(Column.class)) {
								Column column = field.getAnnotation(Column.class);
								if (column.name().equals(columnName) && columnValue != null) {
									BeanUtils.setProperty(object, field.getName(), columnValue);
									break;
								}
							}
						}
						parenttClass = parenttClass.getSuperclass();
					}
				}
				results.add(object);
			}
			return results;
		} catch (SQLException | IllegalAccessException | InstantiationException | InvocationTargetException ex) {

		}
		return null;
	}
}