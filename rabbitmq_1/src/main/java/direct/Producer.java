package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Producer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "logs_direct";

        // 参数1：交换机名称   参数2：交换机类型 direct：路由模式
        channel.exchangeDeclare(exchangeName, "direct");

        // 发送消息
        String routingKey = "error";
        String msg = "这是direct模型发布的基于routingKey: [ " + routingKey + " ]发送的消息";
        channel.basicPublish(exchangeName, routingKey, false, null, msg.getBytes());

        RabbitMQUtils.closeConnectionAndChanel(channel, connection);

    }
}
