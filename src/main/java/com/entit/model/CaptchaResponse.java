package com.entit.model;

import lombok.Data;

@Data
public class CaptchaResponse {

	private Boolean success;
	private String challenge_ts;
	private String hostname;
}
