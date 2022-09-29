package com.entit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.entit.model.CaptchaResponse;
import com.entit.model.Product;
import com.entit.service.MyService;
import com.entit.util.CaptchaValidate;

@Controller						// when working on jsp or thymeleaf use @Controller
@RequestMapping("/product")
public class MyController {
	
	@Autowired
	private MyService service; 
	
	@Autowired
	private CaptchaValidate cp;
	
   @GetMapping("/reg")
	public String  registerPame(Model model){
		model.addAttribute("product", new Product());	
		return "registerPage";
	}
	
	@PostMapping("/save")
	public String saveDataInDb(@ModelAttribute Product product,
			@RequestParam("g-recaptcha-response") String captcha,
			Model model) {		
		if (cp.isValid(captcha)) {
			Integer id = service.saveDataInDb(product);
			model.addAttribute("message", "Product Created.."+ id);		
			model.addAttribute("product", new Product());			
		}
		else {
			model.addAttribute("message", "Please validate captcha first");
		}		
		return "registerPage";
			}
	
	@GetMapping("/all")
	public List<Product> getExcellist(){
		return (List<Product>) service.getAll();
	}
 	
}

