package fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

public class Publisher {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // fanout模式，消息需要发送给交换机
        // 将通道声明指定交换机
        // 参数1: 交换机名称
        // 参数2： 交换机类型：fanout表示广播类型
        channel.exchangeDeclare("logs", "fanout");

        for (int i = 0; i < 10; i++) {
            // 发送消息
            channel.basicPublish("logs", "", null, "fanout type message".getBytes());
        }
        System.out.println("发送完毕");

        RabbitMQUtils.closeConnectionAndChanel(channel, connection);

    }
}
