package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Decoder.BASE64Encoder;



public class ServiceUtils {
	public static String getMd5Password(String password){
		try {
			//�õ�md5�Ķ���
			MessageDigest md = MessageDigest.getInstance("md5");
			//��md5�����passwordbyte������ܣ��õ�һ���µ�byte����
			byte[] md5 = md.digest(password.getBytes());
			//new ��BASE64encoder
			BASE64Encoder encoder = new BASE64Encoder();
			//�����encoder����encode��md5�ֽ�����
			return encoder.encode(md5);
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException();
		}
	}
	

	
	
}
