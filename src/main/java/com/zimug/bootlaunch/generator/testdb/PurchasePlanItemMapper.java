package com.zimug.bootlaunch.generator.testdb;


import com.zimug.bootlaunch.model.PurchasePlanItem;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PurchasePlanItemMapper extends Mapper<PurchasePlanItem> {

    //int deleteByPrimaryKey(Long userId);

    //int insert(PurchasePlanItem record);

    //int insertSelective(PurchasePlanItem record);

    //PurchasePlanItem selectByPrimaryKey(Long userId);

//    int updateByPrimaryKeySelective(PurchasePlanItem record);

//    int updateByPrimaryKey(PurchasePlanItem record);


    List<PurchasePlanItem> cumtomerQuery(PurchasePlanItem param);
}