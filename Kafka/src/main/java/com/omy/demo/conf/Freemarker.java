package com.omy.demo.conf;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author: lijiayang
 * @date: 2019-01-23 11:19
 * @description:
 */


@org.springframework.context.annotation.Configuration
public class Freemarker extends HttpServlet {
    private static Configuration config;

   static {
        config = new Configuration(Configuration.getVersion());
        String path = Freemarker.class.getClassLoader().getResource("").getPath();
        path = path + "/templates/";
        File file  = new File(path);
        try {
            config.setDirectoryForTemplateLoading(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Template getTemplate(String filename){
        Template template = null;
        try {
            template = config.getTemplate(filename+".ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return template;
    }

    public static void forward(HttpServletRequest req, HttpServletResponse res, String filename, Map map){
        Template template = Freemarker.getTemplate(filename);
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html");
        PrintWriter out = null;
        try {
            out = res.getWriter();
            template.process(map, out);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(out != null) {
                out.close();
            }
        }

    }

}
