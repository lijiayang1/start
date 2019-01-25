package com.omy.demo.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

/**
 * @author: lijiayang
 * @date: 2019-01-21 18:18
 * @description:
 */

public class test {
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String servers;

    @Test
    public void ok(){
        Properties props = new Properties();
        props.put("servers",servers);
        System.out.println(props.get(servers));
    }

    public static void main(String[] args) {
       test t = new test();
        System.out.println(t.servers);
    }
}
