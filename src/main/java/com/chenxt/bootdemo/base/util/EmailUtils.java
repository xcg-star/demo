package com.chenxt.bootdemo.base.util;

import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 发送邮件工具类
 *
 * @author chenxt
 * @date 2020/06/16
 */
@Slf4j
public class EmailUtils {

    /**
     * 异步发送邮件
     *
     * @param to      收件人邮箱
     * @param subject 标题
     * @param text    内容
     */
    public static void sendAsync(String to, String subject, String text) {
        CommonThreadPool.execute(() -> {
            send(to, subject, text);
            log.info("邮件发送成功!");
        });
    }

    private static void send(String to, String subject, String text) {
        // 发件人电子邮箱
        String from = "506195407@qq.com";
        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        // 获取默认session对象
        Session session = Session.getInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "vvbppiotuxstbifb"); //发件人邮件用户名、授权码
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from, "你的小可爱", "UTF-8"));
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            // Set Subject: 头部头字段
            message.setSubject(subject);
            // 设置消息体
            message.setText(text);
            // 设置格式
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送消息
            Transport.send(message);
        } catch (MessagingException | UnsupportedEncodingException mex) {
            mex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        send("506195407@qq.com","123","test1");
    }
}
