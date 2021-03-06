package cn.edu.hit.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import cn.edu.hit.entity.*;
import cn.edu.hit.utils.DBUtils;

public class TeacherDao {

	DBUtils db = null;

	public TeacherDao() {
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
	 * 检查该老师是否注册过
	 * 
	 * @param openID
	 *            用以判断教师是否注册过
	 * @return 该教师是否注册过
	 */
	public boolean chechRegistered(String openID) throws SQLException {
		String sql = "select teacherID from teacher where openID = '" + openID + "';";
		ResultSet result = db.executeQuery(sql);
		// 如果结果为空，将没有next，返回false
		return result.next();
	}

	/**
	 * 注册教师的信息
	 * 
	 * @return 注册
	 */
	public boolean register(String openID, int teacherID, String session, String rdSession, String nickName,
			String subject, String phoneNumber, String email, String school, String country, String province,
			String city) {
		String sql = "insert into teacher values ('" + openID + "', " + teacherID + ", '" + session + "', '" + rdSession
				+ "', '" + nickName + "', '" + subject + "', " + phoneNumber + ", '" + email + "', '" + school + "', '"
				+ country + "', '" + province + "', '" + city + "');";
		try {
			db.execute(sql);
		} catch (SQLException ex) {
			ex.printStackTrace();
			// Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE,
			// null, ex);
			return false;
		}
		return true;
	}

	/**
	   * BY:WTJ 此处有一些问题，
	   * teacherID应该是自增的，插入一行自己增加一行，不需要外部输入
	   * 然后由于我尚未知道数据库里面数据的排列顺序，所以我设置了数据库插入列和插入顺序无关的插入方式。 
	   * 此处修改了Insert语句
	   * 
	   * 功能：注册信息插入
	   * 
	   * @return
	   */
	  public boolean register(String openID, String session, String rdSession,
	      String nickName, String subject, String phoneNumber, String email,
	      String school, String country, String province, String city) {
	    String sql =
	        "insert into teacher(openID,session,rdSession,nickName,subject,phoneNumber,email,"
	            + "school,country,province,city) values('" + openID + "', '"
	            + session + "', '" + rdSession + "', '" + nickName + "', '"
	            + subject + "', '" + phoneNumber + "', '" + email + "', '" + school
	            + "', '" + country + "', '" + province + "', '" + city + "');";
	    try {
	      db.execute(sql);
	    } catch (SQLException ex) {
	      ex.printStackTrace();
	      // Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE,
	      // null, ex);
	      return false;
	    }
	    return true;
	  }


	  /**
	   * byWTJ注册基础信息,也就是微信给的那些和openid和rdseesion等
	   *
	   */
	  public boolean registerBasic(String openID, String session, String rdSession,
	      String nickName, String country, String province, String city) {
	    String sql = "insert into teacher(openID,session,rdSession,nickName,"
	        + "country,province,city) values('" + openID + "', '" + session + "', '"
	        + rdSession + "', '" + nickName + "', '" + country + "', '" + province
	        + "', '" + city + "');";
	    try {
	      System.out.println(sql);
	      db.execute(sql);
	    } catch (SQLException ex) {
	      ex.printStackTrace();
	      // Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE,
	      // null, ex);
	      return false;
	    }
	    return true;
	  }

	  /**
	   * Bywtj注册老师进阶信息，需要用户填写的那些信息，也就是学校，联系电话等等四个信息。
	   */
	  public boolean registerFillIn(String teacherID, String subject,
	      String phoneNumber, String email, String school) {
	    String sql = "update teacher set d='" + subject + "',phoneNumber='"
	        + phoneNumber + "',email='" + email + "',school='" + school
	        + "' where teacherID=" + teacherID + ";";
	    try {
	      db.execute(sql);
	    } catch (SQLException ex) {
	      ex.printStackTrace();
	      // Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE,
	      // null, ex);
	      return false;
	    }
	    return true;
	  }


	/**
	 * 为老师添加教学班
	 */
	public void teachClass(int teacherID, int classID) {
		String sql = "insert into teaches (teacherID, classID) values (" + teacherID + ", " + classID + ");";
		try {
			db.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新session和rdSession
	 * 
	 * @param teacherID
	 *            要更新的教师的id
	 * @return 返回是否更新成功
	 */
	public boolean refreshSession(String teacherID, String session, String rdSession) {
		try {
			String sql = "UPDATE teacher SET session = '" + session + "' where teacherID = " + teacherID + ";";
			db.execute(sql);
			sql = "UPDATE teacher SET rdSessionID = '" + rdSession + "'where teacherID = " + teacherID + ";";
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
	 * 得到教师所有信息
	 * 
	 * @param teacherID
	 *            通过教师ID得到信息
	 * @return Teacher类
	 */
	public Teacher getInfo(int teacherID) {
		Teacher teacher = null;
		try {
			String sql = "select * from teacher where teacherID = " + teacherID + ";";
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				String openID = rs.getString("openID");
				String nickName = rs.getString("nickName");
				String subject = rs.getString("subject");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				String school = rs.getString("school");
				String country = rs.getString("country");
				String province = rs.getString("province");
				String city = rs.getString("city");
				teacher = new Teacher(openID, teacherID, nickName, subject, phoneNumber, email, school, country,
						province, city);
			}
			return teacher;
		} catch (SQLException ex) {
			ex.printStackTrace();
			// Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE,
			// null, ex);
			return teacher;
		}

	}

	/**
	 * 得到当前该老师为该班级布置的所有作业（包括完成以及未完成）
	 * 
	 * @param classID
	 *            班级ID
	 * @param teacherID
	 *            教师ID
	 * @return 作业信息
	 */
	public List<Homework> currentHomework(int classID, int teacherID) throws SQLException {
		List<Homework> result = new ArrayList<>();
		String sql = "select * from t_homework where classID = " + classID + " and teacherID = " + teacherID + ";";
		ResultSet rs = db.executeQuery(sql);

		while (rs.next()) {
			Timestamp date = rs.getTimestamp("date");
			Timestamp deadline = rs.getTimestamp("deadline");
			String answer = rs.getString("answer");
			int cutoff = rs.getInt("cutoff");

			result.add(new Homework(date, deadline, answer, cutoff));
		}

		return result;
	}

	/**
	 * 检查作业，同时返回教师发送的标准答案，以及学生填写的答案
	 * 
	 * @param date
	 *            答案的发布时间
	 * @param teacherID
	 *            发布答案教师的ID
	 * @return 返回Answer，包含标准答案， 学生ID以及对应的答案
	 */
	public Answer checkAnswer(Timestamp date, int teacherID) throws SQLException {
		String standardAnswer = "";
		List<String> studentAnswer = new ArrayList<>();
		List<Integer> studentID = new ArrayList<>();
		String sql = "select answer from t_homework where teacherID = " + teacherID + "and date = '"
				+ date.toString().split("[.]")[0] + "';";
		ResultSet rs = db.executeQuery(sql);
		while (rs.next()) {
			standardAnswer = rs.getString("answer");
		}

		sql = "select studentID, answer from s_homework where date = '" + date.toString().split("[.]")[0] + "';";
		rs = db.executeQuery(sql);
		while (rs.next()) {
			studentAnswer.add(rs.getString("answer"));
			studentID.add(rs.getInt("studentID"));
		}

		return new Answer(standardAnswer, studentID, studentAnswer);
	}

	/**
	 * 获取教师所教导的班级的ID
	 * 
	 * @param teacherID
	 *            要查询的教师的ID
	 * @return 返回一个List信息，存储教师当前教授的所有班号
	 */
	public List<Integer> getClassID(int teacherID) {
		List<Integer> result = new ArrayList<>();
		String sql = "select classID from teaches where teacherID = " + teacherID + ";";
		try {
			ResultSet rs = db.executeQuery(sql);

			while (rs.next()) {
				result.add(rs.getInt("classID"));
			}
			return result;
		} catch (SQLException ex) {
			ex.printStackTrace();
			// Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE,
			// null, ex);
			return null;
		}
	}

	/**
	 * 教师布置一份作业
	 * 
	 * @param classID
	 *            要布置作业的班级
	 * @param answer
	 *            答案内容
	 * @param date
	 *            该作业布置的时间（ID）
	 * @param deadline
	 *            该作业的截止日期
	 */
	public void handInAnswer(int teacherID, int classID, String answer, Timestamp date, Timestamp deadline) {
		String sql = "insert into t_homework (date, teacherID, answer, classID, deadline, cutoff) values ("
				+ date.toString().split("[.]")[0] + ", " + teacherID + ", '" + answer + "', " + classID + ", "
				+ deadline.toString().split("[.]")[0] + ", 0)";
		try {
			db.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回未交作业的同学的名单
	 * 
	 * @param date
	 *            要提醒的作业
	 * @return 未上交作业的学生的ID
	 */
	public List<Integer> studentsNotHandingInHomework(Timestamp date) {
		List<Integer> result = new ArrayList<>();
		String sql = "select studentID from student where studentID not in ("
				+ "select studentID from s_homework where date = '" + date.toString().split("[.]")[0] + "');";
		try {
			ResultSet rs = db.executeQuery(sql);

			while (rs.next()) {
				result.add(rs.getInt("studentID"));
			}

			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			// Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE,
			// null, ex);
			return null;
		}
	}

}
