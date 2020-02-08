package com.zimug.bootlaunch.timertask.dynamic;

import lombok.Data;

@Data
public class QuartzAddBean {

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务执行类
     */
    private String jobClass;

    /**
     * 任务运行时间表达式
     */
    private String cronExpression;
}