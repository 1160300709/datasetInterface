package cn.edu.hit.entity;

public class Teacher {
  String openID;
  int teacherID;
  String nickName;
  String subject;
  int phoneNumber;
  String email;
  String school;
  String country;
  String province;
  String city;

  public Teacher(String openID, int teacherID, String nickName, String subject,
      int phoneNumber, String email, String school, String country,
      String province, String city) {
    this.openID = openID;
    this.teacherID = teacherID;
    this.nickName = nickName;
    this.subject = subject;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.school = school;
    this.country = country;
    this.province = province;
    this.city = city;
  }

  public String getOpenID() {
    return openID;
  }

  public void setOpenID(String openID) {
    this.openID = openID;
  }

  public int getTeacherID() {
    return teacherID;
  }

  public void setTeacherID(int teacherID) {
    this.teacherID = teacherID;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }



}

