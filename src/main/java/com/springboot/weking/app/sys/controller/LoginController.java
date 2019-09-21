package com.springboot.weking.app.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    /**
     * 登录
     */
    @RequestMapping("/index")
    public String gotoIndex() {
        return "index";

    }


}
