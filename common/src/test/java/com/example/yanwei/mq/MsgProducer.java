package com.example.yanwei.mq;

import com.example.yanwei.rebbitmq.tool.RabbitUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MsgProducer {

    //a. 生产者
    public static void publishMsg(String exchange, BuiltinExchangeType exchangeType, String toutingKey, String message)
            throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getConnectionFactory();
        //创建连接
        Connection connection = factory.newConnection();
        //创建消息通道
        Channel channel = connection.createChannel();
        // 声明exchange中的消息为可持久化，不自动删除
        channel.exchangeDeclare(exchange, exchangeType, true, false, null);
        // 发布消息
        channel.basicPublish(exchange, toutingKey, null, message.getBytes());
        channel.close();
        connection.close();
    }

    public static void main(String[] args) {
        DirectProducer directProducer = new DirectProducer();
        String[] routingKey = new String[]{"aaa", "bbb"};
        String msg = "hello >>> ";

        for (int i = 0; i < 30; i++) {
            directProducer.publishMsg(routingKey[i % 2], msg + i);
        }
        System.out.println("----over-------");
    }

}
