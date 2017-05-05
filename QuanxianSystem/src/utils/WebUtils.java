package utils;

import java.util.UUID;

import org.junit.Test;

public class WebUtils {

	public static String getId(){
		return UUID.randomUUID().toString();
	}

}
