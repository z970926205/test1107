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
	 * jwt����ֵ
	 */
	@Value("${gt.tokenSecret}")
	private static String flag;
	/**
	 * pwd����ֵ
	 */
	@Value("${gt.pwdSecret}")
	private static String pwdSecret;
	/**
	 * jwt��Ч��
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
	 * ����JWT��ʾ������
	 * @param id �û�id
	 * @param issuer �û���
	 * @param subject ��ɫ
	 * @param ttlMillis ��Ч��
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
	    //���ǽ���ApiKey��Կǩ��JWT
	    //byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecret());
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(flag);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder().setId(id)//�û�id
	                                .setIssuedAt(now)//ǩ��ʱ��
	                                .setSubject(subject)//��ɫ
	                                .setIssuer(issuer)//�û���
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
	 * ��֤�Ͷ�ȡJWT��ʾ������
	 * @param jwt ��Ҫ��֤��token
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
	 * �Ѻ�����ת��Ϊ����
	 * @param ttlMillis ��Ҫת���ĺ�����
	 */
	public static void formatMillisToDay(long ttlMillis){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   String r = simpleDateFormat.format(ttlMillis);
		   logger.info(r);
	}
	
	/**
	 * ��ʽ������
	 * @param date 
	 * @return
	 */
	public static String createDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	/**
	 * ��ʽ�����ں�ʱ��
	 * @param date
	 * @return
	 */
	public static String createDateAndTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * �ж��ַ����Ƿ�ΪnullΪ""
	 * ���ݽ���trim����
	 * @param params ��Ҫ�жϵ��ַ���
	 * @return
	 */
	public static boolean checkStrings(String... params) {//Java �ɱ�����б�
		logger.info("");
		if (null == params || params.length == 0) {//��params�Ǳ���Ϊһ������Դ���
            return false;
        }
		for (String param : params) {
			if (param == null || "".equals(param.trim())) {
                return false;//ֻҪ��һ�����������򷵻�false
            }
			//ȥ��
			param = param.trim();
        }
		return true;
	}
	
	/**
	 * �ж϶����Ƿ�Ϊ��
	 * @param params
	 * @return
	 */
	public static boolean isNulls(Object... params) {
		logger.info("");
		if (null == params || params.length == 0) {//��params�Ǳ���Ϊһ������Դ���
            return false;
        }
		for (Object param : params) {
			if (param == null) {
                return false;//ֻҪ��һ�����������򷵻�false
            }
        }
		return true;
	}
	
	
	/**
	 * MD5����
	 * @param pwdSecret 
	 * @param pwd
	 * @return
	 */
	public static String stringMD5(String pwd) {
		logger.info("pwd:"+pwd);
		try {
			//�пմ���
			if(!checkStrings(pwd)){
				return null;
			}
			//���δ���
			pwd +=pwdSecret;
			// �õ�һ��MD5ת�����������ҪSHA1�������ɡ�SHA1����
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// ������ַ���ת�����ֽ�����
			byte[] inputByteArray = pwd.getBytes();
			// inputByteArray�������ַ���ת���õ����ֽ�����
			messageDigest.update(inputByteArray);
			// ת�������ؽ����Ҳ���ֽ����飬����16��Ԫ��
			byte[] resultByteArray = messageDigest.digest();
			// �ַ�����ת�����ַ�������
			return byteArrayToHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	// ��������������ڽ��ֽ����黻�ɳ�16���Ƶ��ַ���
	private static String byteArrayToHex(byte[] byteArray) {
		// ���ȳ�ʼ��һ���ַ����飬�������ÿ��16�����ַ�
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		// newһ���ַ����飬�������������ɽ���ַ����ģ�����һ�£�һ��byte�ǰ�λ�����ƣ�Ҳ����2λʮ�������ַ���2��8�η�����16��2�η�����
		char[] resultCharArray = new char[byteArray.length * 2];
		// �����ֽ����飬ͨ��λ���㣨λ����Ч�ʸߣ���ת�����ַ��ŵ��ַ�������ȥ
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// �ַ�������ϳ��ַ�������
		return new String(resultCharArray);
	}
	
	
}
