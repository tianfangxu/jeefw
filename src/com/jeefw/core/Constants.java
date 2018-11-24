package com.jeefw.core;

/**
 * <p>
 * 共通定数类
 * </p>
 *
 * @version 1.0
 * @author MBP : 王亮 <br />
 */
public final class Constants {

	// ========================================================= //
	// ************************* 处理状态 *********************** //
	// ========================================================= //

	/** 处理状态：成功 */
	public static final boolean STATUS_SUCCESS = true;

	/** 处理状态：失败 */
	public static final boolean STATUS_FAILURE = false;

	/** 处理状态：警告 */
	public static final String STATUS_WARNING = "warning";

	// ========================================================= //
	// ****************** 常用数字，英字，字符字 ****************** //
	// ========================================================= //

	/** 文字列数字:-1 */
	public static final String STR_NUM_MINUS_ONE = "-1";

	/** 文字列数字:0 */
	public static final String STR_NUM_ZERO = "0";

	/** 文字列数字:1 */
	public static final String STR_NUM_ONE = "1";

	/** 文字列数字:2 */
	public static final String STR_NUM_TWO = "2";

	/** 文字列数字:3 */
	public static final String STR_NUM_THREE = "3";

	/** 文字列数字:4 */
	public static final String STR_NUM_FOUR = "4";

	/** 文字列数字:5 */
	public static final String STR_NUM_FIVE = "5";

	/** 文字列数字:6 */
	public static final String STR_NUM_SIX = "6";

	/** 文字列数字:7 */
	public static final String STR_NUM_SEVEN = "7";

	/** 文字列数字:8 */
	public static final String STR_NUM_EIGHT = "8";

	/** 文字列数字:9 */
	public static final String STR_NUM_NINE = "9";

	/** 文字列数字:10 */
	public static final String STR_NUM_TEN = "10";

	/** 文字列数字:11 */
	public static final String STR_NUM_ELEVEN = "11";

	/** 文字列数字:12 */
	public static final String STR_NUM_TWELVE = "12";

	/** 文字列数字:100 */
	public static final String STR_NUM_HUNDRED = "100";

	/** 文字列:A */
	public static final String CODE_A = "A";

	/** 文字列:B */
	public static final String CODE_B = "B";

	/** 文字列:C */
	public static final String CODE_C = "C";

	/** 文字列:D */
	public static final String CODE_D = "D";

	/** 文字列:E */
	public static final String CODE_E = "E";

	/** 文字列:F */
	public static final String CODE_F = "F";

	/** 文字列:G */
	public static final String CODE_G = "G";

	/** 文字列:H */
	public static final String CODE_H = "H";

	/** 文字列:I */
	public static final String CODE_I = "I";

	/** 文字列:J */
	public static final String CODE_J = "J";

	/** 文字列:K */
	public static final String CODE_K = "K";

	/** 文字列:L */
	public static final String CODE_L = "L";

	/** 文字列:M */
	public static final String CODE_M = "M";

	/** 文字列:N */
	public static final String CODE_N = "N";

	/** 文字列:O */
	public static final String CODE_O = "O";

	/** 文字列:P */
	public static final String CODE_P = "P";

	/** 文字列:Q */
	public static final String CODE_Q = "Q";

	/** 文字列:R */
	public static final String CODE_R = "R";

	/** 文字列:S */
	public static final String CODE_S = "S";

	/** 文字列:T */
	public static final String CODE_T = "T";

	/** 文字列:U */
	public static final String CODE_U = "U";

	/** 文字列:V */
	public static final String CODE_V = "V";

	/** 文字列:W */
	public static final String CODE_W = "W";

	/** 文字列:X */
	public static final String CODE_X = "X";

	/** 文字列:Y */
	public static final String CODE_Y = "Y";

	/** 文字列:Z */
	public static final String CODE_Z = "Z";

	/** 空文字列 */
	public static final String BLANK = "";

	/** 半角空格 */
	public static final String HALF_SPACE = " ";

	/** 全角空格 */
	public static final String FULL_SPACE = "　";

	// ========================================================= //
	// ************************** 符号 ************************* //
	// ========================================================= //

	/** 符号[ = ] */
	public static final String EQUAL = "=";

	/** 符号[ , ] */
	public static final String KAMA = ",";

	/** 符号[ . ] */
	public static final String POINT = ".";

	/** 符号[ : ] */
	public static final String COLON = ":";

	/** 符号[ ; ] */
	public static final String SEMI_COLON = ";";

	/** 引用符[ " ] */
	public static final String DOUBLE_QUOTES = "\"";

	/** 引用符[ ' ] */
	public static final String SINGLE_QUOTES = "'";

	/** 符号[ - ] */
	public static final String DASH = "-";

	/** 符号[ + ] */
	public static final String PLUS = "+";

	/** 日付分割符[ / ] */
	public static final String DATE_SEP = "/";

	/** 符号[ / ] / */
	public static final String SLASH = "/";

	/** 符号[ _ ] */
	public static final String UNDER_LINE = "_";

	/** 符号[ ( ] */
	public static final String LEFT_KAKO = "(";

	/** 符号[ ) ] */
	public static final String RIGHT_KAKO = ")";

	/** 符号[ ? ] */
	public static final String QUESTION_MARK = "?";

	/** 符合[ % ] */
	public static final String PERCENT = "%";

	/** 分割符号[ \t ] */
	public static final String SEPARATOR_T = "\t";

	/** 行分割符号[ mac: \r ] */
	public static final String SEPARATOR_R = "\r";

	/** 行分割符号[ linux: \n ] */
	public static final String SEPARATOR_N = "\n";

	/** 行分割符号[ windows: \r\n ] */
	public static final String SEPARATOR_RN = "\r\n";

	/** 行分割符号 */
	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	/** 文件行分割符号 */
	public static final String FILE_SEPARATOR = System
			.getProperty("file.separator");

	// ========================================================= //
	// ************************ 时间格式 *********************** //
	// ========================================================= //

	/** 日期格式(yyyy年MM月dd日 HH時mm分) */
	public static final String HEADER_DATE_FORMAT = "yyyy年MM月dd日 HH時mm分";

	/** 日期格式(yyyy/MM/dd HH:mm) */
	public static final String DATE_FORMAT_YEAR_MINUTE_SLASH = "yyyy/MM/dd HH:mm";

	/** 日期格式(yyyy/MM/dd HH24:mm) */
	public static final String DATE_FORMAT_YEAR_MINUTE_SLASH_DB = "yyyy/MM/dd HH24:mi";

	/** 日期格式(yyyy/MM/dd HH:mm:ss) */
	public static final String DATE_FORMAT_DATETIME_SLASH = "yyyy/MM/dd HH:mm:ss";

	/** 日期格式(yyyy/MM/dd HH24:mm:ss) */
	public static final String DATE_FORMAT_DATETIME_SLASH_DB = "yyyy/MM/dd HH24:mi:ss";

	/** 日期格式(yyyy/MM/dd HH:mm:ss.FFF) */
	public static final String DATE_FORMAT_DATETIME_FFF_SLASH = "yyyy/MM/dd HH:mm:ss.FFF";

	/** 日期格式(yyyy/MM/dd HH:mm:ss.FFF) */
	public static final String DATE_FORMAT_DATETIME_FFF_SLASH_DB = "yyyy/MM/dd HH24:mi:ss.FFF";

	/** 日期格式(yyyy/MM/dd) */
	public static final String DATE_FORMAT_DATE_SLASH = "yyyy/MM/dd";

	/** 日期格式(yyyyMMddHHmmss) */
	public static final String DATE_FORMAT_DATETIME = "yyyyMMddHHmmss";

	/** 日期格式(yyyyMMddHHmm) */
	public static final String DATE_FORMAT_DATETIMENOSS = "yyyyMMddHHmm";

	/** 日期格式(yyyyMMddHHmmss) */
	public static final String DATE_FORMAT_DATETIME_FFF = "yyyyMMddHHmmssFFF";

	/** 日期格式(yyyyMMddHHmmssSSSSSS) */
	public static final String DATE_FORMAT_DATETIME_SSS = "yyyyMMddHHmmssSSSSSS";

	/** 日期格式(yyyyMMddHHmmssFFFFFF) */
	public static final String DATE_FORMAT_DATETIME_FFFFFF = "yyyymmddhh24missff6";

	/** 日期格式(yyyymmddhh24miss) */
	public static final String DATE_FORMAT_DATETIME_HH24 = "yyyymmddhh24miss";

	/** 日期格式(yyMMddHHmm) */
	public static final String DATE_FORMAT_SHORT_YEAR = "yyMMddHHmm";

	/** 日期格式(yy/MM/dd HH:mm) */
	public static final String DATE_FORMAT_SHORT_YEAR_HOUR = "yy/MM/dd HH:mm";

	/** 日期格式(yy/MM/dd HH:mm) */
	public static final String DATE_FORMAT_SHORT_YEAR_HOUR_DB = "yy/MM/dd HH24:mi";

	/** 日期格式(yyyyMMddHH) */
	public static final String DATE_FORMAT_DATETIME_HOUR = "yyyyMMddHH";

	/** 日期格式(yyyy/MM/dd HH) */
	public static final String DATE_FORMAT_DATETIME_HOUR_SLASH = "yyyy/MM/dd HH";

	/** 日期格式(yyyy/MM/dd HH) */
	public static final String DATE_FORMAT_DATETIME_HOUR_SLASH_DB = "yyyy/MM/dd HH24";

	/** 日期格式(yyMMddHH) */
	public static final String DATE_FORMAT_SHORT_DATE_HOUR = "yyMMddHH";

	/** 日期格式(yy/MM/dd HH) */
	public static final String DATE_FORMAT_SHORT_DATE_HOUR_SLASH = "yy/MM/dd HH";

	/** 日期格式(yy/MM/dd HH) */
	public static final String DATE_FORMAT_SHORT_DATE_HOUR_SLASH_DB = "yy/MM/dd HH24";

	/** 日期格式(yyyy-MM-dd HH:mm:ss) */
	public static final String DATE_FORMAT_DATETIME_HYPHEN = "yyyy-MM-dd HH:mm:ss";

	/** 日期格式(yyyy-MM-dd HH:mm:ss) */
	public static final String DATE_FORMAT_DATETIME_HYPHEN_F9 = "yyyy-mm-dd hh24:mi:ss.ff9";

	/** 日期格式(yyyy-MM-dd HH:mm:ss) */
	public static final String DATE_FORMAT_DATETIME_HYPHEN_F6 = "yyyy-mm-dd hh24:mi:ss.ff6";

	/** 日期格式(dd-MMM-yyyy HH:mm:ss.SS) */
	public static final String DATE_FORMAT_DATETIME_ENGISH_F2 = "dd-MMM-yyyy HH:mm:ss.SS";

	/** 日期格式(yyMMdd) */
	public static final String DATE_FORMAT_SHORT_DATE = "yyMMdd";

	/** 日期格式(yy/MM/dd) */
	public static final String DATE_FORMAT_SHORT_DATE_SLASH = "yy/MM/dd";

	/** 日期格式(yyyy/MM) */
	public static final String DATE_FORMAT_DATE_YM_SLASH = "yyyy/MM";

	/** 日期格式(yyyyMMdd) */
	public static final String DATE_FORMAT_DATE_NO_SLASH = "yyyyMMdd";

	/** 日期格式(yyyy年MM月dd日) */
	public static final String DATE_FORMAT_DATE_NO_KANJI = "yyyy年MM月dd日";

	/** 日期格式(yyyyMMdd) */
	public static final String DATE_DEFAULT_FORMAT = "yyyyMMdd";

	/** 日期格式(yyMMdd) */
	public static final String DATE_SHORT_FORMAT = "yyMMdd";

	/** 日期格式(yyyy/MM/dd) */
	public static final String DATE_SLASH_FORMAT = "yyyy/MM/dd";

	/** 日期格式(yyyyMM) */
	public static final String DEFAULT_YM_FORMAT = "yyyyMM";

	/** 日期格式(yyyy/MM) */
	public static final String SLASH_YM_FORMAT = "yyyy/MM";

	/** Date日期格式(yyyy-MM-dd) */
	public static final String DB_DATE_FORMAT = "yyyy-MM-dd";

	/** Date日期格式(yyyy-MM) */
	public static final String DB_DATE_FORMAT_YM = "yyyy-MM";

	/** Timestamp日期格式(yyyy-MM-dd-HH.mm.ss.SSS) */
	public static final String DB_TIMESTAMP_FORMAT = "yyyy-MM-dd-HH.mm.ss.SSS";

	/** 常用Timestamp日期格式(yyyy-MM-dd HH:mm:ss.SSS) */
	public static final String TIMESTAMP_NORMAL_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	/** 时间格式(HHmmss) */
	public static final String TIME_DEFAULT_FORMAT = "HHmmss";

	/** 时间(HH:mm:ss) */
	public static final String COLON_TIME_FORMAT = "HH:mm:ss";

	/** 时间(HHmm) */
	public static final String DEFAULT_MINUTE_FORMAT = "HHmm";

	/** 时间(HH:mm) */
	public static final String COLON_MINUTE_FORMAT = "HH:mm";

	/** 日期格式(yyyy-MM-dd HH:mm) */
	public static final String DATE_FORMAT_YEAR_MINUTE = "yyyy-MM-dd HH:mm";

	// ========================================================= //
	// ************************ 缓存信息配置 ************************** //
	// ========================================================= //

	/** 用章单位信息缓存sessionid扩展码 */
	public static final String YZ_CACHE_NAME="504000001";

	/** 刻章单位信息缓存sessionid扩展码 */
	public static final String ZZ_CACHE_NAME="102000001";

	// ========================================================= //
	// ************************ 表字段代码常量 ************************** //
	// ========================================================= //

	/** 0：未上传；1：已上传；2：上传错误 */
	public static final String ISPACKAGE_0="0";

	/** 0：未上传；1：已上传；2：上传错误 */
	public static final String ISPACKAGE_1="1";

	/** 0：未上传；1：已上传；2：上传错误 */
	public static final String ISPACKAGE_2="2";

	/** 0：登录；1：查询；2：新增；3：修改；4：删除 */
	public static final String TYPE_0="0";

	/** 0：登录；1：查询；2：新增；3：修改；4：删除 */
	public static final String TYPE_1="1";

	/** 0：登录；1：查询；2：新增；3：修改；4：删除 */
	public static final String TYPE_2="2";

	/** 0：登录；1：查询；2：新增；3：修改；4：删除 */
	public static final String TYPE_3="3";

	/** 0：登录；1：查询；2：新增；3：修改；4：删除 */
	public static final String TYPE_4="4";

	/** 0：登录；1：查询；2：新增；3：修改；4：删除 */
	public static final String TYPE_NAME_0="登录";

	/** 0：登录；1：查询；2：新增；3：修改；4：删除 */
	public static final String TYPE_NAME_1="查询";

	/** 0：登录；1：查询；2：新增；3：修改；4：删除 */
	public static final String TYPE_NAME_2="新增";

	/** 0：登录；1：查询；2：新增；3：修改；4：删除 */
	public static final String TYPE_NAME_3="修改";

	/** 0：登录；1：查询；2：新增；3：修改；4：删除 */
	public static final String TYPE_NAME_4="删除";

	/** 1：成功；0：失败*/
	public static final String OPERATE_RESULT_0="0";

	/** 1：成功；0：失败*/
	public static final String OPERATE_RESULT_1="1";

	/** 1：成功；0：失败*/
	public static final String OPERATE_RESULT_NAME_0="失败";

	/** 1：成功；0：失败*/
	public static final String OPERATE_RESULT_NAME_1="成功";

	/** 0：未删除；1：删除 */
	public static final String DEL_FLAG_0="0";

	/** 0：未删除；1：删除 */
	public static final String DEL_FLAG_1="1";

	/** 101：印章承接消息（用章） */
	public static final String MSG_TYPE_101="101";

	/** 102：印章待领取消息（用章）*/
	public static final String MSG_TYPE_102="102";

	/** 103：印章承接退回消息（用章） */
	public static final String MSG_TYPE_103="103";

	/** 201：申请印章消息（刻章）*/
	public static final String MSG_TYPE_201="201";

	/** 202：印章备案核对失败消息（刻章）*/
	public static final String MSG_TYPE_202="202";

	/** 203：印章备案核对成功消息（刻章）*/
	public static final String MSG_TYPE_203="203";

	/** 301：申请印章消息（备案）*/
	public static final String MSG_TYPE_301="301";

	/** 302：印章备案上传消息（备案）*/
	public static final String MSG_TYPE_302="302";

	/** 303：印章备案核对失败消息（备案）*/
	public static final String MSG_TYPE_303="303";

	/** 304：工商新企业推送消息（备案）*/
	public static final String MSG_TYPE_304="304";

	/** 101：印章承接消息（用章） */
	public static final String MSG_TYPE_NAME_101="印章承接消息";

	/** 102：印章待领取消息（用章）*/
	public static final String MSG_TYPE_NAME_102="印章待领取消息";

	/** 103：印章承接退回消息（用章） */
	public static final String MSG_TYPE_NAME_103="印章承接退回消息";

	/** 201：申请印章消息（刻章）*/
	public static final String MSG_TYPE_NAME_201="申请印章消息";

	/** 202：印章备案核对失败消息（刻章）*/
	public static final String MSG_TYPE_NAME_202="印章备案核对失败消息";

	/** 203：印章备案核对成功消息（刻章）*/
	public static final String MSG_TYPE_NAME_203="印章备案核对成功消息";

	/** 301：申请印章消息（备案）*/
	public static final String MSG_TYPE_NAME_301="申请印章消息";

	/** 302：印章备案上传消息（备案）*/
	public static final String MSG_TYPE_NAME_302="印章备案上传消息";

	/** 303：印章备案核对失败消息（备案）*/
	public static final String MSG_TYPE_NAME_303="印章备案核对失败消息";

	/** 304：工商新企业推送消息（备案）*/
	public static final String MSG_TYPE_NAME_304="工商新企业推送消息";
	/** 0：未读；1：已读*/
	public static final String READED_0="0";
	/** 0：未读；1：已读*/
	public static final String READED_1="1";
}
