package com.selsoft.commonutility.utils;

 import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class CommonUtilityUtils {
	
	public static final SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getCurrentUTCTimeAsSqlTimestampString() {
		return getCurrentUTCTimeAsSqlTimestamp().toString();
	}
	
	public static Timestamp getCurrentUTCTimeAsSqlTimestamp() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		return new Timestamp(System.currentTimeMillis());
	}

}
