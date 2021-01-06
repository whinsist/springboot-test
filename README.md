
文档地址：https://www.kancloud.cn/hanxt/springboot2/1492077
代码地址：https://github.com/hanxt/boot-launch


### 1、环境切换
#
java -jar springboot-test-0.0.1-SNAPSHOT.jar  --spring.profiles.active=dev



### 2、springboot自带logback
  日志常见术语：
      appender：注意控制日志输出到哪里，比如：文件、数据库、控制台打印等
      logger：用来设置一个包或一个类的日志打印级别、以及指定appender
      root：是一个特殊logger，所有的logger最终都会将输出流交给root，除非logger中配置了addivity="false"
      rollingPolicy：所有日志都放在一个文件是不好的，所以可以指定滚动策略，按照一定的周期或文件大小切割存放的日志文件
      RolloverSrategy：日志清理策略。通常是指日志保留事件
      异步日志：单独开一个线程做日志的写操作，达到不阻塞主线程的目的

### 3、整合log4j2
    取消springboot自带日志
    启动项目时出现包StaticLoggerBinder冲突：
    SLF4J: Class path contains multiple SLF4J bindings.
    SLF4J: Found binding in [jar:file:/C:/repository/org/apache/logging/log4j/log4j-slf4j-impl/2.10.0/log4j-slf4j-impl-2.10.0.jar!/org/slf4j/impl/StaticLoggerBinder.class]
    SLF4J: Found binding in [jar:file:/C:/repository/org/slf4j/slf4j-log4j12/1.7.25/slf4j-log4j12-1.7.25.jar!/org/slf4j/impl/StaticLoggerBinder.class]
    因此就要取消slf4j-log4j12-1.7.25.jar


​    
### 4、拦截器实现统一日志访问
    拦截器作用：拦截controller进入退出
    自定义logger 及logger-ref   

### 5、使用线程池
    避免系统资源耗尽， 省去创建线程和销毁线程的时间 
    异步任务在执行中 系统需要停止，如何优雅的关闭线程池


​    
### 6、整合quartz

```
1、增加配置
<!--整合quartz-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-quartz</artifactId>
</dependency>
2、yml配置quartz
 spring: 
   quartz:
    job-store-type: jdbc
    jdbc:
      initialize-schema: never #自动化初始表结构
3、quartz的数据表在：quartz-2.3.0.jar!\org\quartz\impl\jdbcjobstore\tables_mysql_innodb.sql
```



### 7、统一全局异常处理    



### 8、PageHelper

```
<!-- pagehelper 分页插件 --> 
<dependency>
	<groupId>com.github.pagehelper</groupId>
	<artifactId>pagehelper-spring-boot-starter</artifactId>
	<version>${pagehelper.boot.version}</version>
</dependency>

可以在yml配置pagehelper的参数
使用工具类：PageHelper.startPage(pageNum, pageSize)
使用jar其实就是：pagehelper:5.2.0 和jsqlparser:3.2
```



### 9、通用Mapper

```
<!-- 通用Mapper--> 
<dependency>
	<groupId>tk.mybatis</groupId>
	<artifactId>mapper-spring-boot-starter</artifactId>
	<version>1.2.4</version>
</dependency>
修改包名
//@org.mybatis.spring.annotation.MapperScan(basePackages = {"com.zimug.bootlaunch.generator"})
@tk.mybatis.spring.annotation.MapperScan(basePackages = {"com.zimug.bootlaunch.generator"})
实体Mapper类直接继承tk.mybatis.mapper.common.Mapper即可
使用jar其实就是：tk.mybatis:mapper:3.5.3
```

