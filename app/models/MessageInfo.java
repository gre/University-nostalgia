package models;

import java.util.Date;


public class MessageInfo {
    public Long id;
    
    public String content;
    
    public UserInfo sender;
    public UserInfo receiver;
    
    public Date date;
    
    public MessageInfo(Message m) {
        id = m.id;
        content = m.content;
        sender = new UserInfo(m.sender);
        receiver = new UserInfo(m.receiver);
        date = m.date;
    }
}
