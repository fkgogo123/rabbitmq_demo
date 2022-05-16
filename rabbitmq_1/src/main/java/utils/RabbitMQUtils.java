package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;
    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.103.99.58");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/vv2");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123");
        connectionFactory.setConnectionTimeout(120);
    }

    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnectionAndChanel(Channel channel, Connection conn) {
        try {
            if (channel != null) channel.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
