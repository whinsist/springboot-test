package com.zimug.bootlaunch.controller;

import com.zimug.bootlaunch.asynctask.AsyncCallBackTask;
import com.zimug.bootlaunch.asynctask.AsyncTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;

import static java.lang.System.currentTimeMillis;

@RestController
@Slf4j
public class AsyncTaskController {

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private AsyncCallBackTask asyncCallBackTask;

    @GetMapping("/asynctask")
    public String asynctask() throws Exception {
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
        return "ok";
    }

    @GetMapping("/asynctaskcall")
    public String asyncCallBackTask() throws Exception {
        long start = currentTimeMillis();
        Future<String> task1 = asyncCallBackTask.doTaskOneCallback();
        Future<String> task2 = asyncCallBackTask.doTaskOneCallback();
        Future<String> task3 = asyncCallBackTask.doTaskOneCallback();
        while (!task1.isDone() || !task2.isDone() || !task3.isDone()) {
            Thread.sleep(1000);
        }

        long end = currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");

        return "ok";
    }
}
