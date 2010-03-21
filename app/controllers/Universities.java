package controllers;
import models.*;
import java.util.*;
import play.data.validation.*;

public class Universities extends Secure {
  
	public static void list() {
        render();
	}
  
	public static void view(@Required Long id) {
        University university = University.findById(id);
        notFoundIfNull(university);
        List<UniversityYear> universityYears = UniversityYear.find(university,null,null);
        render(university,universityYears);
	}
}
