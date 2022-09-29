package com.entit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import cn.apiclub.captcha.Captcha;
import lombok.Data;

@Data
@Entity
public class Product {

	@Id
	@GeneratedValue
	private Integer ProductId;
	private String ProductName;
	private String ProductDesc;
	private String ProductPrice;

}
