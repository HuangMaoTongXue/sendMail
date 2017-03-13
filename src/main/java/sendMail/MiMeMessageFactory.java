package sendMail;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by huangmao on 2017/3/10.
 */
public class MiMeMessageFactory {
    MiMeMessageFactory(){}
    public static MimeMessage createMiMeMessage(Session session, String sender, Address[] recipients,String subject,String context) throws MessagingException {
        MimeMessage message=new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO,recipients);//设置收件人
        message.setFrom(new InternetAddress(sender));//设置发件人
        message.setSubject(subject);//设置邮件主题
        message.setContent(context,"text/html;charset=UTF-8");//设置邮件正文
        message.saveChanges();//保存邮件
        return message;
    }//end of createMiMeMessage
}//end of class
