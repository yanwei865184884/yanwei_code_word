package com.example.yanwei.rebbitmq.tool;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitUtil {

    public static ConnectionFactory getConnectionFactory() {
        //创建连接工程，下面给出的是默认的case
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("82.156.42.175");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        return factory;
    }

}
