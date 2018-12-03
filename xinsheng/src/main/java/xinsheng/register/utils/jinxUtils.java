package xinsheng.register.utils;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.security.Key;
import java.text.SimpleDateFormat;

import io.jsonwebtoken.*;

import java.util.Date;
public class jinxUtils {
	static String flag = "ceshi";
	//Sample method to construct a JWT
	private static String createJWT(String id, String issuer, String subject, long ttlMillis) {
	 
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
	    JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .setSubject(subject)
	                                .setIssuer(issuer)
	                                .signWith(signatureAlgorithm, signingKey);
	 
	    //if it has been specified, let's add the expiration
	    if (ttlMillis >= 0) {
	    	System.out.println(nowMillis +"\\"+ ttlMillis);
	    long expMillis = nowMillis + ttlMillis;
	    millis(nowMillis);
	    millis(ttlMillis);
	    System.out.println("expMillis:"+expMillis);
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	 
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	//Sample method to validate and read the JWT
	private static void parseJWT(String jwt) {
	 
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary(flag))
	       .parseClaimsJws(jwt).getBody();
	    System.out.println("ID: " + claims.getId());
	    System.out.println("Subject: " + claims.getSubject());
	    System.out.println("Issuer: " + claims.getIssuer());
	    System.out.println("Expiration: " + claims.getExpiration());
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	   String r = simpleDateFormat.format(claims.getExpiration());
	   System.out.println(r);
	}
	
	public static void millis(long ttlMillis){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   String r = simpleDateFormat.format(ttlMillis);
		   System.out.println(r);
	}
	public static void main(String[] args) {
		String id = "3";
		String issuer = "xiaohua";
		String subject = "guanli";
		long ttlMillis = 86400000*5;
		String token = createJWT(id,issuer , subject, ttlMillis);
		parseJWT(token);
	}
}
