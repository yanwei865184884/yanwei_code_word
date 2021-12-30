package com.example.yanwei.mq;

import com.example.yanwei.rebbitmq.tool.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MsgConsumer {

    /**
     * 消费者
     * 获取连接Connection
     * 创建Channel
     * 将Channel与Queue进行绑定
     * 创建一个Consumer，从Queue中获取数据
     * 消息消费之后，ack
     * @param exchange 交换机
     * @param queue 队列名称
     * @param routingKey 路由键
     * @throws IOException
     * @throws TimeoutException
     */
    public static void consumerMsg(String exchange, String queue, String routingKey)
            throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getConnectionFactory();
        //创建连接
        Connection connection = factory.newConnection();
        //创建消息信道
        final Channel channel = connection.createChannel();
        //消息队列
        channel.queueDeclare(queue, true, false, false, null);
        //绑定队列到交换机
        channel.queueBind(queue, exchange, routingKey);
        System.out.println("[*] Waiting for message. To exist press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                try {
                    System.out.println(" [x] Received '" + message);
                } finally {
                    System.out.println(" [x] Done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        // 取消自动ack
        channel.basicConsume(queue, false, consumer);
    }

    /**
     * 推模式获取mq消息
     */
    public static void pushConsumerMsg(String exchange,String queryName, BuiltinExchangeType exchangeType, String routingKey) throws IOException, TimeoutException {
        System.out.println(1);
        //持续订阅 消费消息
        ConnectionFactory connectionFactory = RabbitUtil.getConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(queryName,true,false,false,null);
        Consumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                System.out.println(12);
                System.out.println("这他喵的是啥？");
                System.out.println("consumerTag:"+consumerTag);
                System.out.println("body:"+new String(body,"UTF-8"));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(queryName,defaultConsumer);
        RabbitUtil.connectionClose(connection,channel);
    }

    /**
     * 拉模式获取mq消息
     */
    public static void getConsumerMsg(String exchange,
                                      String queryName,
                                      BuiltinExchangeType exchangeType,
                                      String routingKey) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = RabbitUtil.getConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        GetResponse getResponse = channel.basicGet(queryName, true);
        if(null == getResponse){
            System.out.println("已经没有数据了");
        }else{
            byte[] body = getResponse.getBody();
            String s = new String(body);
            System.out.println(s);
        }
        RabbitUtil.connectionClose(connection,channel);
    }
}
