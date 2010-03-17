package models;

import java.util.Date;


public class MessageInfo {
    public Long id;
    
    public String content;
    
    public UserInfo sender;
    public UserInfo receiver;
    
    public Date date;
    
    public MessageInfo(Message m, User me) {
        id = m.id;
        content = m.content;
        sender = new UserInfo(m.sender, me);
        receiver = new UserInfo(m.receiver, me);
        date = m.date;
    }
}
