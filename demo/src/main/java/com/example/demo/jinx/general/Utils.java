package com.example.demo.jinx.general;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Utils {
	private static final Log logger = LogFactory.getLog(Utils.class); 
	public static boolean checkStrings(String... params) {//Java 可变参数列表
		logger.info("");
		if (null == params || params.length == 0) {//数params是被作为一个数组对待的
            return false;
        }
		for (String param : params) {
			if (param == null || "".equals(param.trim())) {
                return false;//只要有一个参数符合则返回false
            }
			//去空
			param = param.trim();
        }
		return true;
	}
	
	public static boolean isNulls(Object... params) {
		logger.info("");
		if (null == params || params.length == 0) {//数params是被作为一个数组对待的
            return false;
        }
		for (Object param : params) {
			if (param == null) {
                return false;//只要有一个参数符合则返回false
            }
        }
		return true;
	}
	
	public static Date setformatDate(String date){
		return null;
	}
	
	public static Date setformatTime(String date){
		return null;
	}
	
	/**
	 * MD5加密
	 * @param input
	 * @return
	 */
	public static String stringMD5(String input) {
		logger.info("");
		try {
			// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 输入的字符串转换成字节数组
			byte[] inputByteArray = input.getBytes();
			// inputByteArray是输入字符串转换得到的字节数组
			messageDigest.update(inputByteArray);
			// 转换并返回结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();
			// 字符数组转换成字符串返回
			return byteArrayToHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	// 下面这个函数用于将字节数组换成成16进制的字符串
	private static String byteArrayToHex(byte[] byteArray) {
		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}
}
