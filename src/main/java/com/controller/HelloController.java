package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.User;
import com.service.UserService;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("hello")
public class HelloController {
	Logger logger = LoggerFactory.getLogger(HelloController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/word")
    public String HelloWorld(Model model){  
        model.addAttribute("message","Hello World!!!");
        System.out.print("aaaaaaaaaaaaaaaab");
        logger.error("1234567");
        return "helloWord";  
    }  
	@RequestMapping(value="/test")  
	@ResponseBody
    public String test(Model model){  
        model.addAttribute("message","Hello World!!!");
        return "helloWord";  
    }
	@RequestMapping(value="/find")  
    public String find(Model model){
        User user = userService.findById(1L);
        model.addAttribute("user",user);
        System.out.println(user);
        return "helloWord";  
    }
	
}


