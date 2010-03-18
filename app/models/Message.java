package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Message extends Model {
    
    @Lob
    public String content;
    
    @ManyToOne
    public User sender;
    
    @ManyToOne
    public User receiver;
    
    public Date date;
    
    public boolean deleteBySender;
    public boolean deleteByReceiver;
    
    public Message(User sender, User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = new Date();
        deleteBySender = false;
        deleteByReceiver = false;
    }
    
    public boolean userConcerned(Long userid) {
        return userid==sender.id || userid==receiver.id;
    }
    
    public static List<Message> userSendbox(Long userid) {
        return find("sender.id = ?1 and deleteBySender = FALSE order by date desc", userid).fetch();
    }
    public static List<Message> userInbox(Long userid) {
        return find("receiver.id = ?1 and deleteByReceiver = FALSE order by date desc", userid).fetch();
    }
}