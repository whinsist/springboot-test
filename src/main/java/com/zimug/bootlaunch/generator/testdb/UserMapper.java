package com.zimug.bootlaunch.generator.testdb;


import com.zimug.bootlaunch.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    int insert(User record);

    User selectByPrimaryKey(Long userId);

    List<Map<String, Object>> selectAll(Map<String, Object> map);

    List<User> selectList(Map<String, Object> map);
}