package com.bewg.token.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bewg.token.entity.TokenParam;
import com.bewg.token.entity.WeixinEnv;
import com.bewg.token.entity.DingDingEnv;
import com.bewg.token.manager.TokenJobMng;
import com.bewg.token.utils.AuthHelper;

@Controller
@RequestMapping("/hello")
public class HelloWordAct {

    @RequestMapping("/helloWord")
    public String helloWord() {
    	return "hello";
 
    }
    
 
}
