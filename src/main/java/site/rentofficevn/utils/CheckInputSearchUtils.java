package site.rentofficevn.utils;

public class CheckInputSearchUtils {
	public static boolean isEmptyOrNullString(String str) {
		return str == null || str.isEmpty();
	}

	public static boolean isNullInteger(Integer num) {
		return num == null;
	}

	public static boolean isNullLong(Long num) {
		return num == null;
	}
}
