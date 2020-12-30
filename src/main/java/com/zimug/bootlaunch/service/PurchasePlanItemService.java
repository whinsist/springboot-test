package com.zimug.bootlaunch.service;

import com.zimug.bootlaunch.model.PurchasePlanItem;
import com.zimug.bootlaunch.service.base.BaseService;


public interface PurchasePlanItemService extends BaseService<PurchasePlanItem> {


    void custom(Integer pageNum, Integer pageSize);
}