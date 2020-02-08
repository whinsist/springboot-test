


1、环境切换
#
java -jar springboot-test-0.0.1-SNAPSHOT.jar  --spring.profiles.active=dev



2、springboot自带logback
  日志常见术语：
      appender：注意控制日志输出到哪里，比如：文件、数据库、控制台打印等
      logger：用来设置一个包或一个类的日志打印级别、以及指定appender
      root：是一个特殊logger，所有的logger最终都会将输出流交给root，除非logger中配置了addivity="false"
      rollingPolicy：所有日志都放在一个文件是不好的，所以可以指定滚动策略，按照一定的周期或文件大小切割存放的日志文件
      RolloverSrategy：日志清理策略。通常是指日志保留事件
      异步日志：单独开一个线程做日志的写操作，达到不阻塞主线程的目的

