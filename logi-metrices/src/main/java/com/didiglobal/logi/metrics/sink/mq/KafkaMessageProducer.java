package com.didiglobal.logi.metrics.sink.mq;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaMessageProducer {

    private static final Logger      logger         = Logger.getLogger(KafkaMessageProducer.class);

    private static final int         SUCCESS        = 0;

    private static final int         FAILED         = -1;

    private static final String      Configlocation = "kafka-producer.properties";

    private Producer<String, String> producer;

    private String                   brokerList;

    private Properties               properties;

    public KafkaMessageProducer(String brokerList, Properties properties) {
        this.brokerList = brokerList;
        this.properties = properties;
    }

    public void init() throws Exception {
        //读取配置文件的属性（如果有的话）
        Properties confProperties = new Properties();

        try {
            confProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(Configlocation));
        } catch (Exception e) {
            logger.info("there is no kafka-producer.properties file!", e);
        }

        if (properties != null && properties.size() > 0) {
        }

        if (brokerList != null && brokerList.length() > 0) {
            confProperties.put("metadata.broker.list", brokerList);
        }
        //当没有配置文件时，设置最基本的序列化类
        String serializerClass = confProperties.getProperty("serializer.class");
        if (serializerClass == null || serializerClass.length() <= 0) {
            confProperties.put("serializer.class", "kafka.serializer.StringEncoder");
        }

        logger.info("start init kafka clent!confProperties is:" + confProperties);

        ProducerConfig config = new ProducerConfig(confProperties);
        producer = new Producer<String, String>(config);

        logger.info("success init kafka client,properties is:" + confProperties.toString());
    }

    public <T> int sendMessage(T msg, String Topic, String partitionKey) {
        int result = SUCCESS;

        if (msg == null) {
            throw new IllegalArgumentException("message is null");
        }
        if (Topic == null || Topic.trim().equals("")) {
            throw new IllegalArgumentException("topic is null");
        }
        if (partitionKey == null) {
            partitionKey = "";
        }

        long beginTime = System.currentTimeMillis();
        try {
            KeyedMessage<String, String> message = buildMessage(msg, Topic, partitionKey);
            producer.send(message);

            if (logger.isDebugEnabled()) {
                logger.debug("Send Message: msg=" + msg + ", topic=" + Topic + ", partionKey=" + partitionKey);
            }
        } catch (Exception e) {
            logger.error("send Message failed!" + "msg=" + msg + ", topic=" + Topic + ", partionKey=" + partitionKey,
                e);
            result = FAILED;
        }

        return result;
    }

    //如果没有设置partitionKey,默认按时间随机partition
    public <T> int sendMessage(T message, String topic) {
        String partitionKey = "" + System.currentTimeMillis();
        return sendMessage(message, topic, partitionKey);
    }

    public <T> int sendMessages(List<T> msgs, String Topic, String partitionKey) {
        int result = SUCCESS;

        if (msgs == null || msgs.size() < 1) {
            throw new IllegalArgumentException("messages is null");
        }
        if (Topic == null || Topic.trim().equals("")) {
            throw new IllegalArgumentException("topic is null");
        }
        if (partitionKey == null) {
            partitionKey = "";
        }

        long beginTime = System.currentTimeMillis();

        try {
            List<KeyedMessage<String, String>> kms = new ArrayList<KeyedMessage<String, String>>();
            KeyedMessage<String, String> km = null;
            for (T msg : msgs) {
                km = buildMessage(msg, Topic, partitionKey);
                kms.add(km);
            }

            producer.send(kms);
            if (logger.isDebugEnabled()) {
                logger.debug("Send Messages: msgs=" + msgs + ", topic=" + Topic + ", partionKey=" + partitionKey);
            }
        } catch (Exception e) {
            logger.error("send Messages failed!" + "msgs=" + msgs + ", topic=" + Topic + ", partionKey=" + partitionKey,
                e);
            result = FAILED;
        }

        return SUCCESS;

    }

    public <T> int sendMessages(List<T> messages, String Topic) {
        String partitionKey = "" + System.currentTimeMillis();
        return sendMessages(messages, Topic, partitionKey);
    }

    private <T> KeyedMessage<String, String> buildMessage(T msg, String Topic, String partitionKey) throws Exception {

        return new KeyedMessage<String, String>(Topic, partitionKey, String.valueOf(msg));
    }

    public void close() {
        if (producer != null) {
            producer.close();
        }
    }

    public String getBrokerList() {
        return brokerList;
    }

    public void setBrokerList(String brokerList) {
        this.brokerList = brokerList;
    }

    public KafkaMessageProducer() {
        super();
    }

}
