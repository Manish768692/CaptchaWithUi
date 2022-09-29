package com.entit.captcha;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;

@Component
public class CaptchaUtil {
	
	// captcha class object
	public static Captcha createCaptcha(int width, int hight) {
		Captcha cap = new Captcha.Builder(width, hight)
				.addBackground(new GradiatedBackgroundProducer())	// for background color -> after new
				.addText()											// for text combination -> numeric, chinese, arabic 
				.addNoise(new CurvedLineNoiseProducer())			// for line in Captcha -> after new
				.build();
		return cap;
	}
	
	// convert binary to string
	public static String encodeBase64(Captcha captcha) {
		String imageData = null;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(captcha.getImage(), "png", byteArrayOutputStream );
			// try to read binary format in form so encode
			byte[] arr = Base64.getEncoder().encode(byteArrayOutputStream.toByteArray());  
			imageData = new String(arr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imageData;
	}

}
