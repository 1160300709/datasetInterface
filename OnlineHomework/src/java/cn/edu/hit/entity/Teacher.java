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
	
	public Teacher(String openID, int teacherID, String nickName, String subject, int phoneNumber, String email,
			String school, String country, String province, String city) {
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
	
}	

