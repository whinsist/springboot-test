package com.zimug.bootlaunch.model;

import java.util.Date;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 18:48 2020/02/08
 */
@Data
public class ArticleVO {
    private Long id;

    private String author;

    private String content;

    private Date createTime;

    private String title;
}
