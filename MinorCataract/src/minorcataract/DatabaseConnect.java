/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minorcataract;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class DatabaseConnect {
 public static Connection ConnectDb(){
     try{
         Class.forName("com.mysql.jdbc.Driver");
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cataract","root", "");
         System.out.println("Connected");
         return conn;
         
     }catch(Exception e){
         JOptionPane.showMessageDialog(null, e);
         return null;
     }
 }   
}
