package com.zimug.bootlaunch.controller;

import com.zimug.bootlaunch.config.exception.AjaxResponse;
import com.zimug.bootlaunch.model.ArticleVO;
import com.zimug.bootlaunch.model.PurchasePlanItem;
import com.zimug.bootlaunch.model.User;
import com.zimug.bootlaunch.service.ArticleRestService;
import com.zimug.bootlaunch.service.PurchasePlanItemService;
import com.zimug.bootlaunch.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PurchasePlanItemService purchasePlanItemService;

    @GetMapping("/pagehelper")
    public AjaxResponse getAll(Integer pageNum, Integer pageSize) {
        if (Objects.isNull(pageNum)) {
            pageNum = 1;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 2;
        }
        userService.testPage(pageNum, pageSize);
        return AjaxResponse.success();
    }

    @GetMapping("/baseMappser")
    public AjaxResponse test2(Integer pageNum, Integer pageSize) {
        if (Objects.isNull(pageNum)) {
            pageNum = 1;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 2;
        }

        purchasePlanItemService.custom(pageNum, pageSize);


        return AjaxResponse.success();
    }
}