package com.zimug.bootlaunch.controller;

import com.zimug.bootlaunch.model.ArticleVO;
import com.zimug.bootlaunch.service.ArticleRestService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Controller
@RequestMapping("/template")
public class TemplateController {


    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {

        //模版名称，实际的目录为：resources/templates/thymeleaftemp.html
         return "thymeleaftemp";
    }


    @Resource(name="articleMybatisRestServiceImpl")
    ArticleRestService articleRestService;


    //@ModelView
    @GetMapping("/freemarker")
    public String index(Model model) {
        List<ArticleVO> articles = articleRestService.getAll();
        ArticleVO articleVO = new ArticleVO();
        articleVO.setAuthor("张三");
        articleVO.setTitle("redis");
        articleVO.setContent("哈哈哈哈哈");
        articles.add(articleVO);

        model.addAttribute("articles", articles);

        //模版名称，实际的目录为：resources/templates/fremarkertemp.html
        return "fremarkertemp";
    }
}