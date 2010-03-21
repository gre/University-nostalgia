package controllers;
import models.*;
import java.util.*;
import play.data.validation.*;

public class Universities extends Secure {
  
	public static void list() {
        List universities = University.findAll();
        /*for(University uy : univerisites)
            universities.add(new UniversityYearInfo(uy, current));
        University
        Post.count("moderated", true);*/
        render(universities);
	}
  
	public static void view(@Required Long id) {
        University university = University.findById(id);
        notFoundIfNull(university);
        List<UniversityYear> universityYears = UniversityYear.find(university,null,null);
        render(university,universityYears);
	}
}
