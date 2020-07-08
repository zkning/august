package com.ning.message.service.impl;

import com.ning.website.dto.Resp;
import com.ning.message.service.MessageService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zkning on 2020/6/14.
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    public static final String routingkey = "routingkey";

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Override
    public Resp sendDXL(String msg) {

        return rabbitTemplate.execute((Channel chl) -> {

            // 死信交换器 -> 生命死信队列 -> 绑定队列交换机
            chl.exchangeDeclare("exchange.dlx", "direct", true);
            chl.queueDeclare("queue.dlx", true, false, false, null);
            chl.queueBind("queue.dlx", "exchange.dlx", routingkey);


            // 参数
            Map<String, Object> args = new HashedMap();
            args.put("x-message-ttl", 10000);  // 消息过期时间
            args.put("x-dead-letter-exchange", "exchange.dlx");  // 绑定死信交换机
            args.put("x-dead-letter-routing-key", "routingkey"); // 绑定死信路由键


            // 声明交换机 -> 声明队列（TTL队列） -> 绑定队列交换机
            chl.exchangeDeclare("exchange.message", "direct", true);
            chl.queueDeclare("queue.message", true, false, false, args);
            chl.queueBind("queue.message", "exchange.message", routingkey);
            chl.basicPublish("exchange.message", routingkey, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
            return Resp.SUCCESS();
        });
    }
}
