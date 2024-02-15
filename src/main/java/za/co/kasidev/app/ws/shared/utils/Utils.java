package za.co.kasidev.app.ws.shared.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;



@Component
public class Utils {
	
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public String generatedUserId(int length)
	{
		return generateRandomString(length);
	}
	
	private String generateRandomString(int length)
	{
		
		StringBuilder stringBuilder = new StringBuilder(length);
		for(int x = 0; x < length; x++)
		{
			stringBuilder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(stringBuilder);
	}
	

}
