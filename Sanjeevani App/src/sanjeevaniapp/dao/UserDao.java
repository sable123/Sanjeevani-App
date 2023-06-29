/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static sanjeevaniapp.dao.EmpDao.updateName;
import sanjeevaniapp.dbutil.DBConnection;
import sanjeevaniapp.pojo.EmpPojo;
import sanjeevaniapp.pojo.User;
import sanjeevaniapp.pojo.UserPojo;

/**
 *
 * @author ABC
 */
public class UserDao {
    public static String validateUser(User user) throws SQLException{
         Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("Select user_name from users where login_id=? and password=? and user_type=?");
         ps.setString(1, user.getLoginId());
         ps.setString(2,user.getPassword());
         ps.setString(3, user.getUserType());
         ResultSet rs=ps.executeQuery();
         String name=null;
         if(rs.next()){
             name=rs.getString("user_name");
         }
         return name;
   }
     public static void updateName(String currName,String newName)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Update users set user_name=? where user_name=?");
     ps.setString(1, newName);
     ps.setString(2, currName);
     ps.executeUpdate();
}
      public static void removeName(String Name)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("delete from users where user_name=?");
     ps.setString(1,Name);
     
     ps.executeUpdate();
}
      
   public static boolean updateReceptionist(UserPojo user)throws SQLException{
    
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Update users set password=?,login_id=? where user_type=? and user_name=?");
     ps.setString(1, user.getUserName());
     ps.setString(2, user.getPassword());
     ps.setString(3, user.getLoginId());
     ps.setString(4, user.getUserType().toUpperCase());
     return ps.executeUpdate()==1;
 }   
      
       public static boolean addUser(UserPojo user)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Insert into users values(?,?,?,?)");
     ps.setString(1,user.getLoginId());
     ps.setString(2,user.getUserName());
     ps.setString(3,user.getPassword());
     ps.setString(4, user.getUserType().toUpperCase());
     return ps.executeUpdate()==1;
}
      public static void deleteUserByName(String name)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("delete from users where user_name=?");
     ps.setString(1, name);
     ps.executeUpdate();
}
}
