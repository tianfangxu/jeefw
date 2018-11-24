package core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;


/**
 * 提供字符串操作的辅助方法
 * 
 * @author lishu
 * 
 */
public final class StringUnit {

	/**
	 * 返回对象是否是字符串
	 * 
	 * @param a
	 * @return
	 */
	public static boolean isString(Object a) {
		return String.class.isInstance(a);
	}

	/**
	 * 返回字符串对象是否为空或空字符串 如果为空则返回true
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null || obj.equals("null")) {
			return true;
		}
		return "".equals(obj);
	}

	/**
	 * 比较两个字符串对象是否相等
	 * 
	 * @param a
	 *            要比较的第一个字符串
	 * @param b
	 *            要比较的第二个字符串
	 * @param ignoreCase
	 *            是否忽略大小写
	 * @return
	 */
	public static boolean isEquals(Object a, Object b, boolean ignoreCase) {
		if (a != null && b != null) {
			if (isString(a) && isString(b)) {
				String str = (String) a;
				return ignoreCase ? str.equalsIgnoreCase((String) b) : str
						.equals(b);
			}
		} else if (a == null && b == null) {
			return true;
		}
		return false;
	}

	

	

	public static String convertSafeString(String text) {
		return text.replace("'", "\\'");
	}

	/**
	 * 获取两个日期字符串相差多少天
	 * 
	 * @param date1
	 *            ：第一个日期字符串
	 * @param date2
	 *            ：第二个日期字符串
	 * @param dateType2
	 *            ：第一个日子字符串的格式
	 * @return date2-date1的差
	 */
	public static long getDaysBetweenTwoDates(String date1, String date2,
			String dateType) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if ("yyyyMMddHHmmss".equals(dateType)) {
			sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		} else if ("yyyyMMddHHmm".equals(dateType)) {
			sdf = new SimpleDateFormat("yyyyMMddHHmm");
		} else if ("yyyy-MM-dd".equals(dateType)) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else if ("yyyy-MM-dd HH:mm:ss".equals(dateType)) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(date1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time1 = cal.getTimeInMillis();

		try {
			cal.setTime(sdf.parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return between_days;
	}

	/**
	 * 非数据库中定义的主键字段 作用于 其他特殊需要的guid 例如 多文件上传 文件的唯一标识等 获取guid
	 * 
	 * @return
	 */
	public static String getGuid() {
		UUID uuid = UUID.randomUUID();
		String raw = uuid.toString();
		StringBuffer sb = new StringBuffer(64);
		sb.append(raw.substring(0, 8));
		sb.append("-");
		sb.append(raw.substring(8, 12));
		sb.append("-");
		sb.append(raw.substring(12, 16));
		sb.append("-");
		sb.append(raw.substring(16, 20));
		sb.append("-");
		sb.append(raw.substring(20));
		return sb.toString().replace("-", "");
	}

	public static String getContent(String str) {
		if (StringUnit.isNullOrEmpty(str)) {
			return "&nbsp;";
		} else {
			return str;
		}
	}

	public static String getStringFormatMoney(String str) {
		if (!StringUnit.isNullOrEmpty(str)) {
			if (str.length() >= 16) {
				return str.substring(0, str.length() - 15) + ","
						+ str.substring(str.length() - 15, str.length() - 12)
						+ ","
						+ str.substring(str.length() - 12, str.length() - 9)
						+ ","
						+ str.substring(str.length() - 9, str.length() - 6)
						+ ","
						+ str.substring(str.length() - 6, str.length() - 3)
						+ "," + str.substring(str.length() - 3, str.length());
			} else if (str.length() >= 13) {
				return str.substring(0, str.length() - 12) + ","
						+ str.substring(str.length() - 12, str.length() - 9)
						+ ","
						+ str.substring(str.length() - 9, str.length() - 6)
						+ ","
						+ str.substring(str.length() - 6, str.length() - 3)
						+ "," + str.substring(str.length() - 3, str.length());
			} else if (str.length() >= 10) {
				return str.substring(0, str.length() - 9) + ","
						+ str.substring(str.length() - 9, str.length() - 6)
						+ ","
						+ str.substring(str.length() - 6, str.length() - 3)
						+ "," + str.substring(str.length() - 3, str.length());
			} else if (str.length() >= 7) {
				return str.substring(0, str.length() - 6) + ","
						+ str.substring(str.length() - 6, str.length() - 3)
						+ "," + str.substring(str.length() - 3, str.length());
			} else if (str.length() >= 4) {
				return str.substring(0, str.length() - 3) + ","
						+ str.substring(str.length() - 3, str.length());
			} else {
				return str;
			}
		}
		return str;
	}
	
	/**
	 * 根据指定的数据是否为空  来返回相关数值
	 * @param object
	 * @return
	 */
	public static String getParameterString(String object){
		if(StringUnit.isNullOrEmpty(object)){
			return  "";
		}
		return object;
	}
	
	/**
	 *去除含有非数字字符串 
	 */
	public static String getNumberString(String arg){	
		String tmpStr="";
		if(arg.length()>0){
			for(int i=0;i<arg.length();i++){
				String tmp=""+arg.charAt(i);
				if((tmp).matches("[0-9]")){
					tmpStr+=tmp;
				}
			}
		}
		return tmpStr;
	}
}
