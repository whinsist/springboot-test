package com.zimug.bootlaunch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/template")
public class TemplateController {


    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {

        //模版名称，实际的目录为：resources/templates/thymeleaftemp.html
         return "thymeleaftemp";
    }

}