/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package com.zimug.bootlaunch.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;


/**
 * 公用条件查询类
 */
@Data
public class Criteria implements Serializable {

    /**
     * 是否相异
     */
    protected boolean distinct;
    /**
     * 排序字段
     */
    protected String orderByClause;
    /**
     * 存放条件查询值
     */
    private Map<String, Object> condition;

    private Integer pageSize;
    private Integer pageNum;

    /**
     * 数据过滤条件
     **/
    private String sqlFilter;

    /**
     * 是否跳过数据过滤, true: 不进行过滤, false: 进行
     **/
    private boolean ignoreDataFilter;

    private boolean selfService;

    /**
     * 模拟组织id权限
     **/
    private Long mockOrgSid;

    /**
     * 模拟用户id，按照指定的用户id权限查询数据
     **/
    private Long mockUserSid;


    protected Criteria(Criteria example) {
        this.orderByClause = example.orderByClause;
        this.condition = example.condition;
        this.distinct = example.distinct;
        this.pageSize = example.pageSize;
        this.pageNum = example.pageNum;
    }

    public Criteria(String key, Object value) {
        this();
        condition.put(key, value);
    }

    public Criteria() {
        condition = new HashMap<String, Object>();
    }

    public void clear() {
        condition.clear();
        orderByClause = null;
        distinct = false;
        this.pageSize = null;
        this.pageNum = null;
    }

    /**
     * @param condition 查询的条件名称
     * @param value 查询的值
     */
    public Criteria put(String condition, Object value) {
        this.condition.put(condition, value);
        return this;
    }

    /**
     * 得到键值，C层和S层的参数传递时取值所用<br> 自行转换对象
     *
     * @param key 键值
     *
     * @return 返回指定键所映射的值
     */
    public Object get(String key) {
        return this.condition.get(key);
    }

    /**
     * 从对象设置 condition参数
     *
     * @param t
     * @param <T>
     */
    public <T> void setConditionObject(T t) {
        ObjectMapper objectMapper =new ObjectMapper();
        try {
            this.condition = objectMapper.readValue(objectMapper.writeValueAsString(t), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
