package com.xxy.boot.oss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController //接口用
//@Controller //页面渲染用
@RequestMapping(value={"index","index2"})
public class IndexController {

    /*@RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }*/
    
    /*@RequestMapping("/")
    public ModelAndView index() {
    	ModelMap model2 = new ModelMap();  
		model2.addAttribute("param2","abc");   
				
		return new ModelAndView("index", model2);
    }*/
	
	/*@RequestMapping("/")
    public ModelAndView index() {
		ModelAndView model=new ModelAndView("index");
		model.addObject("param2","abc");
				
		return  model;
    }*/
    
    /*@RequestMapping({"test"})
	public ModelAndView index(Model model) {
		model.addAttribute("param", "123");
		
		ModelMap model2 = new ModelMap();  
		model2.addAttribute("param2","abc");   
				
		return new ModelAndView("index", model2);
	}*/
	
	@RequestMapping("/test1")
    public String test1() {
		
        return "test1";
    }
	
	@RequestMapping("/test2")
    public String test2() {
        return "test2";
    }
    
    


}
