package core.util;

import java.security.MessageDigest;


/**
 * <p>
 * MD5加密工具类
 * </p>
 * 
 */
public final class Md5Util {


	/**
	 * 密钥字符集
	 */
	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 加密
	 * 
	 * @param arg
	 * @return 密钥
	 */
	public static String encode(String password) {
		if (password == null) {
			password = "";
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return toHex(md5.digest());
	}

	private static String toHex(byte[] bytes) {
		StringBuffer str = new StringBuffer(32);
		for (byte b : bytes) {
			str.append(hexDigits[(b & 0xf0) >> 4]);
			str.append(hexDigits[(b & 0x0f)]);
		}
		return str.toString();
	}
	
//	public static void main(String[] args){
//		System.out.println(encode("123456"));
//		System.out.println(encode("159632"));
//		System.out.println(encode("159632_zb"));
//		System.out.println(encode("justdoit"));
//	}
	
	public static String encryptString(String src) {
		int k = 0;
		String ret = "";
		char xChar;
		int intChar = 0;
		int ucs = 0;
		int utf = 0;

		for (int i = 1; i <= src.length(); i++) {
			k++;
			// xChar = char.Parse(src.Substring(i - 1, 1));
			xChar = src.charAt(i - 1);
			intChar = xChar;
			// UCS-2
			if (intChar >= 0 && intChar <= 127) {
				ret = encryptChar(xChar, k) + ret;
			} else if (intChar >= 128 && intChar <= 2047) // UCS-4
			{
				ucs = (intChar >> 6) | 192;
				intChar = (intChar & 63) | 128;
				ret = encryptChar((char) (intChar), k + 1)
						+ encryptChar((char) ucs, k) + ret;
				k++;
			} else // UTF-8
			{
				utf = (intChar >> 12) | 224;
				ucs = (intChar >> 6) & 63 | 128;
				intChar = (intChar & 63) | 128;
				ret = encryptChar((char) intChar, k + 2)
						+ encryptChar((char) ucs, k + 1)
						+ encryptChar((char) utf, k) + ret;
				k = k + 2;
			}
		}

		// return HttpUtility.UrlEncode(ret);
		return ret;
	}

	public static String decryptString(String src) throws Exception {
		int k = 0;
		String ret = "";
		String xDouble;

		char xChar;
		byte intChar;
		char xChar2;
		byte intChar2;
		char xChar3;
		byte intChar3;

		// src = HttpUtility.UrlDecode(src);
		if (src.equals("undefined")) {
			return "";
		}

		if (src.length() % 2 != 0) {
			throw new Exception("Decrypt failed. Data may have been destroyed.");
		}

		for (int i = src.length(); i >= 1; i = i - 2) {
			k++;
			/* xDouble = src.Substring(i - 2, 2); */
			xDouble = src.substring(i - 2, i);
			xChar = decryptChar(xDouble, k);
			intChar = (byte) xChar;
			if (intChar >= 0 && intChar <= 127) // UCS-2
			{
				ret += xChar;
			} else if (intChar >= 192 && intChar <= 223) // UCS-4
			{
				k++;
				i = i - 2;
				// xDouble = src.Substring(i - 2, 2);
				xDouble = src.substring(i - 2, i);
				xChar2 = decryptChar(xDouble, k);
				intChar2 = (byte) xChar2;

				ret += (char) ((intChar & 31) << 6 | intChar2 & 63);
			} else // UTF-8
			{
				k++;
				i = i - 2;
				xDouble = src.substring(i - 2, i);
				xChar2 = decryptChar(xDouble, k);
				intChar2 = (byte) xChar2;

				k++;
				i = i - 2;
				xDouble = src.substring(i - 2, i);
				xChar3 = decryptChar(xDouble, k);
				intChar3 = (byte) xChar3;

				ret += (char) ((intChar & 15) << 12 | (intChar2 & 63) << 6 | intChar3 & 63);
			}
		}

		return ret;
	}

	private static String encryptChar(char src, int i) {
		char xChar;
		int intChar;
		int yintChar;
		// char yChar;
		int xChar1;
		int xChar2;
		char yChar1;
		char yChar2;
		String ret = "";

		xChar = src;
		intChar = xChar;
		yintChar = intChar;
		if (i % 2 == 0) {
			yintChar = intChar + 2;
		} else {
			yintChar = intChar + 1;
		}
		yintChar = yintChar ^ 11;
		// yChar = (char) yintChar;
		xChar1 = (int) Math.floor((yintChar / 15));
		if (xChar1 >= 10) {
			yChar1 = (char) (('A') + xChar1 - 10);
		} else {
			yChar1 = (xChar1 + "").trim().charAt(0);
		}

		xChar2 = yintChar % 15;
		if (xChar2 >= 10) {
			yChar2 = (char) (('A') + xChar2 - 10);
		} else {
			yChar2 = (xChar2 + "").trim().charAt(0);
		}

		ret = yChar2 + "" + yChar1;

		return ret;
	}

	private static char decryptChar(String src, int i) {
		char xChar;
		int intChar;
		int yintChar;
		int xChar1;
		int xChar2;
		char yChar1;
		char yChar2;
		char ret;

		xChar1 = 0;
		xChar2 = 0;

		// yChar1 = char.Parse(src.Substring(1, 1));
		yChar1 = src.charAt(1);
		// yChar2 = char.Parse(src.Substring(0, 1));
		yChar2 = src.charAt(0);
		if (yChar1 >= 'A') {
			xChar1 = 10 + yChar1 - 'A';
		} else {
			xChar1 = Integer.parseInt(yChar1 + "");
		}
		if (yChar2 >= 'A') {
			xChar2 = 10 + yChar2 - 'A';
		} else {
			xChar2 = Integer.parseInt(yChar2 + "");
		}
		yintChar = xChar1 * 15 + xChar2;
		yintChar = yintChar ^ 11;

		if (i % 2 == 0) {
			intChar = yintChar - 2;
		} else {
			intChar = yintChar - 1;
		}

		xChar = (char) intChar;
		ret = xChar;

		return ret;
	}

	public static void main(String args[]) {
		try {
			String aa = Md5Util.encryptString("123456");
			System.out.println(aa);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}