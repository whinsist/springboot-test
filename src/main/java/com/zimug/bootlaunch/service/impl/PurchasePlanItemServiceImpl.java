package com.zimug.bootlaunch.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zimug.bootlaunch.generator.testdb.PurchasePlanItemMapper;
import com.zimug.bootlaunch.generator.testdb.UserMapper;
import com.zimug.bootlaunch.model.PurchasePlanItem;
import com.zimug.bootlaunch.model.User;
import com.zimug.bootlaunch.service.PurchasePlanItemService;
import com.zimug.bootlaunch.service.base.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class PurchasePlanItemServiceImpl extends CustomService<PurchasePlanItem> implements PurchasePlanItemService {

    @Autowired
    private PurchasePlanItemMapper purchasePlanItemMapper;


    @Override
    public void custom(Integer pageNum, Integer pageSize) {
        // selectByPrimaryKey(Long userId); deleteByPrimaryKey(Long userId); 一定不能写在PurchasePlanItemMapper中 因为父类已经存在
        PurchasePlanItem purchasePlanItem = new PurchasePlanItem();
        purchasePlanItem.setUsername("aaa");
        purchasePlanItem.setUserpwd("bbb");
        purchasePlanItemMapper.insertSelective(purchasePlanItem);
        purchasePlanItemMapper.insert(purchasePlanItem);


        //delete(T record); 根据实体属性作为条件进行删除，查询条件使用等号
        //deleteByPrimaryKey(Object key); 根据主键字段进行删除，方法参数必须包含完整的主键属性
        purchasePlanItemMapper.delete(purchasePlanItem);
        purchasePlanItemMapper.deleteByPrimaryKey(1222L);
        Example delExample = new Example(PurchasePlanItem.class);
        delExample.createCriteria().andLike("username", "xx").andEqualTo("userpwd", "xxx");
        purchasePlanItemMapper.deleteByExample(delExample);


        //updateByPrimaryKey(T record); 根据主键更新实体全部字段，null值会被更新
        //updateByPrimaryKeySelective(T record); 根据主键更新属性不为null的值
        PurchasePlanItem purchasePlanItemUpdate = new PurchasePlanItem();
        purchasePlanItemUpdate.setUserId(123L);
        purchasePlanItemUpdate.setUserpwd("####");
        purchasePlanItemMapper.updateByPrimaryKeySelective(purchasePlanItemUpdate);
        Example updateExample = new Example(PurchasePlanItem.class);
        updateExample.createCriteria().andLike("username", "xx").andEqualTo("userpwd", "xxx");
        purchasePlanItemMapper.updateByExampleSelective(purchasePlanItemUpdate, updateExample);


        //带条件的SQL：
        Example selectExample = new Example(PurchasePlanItem.class);
        selectExample.createCriteria().andLike("username", "%2018-05-20%").andEqualTo("username", "1");
        List<PurchasePlanItem> purchasePlanItems = purchasePlanItemMapper.selectByExample(selectExample);

        PageHelper.startPage(pageNum, pageSize);
        List<PurchasePlanItem> list = purchasePlanItemMapper.selectAll();
        PageInfo<PurchasePlanItem> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getList());

        PurchasePlanItem query = new PurchasePlanItem();
        List<PurchasePlanItem> list2 = purchasePlanItemMapper.cumtomerQuery(query);
        System.out.println(list2);


    }
}
