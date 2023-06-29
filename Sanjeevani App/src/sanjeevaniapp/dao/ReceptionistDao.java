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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static sanjeevaniapp.dao.EmpDao.updateName;
import sanjeevaniapp.dbutil.DBConnection;
import sanjeevaniapp.pojo.EmpPojo;
import sanjeevaniapp.pojo.ReceptionistPojo;

/**
 *
 * @author ABC
 */
public class ReceptionistDao {
    public static void updateName(String currName,String newName)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Update receptionists set receptionist_name=? where receptionist_name=?");
     ps.setString(1, newName);
     ps.setString(2, currName);
     ps.executeUpdate();
}
    public static void removeName(String Name)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("delete from Receptionists where receptionist_name=?");
     ps.setString(1,Name);
    
     ps.executeUpdate();
}
    
    public static String getNewRecId()throws  SQLException{
   Connection conn=DBConnection.getConnection();
   Statement st=conn.createStatement();
   ResultSet rs=st.executeQuery("Select max(receptionist_id) from receptionists");
   rs.next();
   int recId=101;
   String id=rs.getString(1);
   if(id!=null){
       String num=id.substring(3);
       recId=Integer.parseInt(num)+1;
    }
   return "REC"+recId;
}      
  public static boolean addReceptionist(ReceptionistPojo rec)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Insert into receptionists values(?,?,?)");
     ps.setString(1,rec.getReceptionistId());
     ps.setString(2,rec.getReceptionistName());
     ps.setString(3,rec.getGender());
     
     return ps.executeUpdate()==1;
}
  
  public static ReceptionistPojo getReceptionistDetails(String RecId)throws SQLException{
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("Select * from receptionists where receptionist_id=?");
    ps.setString(1,RecId);
    ResultSet rs=ps.executeQuery();
    rs.next();
    ReceptionistPojo emp=new ReceptionistPojo();
    emp.setReceptionistId(rs.getString(1));
    emp.setReceptionistName(rs.getString(2));
    emp.setGender(rs.getString(3));
    return emp;
    
 } 
  
  public static List<String> getAllReceptionistId()throws SQLException{
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select receptionist_id from Receptionists");
    List<String> RecIdList=new ArrayList<>();
    while(rs.next()){
        RecIdList.add(rs.getString(1));
    }
    return RecIdList;
}       
  public static void updateName(ReceptionistPojo Rec)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Select receptionist_name from receptionists where receptionist_id=?");
     ps.setString(1, Rec.getReceptionistId());
     ResultSet rs=ps.executeQuery();
     rs.next();
     String currName=rs.getString(1);
     String newName=Rec.getReceptionistName();
     UserDao.updateName(currName,newName);
     ReceptionistDao.updateName(currName,newName);
    }
   public static boolean updateReceptionist(ReceptionistPojo Rec)throws SQLException{
     updateName(Rec);
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Update receptionists set receptionist_name=?,gender=? where receptionist_id=?");
     ps.setString(1, Rec.getReceptionistName());
     ps.setString(2, Rec.getGender());
     ps.setString(3, Rec.getReceptionistId());
    
     return ps.executeUpdate()==1;
 }
   
   
   
  public static List<ReceptionistPojo> getAllReceptionistDetails()throws SQLException{
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select * from receptionists order by receptionist_id");
    List<ReceptionistPojo>recList=new ArrayList<>();
    while(rs.next()){
    ReceptionistPojo recep=new ReceptionistPojo();
    recep.setReceptionistId(rs.getString(1));
    recep.setReceptionistName(rs.getString(2));
    recep.setGender(rs.getString(3));
    recList.add(recep);
    
    }
    return recList;
}
  
  public static boolean deleteReceptionistById(String recId)throws SQLException
  {
      Connection conn=DBConnection.getConnection();
      PreparedStatement ps=conn.prepareStatement("Select receptionist_name from receptionists where receptionist_id=?");
      ps.setString(1, recId);
      ResultSet rs=ps.executeQuery();
      rs.next();
      String recName=rs.getString(1);
      UserDao.removeName(recName);
      ps=conn.prepareStatement("Delete from receptionists where receptionist_id=?");
      ps.setString(1, recId);
      return ps.executeUpdate()==1;
  }
}
