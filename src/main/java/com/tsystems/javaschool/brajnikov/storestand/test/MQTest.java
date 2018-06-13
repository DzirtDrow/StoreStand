package com.tsystems.javaschool.brajnikov.storestand.test;

public class MQTest {
    public MQTest() throws Exception {

        QueueConsumer consumer = new QueueConsumer("promo_queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }

    public static void main(String[] args) throws Exception {

        new MQTest();

//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("127.0.0.1");
//        factory.setPort(5672);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        //channel.queueDeclare("promo_queue", false, false, false, null);
//        channel.queueBind("promo_queue", "", "");
//
//        System.out.println("Waiting");
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
//                String message = new String(bytes, "UTF-8");
//                System.out.println("Message: " + message);
//            }
//        };
//        channel.basicConsume("promo_queue", true, consumer);
    }
}
