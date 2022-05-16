package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Producer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "logs_topic";

        channel.exchangeDeclare(exchangeName, "topic");

        // 发送的消息的routingKey
        String routingKey = "rabbitmq.user.save";

        String msg = "这是topic动态路由模型，routingKey: [ " + routingKey + " ]";
        channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());

        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
    }
}
