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
        User connected = connectedUser();
        notFoundIfNull(university);
        List<UniversityYearInfo> universityYears = new ArrayList<UniversityYearInfo>();
        for(UniversityYear uy : UniversityYear.find(university,null,null))
          universityYears.add(new UniversityYearInfo(uy, connected));
        Map<Long, List<UniversityYearInfo>> universityYearsByYear = new TreeMap<Long, List<UniversityYearInfo>>();
        for(UniversityYearInfo uyi : universityYears) {
          List<UniversityYearInfo> uyis;
          uyis = universityYearsByYear.get(uyi.year);
          if(uyis==null) {
            uyis = new ArrayList<UniversityYearInfo>();
            universityYearsByYear.put(uyi.year, uyis);
          }
          uyis.add(uyi);
        }
        render(university,universityYears, universityYearsByYear);
	}
}
