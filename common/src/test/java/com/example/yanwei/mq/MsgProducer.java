package com.example.yanwei.mq;

import com.example.yanwei.rebbitmq.tool.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MsgProducer {

    /**
     * 生产者
     * @param exchange 交换机名称
     * @param exchangeType 交换机类型
     * @param routingKey 路由键
     * @param message 消息内容
     * @throws IOException
     * @throws TimeoutException
     */
    public static void publishMsg(String exchange,String queryName, BuiltinExchangeType exchangeType, String routingKey, String message)
            throws IOException, TimeoutException {
        ConnectionFactory factory = RabbitUtil.getConnectionFactory();
        //创建连接
        Connection connection = factory.newConnection();
        //创建消息通道
        Channel channel = connection.createChannel();
        // 声明exchange中的消息为可持久化，不自动删除
        channel.exchangeDeclare(exchange, exchangeType, true, false, null);
        channel.queueDeclare(queryName,true,false,false,null);
        channel.queueBind(queryName, exchange, routingKey, null);

        // 发布消息
        channel.basicPublish(exchange, routingKey, null, message.getBytes());
        RabbitUtil.connectionClose(connection,channel);
    }

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        String exchange = "yw_exchange_1";
        String queryName = "yw_mq_test2";
      /*  MsgProducer.publishMsg(exchange,queryName,
                BuiltinExchangeType.TOPIC,
                "com.yanwei.rebbitMq.test","我是在测试,tuc");

        TimeUnit.SECONDS.sleep(10);
        MsgConsumer.pushConsumerMsg(exchange,
                queryName,
                BuiltinExchangeType.TOPIC,
                "com.yanwei.rebbitMq.test");
*/
        for (int i = 0 ; i < 20 ;i++ ) {
            MsgConsumer.getConsumerMsg(exchange, queryName, BuiltinExchangeType.TOPIC, null);
        }
    }

}
