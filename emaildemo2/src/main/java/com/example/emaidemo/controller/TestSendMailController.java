package com.example.emaidemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Controller
@RequestMapping("/email")
public class TestSendMailController {

    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private  String sendermail;

    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping("/sendMail")
    @ResponseBody
    public String sendMail(){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sendermail);
            simpleMailMessage.setTo("918126051@qq.com");
            simpleMailMessage.setSubject("一封简单的邮件");
            simpleMailMessage.setText("感谢你收到此封邮件");
            jms.send(simpleMailMessage);
            return "发送成功";
        }catch (Exception e){
            e.printStackTrace();
            return  e.getMessage();
        }

    }
    @RequestMapping("/sendhmail")
    @ResponseBody
    public String sendHtmlMail(){
        MimeMessage mimeMessage = null;
        try{
            mimeMessage = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(sendermail);
            helper.setTo("918126051@qq.com");
            helper.setSubject("一个链接");
            StringBuffer sb = new StringBuffer("<a href='https://www.baidu.com'>百度</a>");
            helper.setText(sb.toString(),true);
            jms.send(mimeMessage);
            return "发送成功";
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    @RequestMapping("/scmail")
    @ResponseBody
    public String sendContextMail(String code){
        MimeMessage message = null;
        try{
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(sendermail);
            helper.setTo("918126051@qq.com");
            helper.setSubject("一封有内容的邮件");
            Context context = new Context();
            context.setVariable("code",code);
            String template = templateEngine.process("a",context);
            helper.setText(template,true);
            jms.send(message);
            return "成功";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
