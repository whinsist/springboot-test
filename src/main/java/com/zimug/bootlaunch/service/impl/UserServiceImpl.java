package com.zimug.bootlaunch.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zimug.bootlaunch.generator.testdb.UserMapper;
import com.zimug.bootlaunch.model.User;
import com.zimug.bootlaunch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hong.Wu
 * @date: 9:08 2018/08/18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public void testPage(Integer pageNum,  Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectList(null);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getList());
    }
}
