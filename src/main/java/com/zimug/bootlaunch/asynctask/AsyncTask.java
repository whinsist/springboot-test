package com.zimug.bootlaunch.asynctask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {

    @Autowired
    private TaskService taskService;

    @Async("taskExecutor")
    public void doTaskOne() throws Exception {
        taskService.doTaskOne();
    }

    @Async("taskExecutor")
    public void doTaskTwo() throws Exception {
        taskService.doTaskTwo();
    }

    @Async("taskExecutor")
    public void doTaskThree() throws Exception {
        taskService.doTaskThree();
    }
}