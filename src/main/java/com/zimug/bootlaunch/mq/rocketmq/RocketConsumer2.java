package com.zimug.bootlaunch.mq.rocketmq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
//同一个消费者组 和RocketConsumer平均消费
//@RocketMQMessageListener(consumerGroup = RocketContants.CONSUMER_GROUP1, topic = RocketContants.TEST_TOPIC)

// 单个组即p2p 多个组即发布订阅模式
// 不同消费组是可以同时消费 等于说TEST_TOPIC可以消费两次
@RocketMQMessageListener(consumerGroup = RocketContants.CONSUMER_GROUP2, topic = RocketContants.TEST_TOPIC)
public class RocketConsumer2 implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.err.println("RocketConsumer2接收到消息：" + message);
    }
}
