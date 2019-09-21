package com.springboot.weking.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;


@Controller
public class WorkSpaceAction {


    /**
     * 页面跳转
     *
     * @param url
     * @return
     */
    @RequestMapping("{url}.html")
    public String page(@PathVariable("url") String url, RedirectAttributes attributes, HttpServletRequest request, Model model) {

        Enumeration pNames = request.getParameterNames();
        while (pNames.hasMoreElements()) {
            String name = (String) pNames.nextElement();
            String value = request.getParameter(name);
            System.out.println(name + "=" + value);

            attributes.addAttribute(name, value);
            attributes.addFlashAttribute(name, value);
            model.addAttribute(name, value);
        }

        return url;
    }


}
