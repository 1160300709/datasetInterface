package tmp;

import cn.edu.hit.dao.TeacherDao;

/**
 * by wtj 测试接口
 * 
 * @author dell
 *
 */
public class Main {


  public static void main(String[] args) {
    TeacherDao test = new TeacherDao();
    test.register("test", "test", "test", "test", "test", "test", "test",
        "test", "test", "test", "test");
  }


}
