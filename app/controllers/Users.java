package controllers;
import java.io.File;

import play.vfs.VirtualFile;

public class Users extends Secure {
  
	public static void view(Long id) {
        render();
	}
	
	public static void avatar(Long id) {
	    VirtualFile avatar = VirtualFile.fromRelativePath("/data/users/"+id+"/avatar");
	    renderBinary(avatar.exists() ? avatar.getRealFile() : VirtualFile.fromRelativePath("/public/images/avatar.png").getRealFile(), "avatar");
	}
}
