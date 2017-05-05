package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Decoder.BASE64Encoder;



public class ServiceUtils {
	public static String getMd5Password(String password){
		try {
			//得到md5的对象
			MessageDigest md = MessageDigest.getInstance("md5");
			//将md5对象对passwordbyte数组加密，得到一个新的byte数组
			byte[] md5 = md.digest(password.getBytes());
			//new 出BASE64encoder
			BASE64Encoder encoder = new BASE64Encoder();
			//用这个encoder可以encode出md5字节数组
			return encoder.encode(md5);
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException();
		}
	}
	

	
	
}
