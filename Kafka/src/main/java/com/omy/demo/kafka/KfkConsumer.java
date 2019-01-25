package com.omy.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author: lijiayang
 * @date: 2019-01-19 17:13
 * @description: 消费者
 */

public class KfkConsumer {
    private static KafkaConsumer<String, String> consumer;
    private final static String topic = "hello";

    KfkConsumer(){
        Properties props = new KafkaProperties().getConsumerProperties();
        consumer = new KafkaConsumer<String, String>(props);
    }

    public void consume(){
        consumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records){
                System.out.printf("offset = %d, key = %s, value = %s",record.offset(), record.key(), record.value());
                System.out.println();
            }
            consumer.commitAsync();


        }
    }

    public static void main(String[] args) {
        new KfkConsumer().consume();
    }

}
