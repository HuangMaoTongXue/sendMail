package sendMail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by huangmao on 2017/3/10.
 */
public class MyAuthenticator extends Authenticator {
    private String userName;
    private String password;
    MyAuthenticator(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName,password);
    }
}
