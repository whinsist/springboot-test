package com.zimug.bootlaunch.controller;

import com.zimug.bootlaunch.config.exception.AjaxResponse;
import com.zimug.bootlaunch.service.PurchasePlanItemService;
import com.zimug.bootlaunch.service.UserService;
import com.zimug.bootlaunch.service.test.log.TestLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PurchasePlanItemService purchasePlanItemService;

    @Autowired
    private TestLogService testLogService;

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

    @GetMapping("/testLog")
    public AjaxResponse testLog(Integer pageNum, Integer pageSize) {
        System.out.println("isDebugEnabled=" + log.isDebugEnabled() + " isInfoEnabled=" + log.isInfoEnabled() + " isErrorEnabled=" + log.isErrorEnabled());
        log.info("testLog start ...");
        log.debug("testLog debug ...");

        testLogService.testlogLevel();
        return AjaxResponse.success();
    }

}