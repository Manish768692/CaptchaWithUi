package com.entit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entit.captcha.CaptchaUtil;
import com.entit.model.Product;
import com.entit.service.MyService;

import cn.apiclub.captcha.Captcha;

@Controller						// when working on jsp or thymeleaf use @Controller
@RequestMapping("/product")
public class MyController {
	
	@Autowired
	private MyService service; 

	private void setUpCaptcha(Product pr) {
	Captcha captcha	= CaptchaUtil.createCaptcha(250,80);
	pr.setHiddenCaptcha(captcha.getAnswer());
	pr.setCaptcha("");
	pr.setRealCaptcha(CaptchaUtil.encodeBase64(captcha));
	}
	
	@GetMapping("/reg")
	public String  registerPame(Model model) {
		Product p = new Product();
		setUpCaptcha(p);
		model.addAttribute("www", p);	
		return "registerPage";
	}
	
	@PostMapping("/save")
	public String saveDataInDb(@ModelAttribute Product product, Model model) {
		
		if (product.getCaptcha().equals(product.getHiddenCaptcha()) ) {			
		service.saveDataInDb(product);
		model.addAttribute("message", "Product Created..");
		}
		else {
			setUpCaptcha(product);
		}
		model.addAttribute("www", product);	
		return "registerPage";
			}
	
	@GetMapping("/all")
	public List<Product> getExcellist(){
		return (List<Product>) service.getAll();
	}
 	
}

