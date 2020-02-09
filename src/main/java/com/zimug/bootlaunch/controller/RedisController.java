package com.zimug.bootlaunch.controller;

import com.zimug.bootlaunch.model.Address;
import com.zimug.bootlaunch.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

/**
 * @author Hong.Wu
 * @date: 10:29 2020/02/09
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOperations;

    @Resource(name = "redisTemplate")
    private ListOperations<String, Object> listOperations;

    @Resource(name = "redisTemplate")
    private SetOperations<String, Object> setOperations;

    @Resource(name = "redisTemplate")
    private ZSetOperations<String, Object> zSetOperations;


    @GetMapping("test_value")
    public void testValueObj() throws Exception {
        Person person = new Person("kobe", "byrant");
        person.setAddress(new Address("南京", "中国"));

        valueOperations.set("player:ValueOperations", person);

        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set("player:RedisTemplate", person);

        stringRedisTemplate.opsForValue().set("player:stringRedisTemplate", "abc");


        Person getBack = (Person) valueOperations.get("player:1");
        System.out.println(getBack);
    }

    @GetMapping("test_set")
    public void testSetOperation() throws Exception {
        Person person = new Person("boke", "byrant");
        Person person2 = new Person("curry", "stephen");
        setOperations.add("playerset", person, person2);
        Set<Object> result = setOperations.members("playerset");
        System.out.println(result);
    }

    @GetMapping("test_hash")
    public void HashOperations() throws Exception {
        Person person = new Person("boke", "byrant");
        //hset "hash:player" "firstname" kobe
        hashOperations.put("hash:player", "firstname", person.getFirstname());
        hashOperations.put("hash:player", "lastname", person.getLastname());
        hashOperations.put("hash:player", "address", person.getAddress());
        System.out.println(hashOperations.get("hash:player", "firstname"));
    }

    @GetMapping("test_list")
    public void ListOperations() throws Exception {

//        listOperations.leftPush("list:player",new Person("boke","byrant"));
//        listOperations.leftPush("list:player",new Person("Jordan","Mikel"));
//        listOperations.leftPush("list:player",new Person("curry","stephen"));

        redisTemplate.opsForList().leftPush("list:player", new Person("boke", "byrant"));
        redisTemplate.opsForList().leftPush("list:player", new Person("Jordan", "Mikel"));
        redisTemplate.opsForList().leftPush("list:player", new Person("curry", "stephen"));
        System.out.println(listOperations.leftPop("list:player"));
        System.out.println("-------");

        List<Person> personList = redisTemplate.opsForList().range("list:player", 0, -1);
        for (Person person : personList) {
            System.out.println(person);
        }
    }
}
