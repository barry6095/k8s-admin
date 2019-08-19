package cn.com.agree.aweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("echo")
public class EchoController {

	@GetMapping("/test")
	public String echo() {
		
		return "Hello World";
	}
	
}
