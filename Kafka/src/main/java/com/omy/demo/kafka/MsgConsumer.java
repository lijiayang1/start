package com.omy.demo.kafka;

import org.apache.ibatis.ognl.ASTRootVarRef;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.management.loading.MLet;
import java.util.*;

/**
 * @author: lijiayang
 * @date: 2019-01-23 15:15
 * @description:
 */

@Component
public class MsgConsumer {

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private String enableAutoCommit;
    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeserializer;
    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeserializer;
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private String autoCommitInternal;
    @Value("${spring.kafka.consumer.session-timeout-ms}")
    private String sessionTimeoutMs;

    public KafkaConsumer<String, String> createConsumer(){
        Properties props = new Properties();
        props.put("group.id",this.groupId);
        props.put("auto.offset.reset",this.autoOffsetReset);
        props.put("enable.auto.commit",this.enableAutoCommit);
        props.put("key.deserializer",this.keyDeserializer);
        props.put("value.deserializer",this.valueDeserializer);
        props.put("bootstrap.servers",this.bootstrapServers);
        props.put("auto.commit.interval",this.autoCommitInternal);
        props.put("session.timeout.ms",this.sessionTimeoutMs);
        return new KafkaConsumer<String, String>(props);
    }

    public List<Map<String, String>> consumer(String topic){
        KafkaConsumer<String, String> consumer = this.createConsumer();
        consumer.subscribe(Arrays.asList(topic));
        Map<String, String> map = null;
        List<Map<String, String>> list = new ArrayList<>();
        while(true){
            ConsumerRecords<String, String> crs = consumer.poll(100);

            for(ConsumerRecord<String, String> cr : crs){
                map = new HashMap<>();
                map.put("offfset", String.valueOf(cr.offset()));
                map.put("key", cr.key());
                map.put("value", cr.value());
                list.add(map);
                System.out.println("timestamp: "+cr.timestamp()+" offset: "+cr.offset()+" key: "+cr.key()+" value: "+cr.value());
            }
            consumer.commitAsync();

            if(list.size()!=0){
               return list;
            }

            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
