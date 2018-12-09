package com.xxy.boot.oss.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //注意这里必须为Controller
public class HelloController {

	/*@RequestMapping("/hello")
    public String helloHtml(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        return "hello";
    }*/
	
	@RequestMapping("/hello")
    public String helloHtml(Model model) {
		model.addAttribute("hello", "欢迎进入HTML页面1");
        return "hello";
    }
	
}
