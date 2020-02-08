package com.zimug.bootlaunch.controller;

import com.zimug.bootlaunch.timertask.dynamic.QuartzAddBean;
import com.zimug.bootlaunch.timertask.dynamic.QuartzUpdateBean;
import com.zimug.bootlaunch.timertask.dynamic.QuartzUtils;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/quartz/job/")
public class QuartzController {

    //注入任务调度
    @Resource
    private Scheduler scheduler;

    @PostMapping("/create")
    @ResponseBody
    public String createJob(@RequestBody QuartzAddBean quartzAddBean) {
        QuartzUtils.createScheduleJob(scheduler, quartzAddBean);
        return "已创建任务";
    }

    @PostMapping("/pause")
    @ResponseBody
    public String pauseJob(String jobName) throws SchedulerException {
        QuartzUtils.pauseScheduleJob(scheduler, jobName);
        return "已暂停成功";
    }

    @PostMapping("/run")
    @ResponseBody
    public String runOnce(String jobName) throws SchedulerException {
        QuartzUtils.runOnce(scheduler, jobName);
        return "运行任务" + jobName + "成功";
    }

    @PostMapping("/resume")
    @ResponseBody
    public String resume(String jobName) throws SchedulerException {
        QuartzUtils.resumeScheduleJob(scheduler, jobName);
        return "恢复定时任务成功：" + jobName;
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(@RequestBody QuartzUpdateBean quartzUpdateBean) throws SchedulerException {
        QuartzUtils.updateScheduleJob(scheduler, quartzUpdateBean);
        return "更新定时任务调度信息成功";
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(String jobName) throws SchedulerException {
        QuartzUtils.deleteScheduleJob(scheduler, jobName);
        return "删除定时任务调度信息成功";
    }
}

