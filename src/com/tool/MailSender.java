package com.tool;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by FuFangzhou on 2017/5/19.
 */
public class MailSender {
    private String title;
    private String receiver;
    private String content;


    public static final String SENDERADDRESS = "316585692@qq.com";
    public static final String SENDERPASSWORD = "qbojangkeiusbgcb";
    public static final String CHARSET = "UTF-8";
    public static final String HTMLCHARSET = "text/html;charset=UTF-8";
    public static final String SMTPADDRESS = "smtp.qq.com";
    public static final String SENDER = "URJACK";

    public MailSender(){

    }

    public static String getCheckNumber(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int r = (int) (Math.random() * 123 % 10);
            sb.append(r);
        }
        return sb.toString();
    }

    public void sendEmail() throws Exception {

        final String smtpPort = "465";
        Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.host", SMTPADDRESS);
        prop.setProperty("mail.smtp.auth", "true");

        prop.setProperty("mail.smtp.port", smtpPort);
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.socketFactory.port", smtpPort);

        Session session = Session.getDefaultInstance(prop);
        session.setDebug(true);

        MimeMessage message = createMineMessage(session, SENDERADDRESS, receiver);

        Transport transport = session.getTransport();

        transport.connect(SENDERADDRESS, SENDERPASSWORD);

        transport.sendMessage(message, message.getAllRecipients());

        transport.close();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private MimeMessage createMineMessage(Session session, String senderaddress, String receiveraddress) throws Exception {
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(senderaddress, SENDER, CHARSET));

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveraddress, SENDER, CHARSET));

        message.setSubject(this.title);

        message.setContent(content, HTMLCHARSET);

        message.setSentDate(new Date());

        message.saveChanges();

        return message;
    }
}
