package com.zimug.bootlaunch.utils;

import java.io.Serializable;
import java.util.Map;

public interface IQuery extends Serializable {

    enum LINK {
        LIKE, EQ, NOT_EQ, LT, LT_OR_EQ, GT, GT_OR_EQ, IN, NOT_IN, IS_NULL, NOT_NULL
    }

    /**
     * 查询页码
     *
     * @return
     */
    Integer getStart();

    /**
     * 查询每页显示记录条数
     *
     * @return
     */
    Integer getLength();

    String sortOrder();

    Map<String, Object> params();

}