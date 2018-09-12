package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

final public class DBConnect {
 
    public static Connection connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection res = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","fuckoff");
            if (res != null) {
                System.out.println("Connection working");
            } else {
                System.out.println("Failed to make connection!");
            }
            return res;
        } catch (Exception e) {
            System.out.println("Connection Failed! Check output console");
            return null;
        }
    }   
    
    public static boolean insertAndUpdate(String sql,ArrayList al) {
     
     Connection con = DBConnect.connect();
        try {
           PreparedStatement stmt=con.prepareStatement(sql);
           for (int i=0;i<al.size();i++) {
              stmt.setObject(i+1, al.get(i));
               System.out.println(al.get(i));
           }
           if(stmt.executeUpdate()==1)
                return true;
           else 
                return false;
        }
        catch (Exception ex) {
           Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
    }
     
    public static int lastId(String tableName) {
        
       String sql="SELECT MAX(id) FROM "+tableName;
       sql=sql.replace("$TableName", tableName);
       try {
           Connection con = DBConnect.connect();
           PreparedStatement stmt=con.prepareStatement(sql);           
           ResultSet rs= stmt.executeQuery(sql);
           
           if(rs.next()){
              int id=rs.getInt(1);    
               System.out.println("id=="+id);
              return id;
           }
           return 0;
       }
       catch(Exception ex){
           Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
          return -2; 
       }
    }
    
    public static Object login(String tableName,String userCol,String passCol,String username,String password) {
        
        System.out.println("Username = "+username);
        String sql="SELECT * FROM "+tableName+" WHERE "+userCol+"='"+username+"' AND "+passCol+"='"+password+"' AND switch=1";
        System.out.println("slq= "+sql);
        try {
            Connection con = DBConnect.connect();
            PreparedStatement stmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);           
                      
            ResultSet rs= stmt.executeQuery(sql);
           
            if(getRows(rs)==1){
               rs.next();
              return rs;
            }
            return -1;
        } catch(Exception ex){
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return -2; 
        }
    }
     
     public static int getRows(ResultSet res){
        try {
            res.afterLast();
            res.previous();
            int totalRows = res.getRow();
            res.beforeFirst();
            return totalRows ;    
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
