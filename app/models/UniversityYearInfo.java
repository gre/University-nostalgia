package models;

import java.util.List;
import java.util.ArrayList;


public class UniversityYearInfo {
  public Long id;
  public University university;
  public Long year;
  public Speciality speciality;
  public Diploma diploma;
  public UserInfo user;
  
  public UniversityYearInfo(UniversityYear uy, User connectedUser) {
        id = uy.id;
    university = uy.university;
    year = uy.year;
    speciality = uy.speciality;
    diploma = uy.diploma;
    user = new UserInfo(uy.user, connectedUser);
  }
  
  public static List<UniversityYearInfo> parse(List<UniversityYear> uys, User connectedUser) {
    List<UniversityYearInfo> uyi = new ArrayList<UniversityYearInfo>();
    for(UniversityYear uy : uys)
      uyi.add(new UniversityYearInfo(uy, connectedUser));
    return uyi;
  }
}
