package controllers;
import play.mvc.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

import models.*;

import play.data.validation.*;
import play.i18n.Messages;
import play.libs.Images;
import play.vfs.VirtualFile;

public class Profile extends Secure {
	
    @Before
    static void profileGlobals() {
      User me = connectedUser();
      if(me!=null)
        renderArgs.put("connectedUserInfo", new UserInfo(me, me));
    }
  
    public static void index() {
        infos();
    }
    
	public static void infos() {
        render();
    }

    public static void avatar() {
        render();
    }
    
    public static void password() {
        render();
    }
    
    public static void formation() {
        render();
    }
  
    public static void editInfos(
            @Required String firstname, 
            @Required String lastname, 
            @Required @Email String email, 
            String phone, 
            String address) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            informError();
        }
        else {
            User user = connectedUser();
            user.firstname = firstname;
            user.lastname = lastname;
            user.email = email;
            user.phone = phone;
            user.address = address;
            user.save();
            informSuccess();
        }
        infos();
    }
  
    public static void editAvatar(@Required File avatar) throws IOException {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            informError();
        }
        else {
            try {
                File userDir = VirtualFile.fromRelativePath("/data/users/"+connectedUser().id+"/").getRealFile();
                if(!userDir.exists())
                    userDir.mkdirs();
                Images.resize(avatar, new File(userDir, "avatar"), 96, 96);
            }
            catch(Exception e) {
                informError();
                index();
            }
            informSuccess();
        }
        avatar();
    }
  
    public static void editPassword(
            @Required String currentPwd,
            @Required @MinSize(5) String password, 
            @Equals("password") String password2) {
        User user = connectedUser();
        boolean badPassword = !currentPwd.equals(user.password);
        if (validation.hasErrors() || badPassword) {
            if(badPassword)
                validation.addError("currentPwd", Messages.get("validation.badpassword"));
            validation.keep();
            params.flash();
            informError();
        }
        else {
            user.password = password;
            user.save();
            informSuccess();
        }
        password();
    }
    public static void editFormation() {
        
    }

}
