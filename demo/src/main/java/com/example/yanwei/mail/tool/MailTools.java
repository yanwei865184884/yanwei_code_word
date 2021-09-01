package com.example.yanwei.mail.tool;

import com.example.yanwei.mail.controller.MailController;
import com.example.yanwei.mail.entity.vo.SendUserInfoVO;
import lombok.SneakyThrows;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

/**
 * @author yanwei
 */
public class MailTools {

    /**
     * 发送邮件
     * @param receiver 邮件接收人
     * @param context 邮件内容
     */
    public static void sendMailService(String receiver,String context){
         SendUserInfoVO jsonSendUserInfo = getSendUserInfo();
         String sendMailUserName = jsonSendUserInfo.getSendMailUserName();
         String sendMailPwd = jsonSendUserInfo.getSendMailPwd();
        String substring = sendMailUserName.substring(sendMailUserName.indexOf("@")+1, sendMailUserName.indexOf("."));
        String smtp = "";
        if("qq".equals(substring)){
            smtp = "smtp.qq.com";
        }else{
            throw new RuntimeException();
        }
        // 连接到SMTP服务器587端口:
        Properties props = new Properties();
        // SMTP主机名
        props.put("mail.smtp.host", smtp);
        // 主机端口号
        props.put("mail.smtp.port", "25");
        // 是否需要用户认证
        props.put("mail.smtp.auth", "true");
        // 启用TLS加密
        props.put("mail.smtp.starttls.enable", "true");
        // 获取Session实例:
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendMailUserName, sendMailPwd);
            }
        });
        session.setDebug(false);
        MimeMessage message = new MimeMessage(session);
        // 设置发送方地址:
        try {
            message.setFrom(new InternetAddress(sendMailUserName));
            // 设置接收方地址（QQ、网易、谷歌）:
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            // 设置邮件主题:
            message.setSubject("Hello", "UTF-8");
            // 设置邮件正文:
            message.setText("demo测试", "UTF-8");
            // 发送:
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取邮件发送人账号 密码
     * @return
     */
    public static SendUserInfoVO getSendUserInfo() {
        String file1 = MailController.class.getClassLoader().getResource("json/sendMailUser.properties").getFile();
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(file1));
            Object sendMailUserName = properties.get("sendMailUserName");
            Object sendMailPwd = properties.get("sendMailPwd");
            if(null != sendMailUserName && null != sendMailPwd){
              return new SendUserInfoVO(sendMailUserName.toString(),sendMailPwd.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    /**
     * 新增 邮件发送人与密码
     * @param sendUserInfo
     */
    @SneakyThrows
    public static void updateProperties(SendUserInfoVO sendUserInfo) {
        String staticPath = "json/sendMailUser.properties";
        //清空文件
        String fileName = MailController.class.getClassLoader().getResource(staticPath).getFile();
        clearFile(fileName);
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(fileName));
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(fileName);
            props.setProperty("sendMailUserName",sendUserInfo.getSendMailUserName());
            props.setProperty("sendMailPwd",sendUserInfo.getSendMailPwd());
            // 以适合使用 load 方法加载到 Properties 表中的格式，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "");
        } catch (IOException e) {
            System.err.println("属性文件更新错误");
        }
    }

    /**
     * 清空文件
     * @param filePath
     */
    public static void clearFile(String filePath ){
        File file = new File(filePath);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
