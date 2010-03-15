package models;


public class MessageInfo {
    public Long id;
    
    public String content;
    
    public UserInfo sender;
    public UserInfo receiver;
    
    public MessageInfo(Message m) {
        id = m.id;
        content = m.content;
        sender = new UserInfo(m.sender);
        receiver = new UserInfo(m.receiver);
    }
}
