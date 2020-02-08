package com.zimug.bootlaunch.controller;

import com.zimug.bootlaunch.config.exception.AjaxResponse;
import com.zimug.bootlaunch.model.ArticleVO;
import com.zimug.bootlaunch.service.ArticleRestService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/rest/articles")
public class ArticleRestController {

    @Resource(name = "articleMybatisRestServiceImpl")
    ArticleRestService articleRestService;


    @PostMapping("")
    public @ResponseBody
    AjaxResponse saveArticle(@RequestBody ArticleVO article) {
        log.info("saveArticle：{}", article);
        log.info("articleRestService return :" + articleRestService.saveArticle(article));
        return AjaxResponse.success(article);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    AjaxResponse deleteArticle(@PathVariable Long id) {
        articleRestService.deleteArticle(id);
        return AjaxResponse.success(id);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    AjaxResponse updateArticle(@PathVariable Long id, @Valid @RequestBody ArticleVO article) {
        article.setId(id);
        articleRestService.updateArticle(article);
        return AjaxResponse.success();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    AjaxResponse getArticle(@PathVariable Long id) {
        return AjaxResponse.success(articleRestService.getArticle(id));
    }


    @GetMapping("")
    public @ResponseBody
    AjaxResponse getAll() {
        return AjaxResponse.success(articleRestService.getAll());
    }
}