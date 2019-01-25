package com.omy.demo.kafka;




import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author: lijiayang
 * @date: 2019-01-19 17:13
 * @description: 生产者
 */

public class KfkProducer extends Thread{
    private String topic;

    private KafkaProducer<String, String> producer;

    public KfkProducer(String topic) {
        this.topic = topic;
        Properties props = new KafkaProperties().getProducerProps();
        producer = new KafkaProducer<>(props);
    }

    public void produce(){
        for (int i = 0;i<40;i++){
            String key = String.valueOf(i);
            String data = "hello : "+key+"kafka message";
            producer.send(new ProducerRecord<String, String>(topic,key,data));
            System.out.println(data);
        }
        producer.close();
    }

    public static void main(String[] args) {
        new KfkProducer("hello").produce();
    }

}
