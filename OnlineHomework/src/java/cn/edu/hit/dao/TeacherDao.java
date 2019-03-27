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
   * 妫�鏌ヨ鑰佸笀鏄惁娉ㄥ唽杩�
   * 
   * @param openID 鐢ㄤ互鍒ゆ柇鏁欏笀鏄惁娉ㄥ唽杩�
   * @return 璇ユ暀甯堟槸鍚︽敞鍐岃繃
   */
  public boolean chechRegistered(String openID) throws SQLException {
    String sql =
        "select teacherID from teacher where openID = '" + openID + "';";
    ResultSet result = db.executeQuery(sql);
    // 濡傛灉缁撴灉涓虹┖锛屽皢娌℃湁next锛岃繑鍥瀎alse
    return result.next();
  }

  /**
   * 娉ㄥ唽鏁欏笀鐨勪俊鎭�
   * 
   * @return 娉ㄥ唽
   */
  public boolean register(String openID, int teacherID, String session,
      String rdSession, String nickName, String subject, String phoneNumber,
      String email, String school, String country, String province,
      String city) {
    String sql = "insert into teacher values ('" + openID + "', " + teacherID
        + ", '" + session + "', '" + rdSession + "', '" + nickName + "', '"
        + subject + "', " + phoneNumber + ", '" + email + "', '" + school
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
   * BY:WTJ 此处有一些问题，
   * teacherID应该是自增的，插入一行自己增加一行，不需要外部输入
   * 然后由于我尚未知道数据库里面数据的排列顺序，所以我设置了数据库插入列和插入顺序无关的插入方式。 
   * 此处修改了Insert语句
   * 
   * 功能：注册信息插入
   * 
   * @param openID
   * @param session
   * @param rdSession
   * @param nickName
   * @param subject
   * @param phoneNumber
   * @param email
   * @param school
   * @param country
   * @param province
   * @param city
   * @return
   */
  public boolean register(String openID, String session, String rdSession,
      String nickName, String subject, String phoneNumber, String email,
      String school, String country, String province, String city) {
    String sql =
        "insert into teacher(openID,session,rdSession,nickName,subject,phoneNumber,email,"
            + "school,country,province,city) values ('" + openID + ", '"
            + session + "', '" + rdSession + "', '" + nickName + "', '"
            + subject + "', " + phoneNumber + ", '" + email + "', '" + school
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
   * 鏇存柊session鍜宺dSession
   * 
   * @param teacherID 瑕佹洿鏂扮殑鏁欏笀鐨刬d
   * @return 杩斿洖鏄惁鏇存柊鎴愬姛
   */
  public boolean refreshSession(String teacherID, String session,
      String rdSession) {
    try {
      String sql = "UPDATE teacher SET session = '" + session
          + "' where teacherID = " + teacherID + ";";
      db.execute(sql);
      sql = "UPDATE teacher SET rdSessionID = '" + rdSession
          + "'where teacherID = " + teacherID + ";";
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
   * 寰楀埌鏁欏笀鎵�鏈変俊鎭�
   * 
   * @param teacherID 閫氳繃鏁欏笀ID寰楀埌淇℃伅
   * @return Teacher绫�
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
        teacher = new Teacher(openID, teacherID, nickName, subject, phoneNumber,
            email, school, country, province, city);
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
   * 寰楀埌褰撳墠璇ヨ�佸笀涓鸿鐝骇甯冪疆鐨勬墍鏈変綔涓氾紙鍖呮嫭瀹屾垚浠ュ強鏈畬鎴愶級
   * 
   * @param classID 鐝骇ID
   * @param teacherID 鏁欏笀ID
   * @return 浣滀笟淇℃伅
   */
  public List<Homework> currentHomework(int classID, int teacherID)
      throws SQLException {
    List<Homework> result = new ArrayList<>();
    String sql = "select * from t_homework where classID = " + classID
        + " and teacherID = " + teacherID + ";";
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
   * 妫�鏌ヤ綔涓氾紝鍚屾椂杩斿洖鏁欏笀鍙戦�佺殑鏍囧噯绛旀锛屼互鍙婂鐢熷～鍐欑殑绛旀
   * 
   * @param date 绛旀鐨勫彂甯冩椂闂�
   * @param teacherID 鍙戝竷绛旀鏁欏笀鐨処D
   * @return 杩斿洖Answer锛屽寘鍚爣鍑嗙瓟妗堬紝 瀛︾敓ID浠ュ強瀵瑰簲鐨勭瓟妗�
   */
  public Answer checkAnswer(Timestamp date, int teacherID) throws SQLException {
    String standardAnswer = "";
    List<String> studentAnswer = new ArrayList<>();
    List<Integer> studentID = new ArrayList<>();
    String sql = "select answer from t_homework where teacherID = " + teacherID
        + "and date = '" + date.toString().split("[.]")[0] + "';";
    ResultSet rs = db.executeQuery(sql);
    while (rs.next()) {
      standardAnswer = rs.getString("answer");
    }

    sql = "select studentID, answer from s_homework where date = '"
        + date.toString().split("[.]")[0] + "';";
    rs = db.executeQuery(sql);
    while (rs.next()) {
      studentAnswer.add(rs.getString("answer"));
      studentID.add(rs.getInt("studentID"));
    }

    return new Answer(standardAnswer, studentID, studentAnswer);
  }

  /**
   * 鑾峰彇鏁欏笀鎵�鏁欏鐨勭彮绾х殑ID
   * 
   * @param teacherID 瑕佹煡璇㈢殑鏁欏笀鐨処D
   * @return 杩斿洖涓�涓狶ist淇℃伅锛屽瓨鍌ㄦ暀甯堝綋鍓嶆暀鎺堢殑鎵�鏈夌彮鍙�
   */
  public List<Integer> getClassID(int teacherID) {
    List<Integer> result = new ArrayList<>();
    String sql =
        "select classID from teaches where teacherID = " + teacherID + ";";
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
   * 杩斿洖鏈氦浣滀笟鐨勫悓瀛︾殑鍚嶅崟
   * 
   * @param date 瑕佹彁閱掔殑浣滀笟
   * @return 鏈笂浜や綔涓氱殑瀛︾敓鐨処D
   */
  public List<Integer> studentsNotHandingInHomework(Timestamp date) {
    List<Integer> result = new ArrayList<>();
    String sql = "select studentID from student where studentID not in ("
        + "select studentID from s_homework where date = '"
        + date.toString().split("[.]")[0] + "');";
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
