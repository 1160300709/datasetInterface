/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.hit.utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class DBUtils {
    private Connection con;
    public DBUtils() throws ClassNotFoundException, SQLException{
            Class.forName("com.mysql.jdbc.Driver");//启动驱动
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hit","root","root123");//建立一个连接
    }
    
    
    public ResultSet executeQuery(String sql) throws SQLException{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            return rs;
    }
    
    public void execute(String sql) throws SQLException {
    		Statement stmt=con.createStatement();
            stmt.execute(sql);
    		
    }
    
    public void close()
    {
        try{
            con.close();;
        }catch(SQLException ex){
            ex.printStackTrace();
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
