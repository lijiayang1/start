package com.omy.demo.controller;

import com.omy.demo.conf.Freemarker;
import com.omy.demo.kafka.MsgConsumer;
import com.omy.demo.kafka.MsgProducer;
import com.omy.demo.model.Comment;
import com.omy.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lijiayang
 * @date: 2019-01-21 19:26
 * @description:
 */

@Controller
@RequestMapping(value = "/test")
public class controller {
    @Autowired
    private CommentService commentService;
    @Autowired
    private MsgProducer producer;
    @Autowired
    private MsgConsumer consumer;

    @RequestMapping(value = "/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response){
        String hello = request.getParameter("hello");
        List<Comment> list;
        list = commentService.getAllComment();
        Map map = new HashMap();
        map.put("listComment",list);
        producer.sent("hello","hello",hello);
        List<Map<String, String>> list1= consumer.consumer("hello");
        System.out.println("niaho fwfwe:"+list1.get(0).get("value"));
        Freemarker.forward(request, response,"show", map);
        System.out.println(hello);
    }
}
