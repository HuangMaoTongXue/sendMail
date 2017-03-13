package sendMail;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by huangmao on 2017/3/10.
 */
public class SendMail {
    private String mailHost;
    private String userAddress;
    private String password;
    private int mailPort;
    private List<String> recipients;
    private String subject;
    private String content;
    SendMail(String mailHost,int mailPort,String userAddress,String password){
        this.mailHost=mailHost;
        this.userAddress=userAddress;
        this.password=userAddress;
        this.mailPort=mailPort;
    }
    SendMail(String mailHost,String userAddress,String password){
        this.mailHost=mailHost;
        this.userAddress=userAddress;
        this.password=password;
        this.mailPort=465;//默认端口为465
    }
    public void sendMail() throws Exception {
        //设置properties
        Properties prop=new Properties();
        prop.put("mail.transport.protocol","smtp");//使用smtp协议
        prop.put("mail.smtp.host",mailHost);//设置smtp服务器
        prop.put("mail.smtp.port",mailPort);//设置服务器端口
        prop.put("mail.smtp.auth","true");//设置需要验证登录
        //使用SSL
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.socketFactory.fallback","false");
        prop.put("mail.smtp.socketFactory.port",mailPort);

        //创建会话
        Session session=Session.getDefaultInstance(prop,new MyAuthenticator(userAddress,password));
        //创建接受者地址
        if (recipients==null||recipients.size()<=0){
            throw new Exception("recipients is null");
        }
        Address[] recipientAddr=new Address[recipients.size()];
        for (int i=0;i<recipients.size();i++){
            recipientAddr[i]=new InternetAddress(recipients.get(i));
        }
        //创建邮件
        MimeMessage mail=MiMeMessageFactory.createMiMeMessage(session,userAddress,recipientAddr,subject,content);
        //创建连接
        Transport transport=session.getTransport();
        transport.connect(mailHost,mailPort,userAddress,password);//建立连接
        transport.sendMessage(mail,recipientAddr);
        transport.close();//关闭连接
    }
    public void setRecipients(List<String> recipients){
        this.recipients=recipients;
    }
    public void setSubject(String subject){
        this.subject=subject;
    }
    public void setContent(String content){
        this.content=content;
    }

    public static void main(String[] args){
        SendMail sendMail=new SendMail("smtp.exmail.qq.com","huangmao@yuewen.com","Hm918121@oa");
        sendMail.setSubject("send by me");
        List<String> recipients=new ArrayList<>();
        recipients.add("huangmao@yuewen.com");
        sendMail.setRecipients(recipients);
        sendMail.setContent("succeed");
        try {
            sendMail.sendMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
