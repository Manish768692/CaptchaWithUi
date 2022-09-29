package com.entit.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.entit.model.CaptchaResponse;

@Component
public class CaptchaValidate {

	public boolean isValid(String captcha) {
		String url = "https://www.google.com/recaptcha/api/siteverify";
		String param = "?secret=6Lcpgz0iAAAAACl03mDyxSWNBXPNB6YU3YUqjxKT&response=" + captcha;
		RestTemplate restTemplate = new RestTemplate();
		CaptchaResponse captchaResponse = restTemplate.postForObject(url + param, null, CaptchaResponse.class);
		return captchaResponse.getSuccess();
	}
}
