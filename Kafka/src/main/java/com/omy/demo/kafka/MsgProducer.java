package com.omy.demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author: lijiayang
 * @date: 2019-01-23 14:54
 * @description:
 */
@Component
public class MsgProducer {

    @Value("${spring.kafka.producer.retries}")
    private String retries;
    @Value("${spring.kafka.producer.batch-size}")
    private String batchSize;
    @Value("${spring.kafka.producer.buffer-memory}")
    private String bufferMemory;
    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;
    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.producer.acks}")
    private String acks;

    public KafkaProducer<String, String> createProducer(){
        KafkaProducer<String, String> template;
        Properties props = new Properties();
        props.put("retries",this.retries);
        props.put("batch.size",this.batchSize);
        props.put("buffer.memory",this.bufferMemory);
        props.put("key.serializer",this.keySerializer);
        props.put("value.serializer",this.valueSerializer);
        props.put("bootstrap.servers",this.bootstrapServers);
        props.put("acks",this.acks);
        return new KafkaProducer<String, String>(props);
    }

    public void sent(String topic, String key, String data){
        KafkaProducer<String, String> producer = this.createProducer();
        producer.send(new ProducerRecord<>(topic, key, data));
        if(producer != null){
            producer.close();
        }
    }
}
