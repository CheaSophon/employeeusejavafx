/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapp;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author MSI
 */
public class database {
    public static Connection Database(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
            return  con;
        } 
        catch (Exception e){
            e.printStackTrace();
        }
         return null;
     }
}
