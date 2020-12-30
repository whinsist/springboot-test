package com.zimug.bootlaunch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
//@org.mybatis.spring.annotation.MapperScan(basePackages = {"com.zimug.bootlaunch.generator"})
//tk.mybatis.mapper.MapperException: 无法获取实体类com.zimug.bootlaunch.model.PurchasePlanItem对应的表名!
@tk.mybatis.spring.annotation.MapperScan(basePackages = {"com.zimug.bootlaunch.generator"})
@EnableCaching


public class SpringbootTestApplication {

	public static void main(String[] args) {

		// spring-boot-autoconfigure-2.0.4.RELEASE.jar -> spring.factories


		SpringApplication.run(SpringbootTestApplication.class, args);
	}

}
