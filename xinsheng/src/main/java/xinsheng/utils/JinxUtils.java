package xinsheng.utils;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import io.jsonwebtoken.*;

import java.util.Date;
public class JinxUtils {
	private static final Logger logger =Logger.getLogger(JinxUtils.class);
	
	/**
	 * jwt加密值
	 */
	@Value("${gt.tokenSecret}")
	private static String flag;
	/**
	 * pwd加密值
	 */
	@Value("${gt.pwdSecret}")
	private static String pwdSecret;
	/**
	 * jwt有效期
	 */
	@Value("${gt.tokenMillis}")
	private static long ttlMillis;
	
	public static void main(String[] args) {
		JinxUtils j = new JinxUtils();
		String id = "3";
		String issuer = "xiaohua";
		String subject = "guanli";
		long validityMillis = 86400000*5;
		String token = j.createJWT(id,issuer , subject);
		j.parseJWT(token);
	}
	
	/**
	 * 构建JWT的示例方法
	 * @param id 用户id
	 * @param issuer 用户名
	 * @param subject 角色
	 * @param ttlMillis 有效期
	 * @return
	 */
	//Sample method to construct a JWT
	public static String createJWT(String id, String issuer, String subject) {
		logger.info("id:"+id+" issuer:"+issuer+" subject:"+subject+" ttlMillis:"+ttlMillis);
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	    //We will sign our JWT with our ApiKey secret
	    //我们将用ApiKey密钥签署JWT
	    //byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecret());
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(flag);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)//用户id
	                                .setIssuedAt(now)//签发时间
	                                .setSubject(subject)//角色
	                                .setIssuer(issuer)//用户用
	                                .signWith(signatureAlgorithm, signingKey);
	    //if it has been specified, let's add the expiration
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	    formatMillisToDay(expMillis);
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	 
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    logger.info("token:"+builder.compact());
	    return builder.compact();
	}
	
	/**
	 * 验证和读取JWT的示例方法
	 * @param jwt 需要验证的token
	 */
	//Sample method to validate and read the JWT
	public static void parseJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary(flag))
	       .parseClaimsJws(jwt).getBody();
	    logger.info("ID: " + claims.getId());
	    logger.info("Subject: " + claims.getSubject());
	    logger.info("Issuer: " + claims.getIssuer());
	    logger.info("Expiration: " + claims.getExpiration());
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	   String r = simpleDateFormat.format(claims.getExpiration());
	   logger.info(r);
	}
	/**
	 * 把毫秒数转换为天数
	 * @param ttlMillis 需要转换的毫秒数
	 */
	public static void formatMillisToDay(long ttlMillis){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   String r = simpleDateFormat.format(ttlMillis);
		   logger.info(r);
	}
	
	/**
	 * 格式化日期
	 * @param date 
	 * @return
	 */
	public static String createDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	/**
	 * 格式化日期和时间
	 * @param date
	 * @return
	 */
	public static String createDateAndTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 判断字符串是否为null为""
	 * 内容进行trim处理
	 * @param params 需要判断的字符串
	 * @return
	 */
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
	
	/**
	 * 判断对象是否为空
	 * @param params
	 * @return
	 */
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
	
	
	/**
	 * MD5加密
	 * @param pwdSecret 
	 * @param pwd
	 * @return
	 */
	public static String stringMD5(String pwd) {
		logger.info("pwd:"+pwd);
		try {
			//判空处理
			if(!checkStrings(pwd)){
				return null;
			}
			//加盐处理
			pwd +=pwdSecret;
			// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 输入的字符串转换成字节数组
			byte[] inputByteArray = pwd.getBytes();
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
