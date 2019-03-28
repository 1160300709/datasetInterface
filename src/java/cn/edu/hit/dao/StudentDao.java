package cn.edu.hit.dao;

import java.sql.*;
import java.util.*;
import cn.edu.hit.entity.*;
import cn.edu.hit.utils.DBUtils;

public class StudentDao {
	DBUtils db = null;

	public StudentDao() {
		try {
			db = new DBUtils();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(-2);
		}
	}

	/**
	 * 检查该学生是否已经注册过
	 * 
	 * @param openID
	 *            输入该学生的openID
	 * @return true:注册过 false：未注册过
	 */
	public boolean checkRegister(String openID) {
		String sql = "select studentID from student where openID = '" + openID + "';";
		ResultSet result;
		try {
			result = db.executeQuery(sql);
			// 如果结果为空，将没有next，返回false
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 插入新的学生的信息
	 * 
	 * @return 如果插入成功，返回true
	 */
	public boolean register(String openID, int studentID, String session, String rdSession, String nickName,
			String name, String phoneNumber, String school, String country, String province, String city) {
		String sql = "insert into student values ('" + openID + "', " + studentID + ", '" + session + "', '" + rdSession
				+ "', '" + name + "', '" + phoneNumber + "', '" + school + "', '" + country + "', '" + province + "', '"
				+ city + "');";
		try {
			db.execute(sql);
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			// Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE,
			// null, ex);
			return false;
		}
	}

	/**
	 * 学生选课，studentID选择classID
	 */
	public void addClass(int studentID, int classID) {
		String sql = "insert into class (studentID, classID) values (" + studentID + ", " + classID + ");";
		try {
			db.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 学生提交作业
	 * @param date 该作业的时间(ID)
	 * @param answer 学生的答案
	 * @param studentID 学生的ID
	 */
	public void handInHomework(Timestamp date, String answer, int studentID) {
		String sql = "insert into s_homework (date, studentID, answer) values ('" + date.toString().split("[.]")[0]
				+ "', " + studentID + ", '" + answer + "');";
		try {
			db.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示学生的得分情况，返回一个Answer类，其中StudentID只有该学生一人，StudentAnswer也只有该学生的answer，通过这个结果得到想要的数据统计
	 * 
	 * @param checkAnswer
	 *            输入全班的Answer结果，从中找到该学生的answer
	 * @param studentID
	 *            要查询作业的学生的ID
	 * @return 返回一个Answer，存储该学生ID和answer
	 */
	public Answer checkMyAnswer(Answer checkAnswer, int studentID) {
		int index = checkAnswer.getStudentID().indexOf(studentID);
		List<Integer> ID = new ArrayList<>();
		ID.add(studentID);
		List<String> answer = new ArrayList<>();
		answer.add(checkAnswer.getStudentAnswer().get(index));
		Answer result = new Answer(checkAnswer.getStandardAnswer(), ID, answer);
		return result;
	}

}
