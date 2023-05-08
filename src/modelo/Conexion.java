/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;

public class Conexion {
    Connection con;
    private String nombreBd="sistemaventa";
    private String usuario="root";
    private String password="";
    private String url = "jdbc:mysql://localhost:3306/"+nombreBd+"?useUnicode=true&use"+
                "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"+"serverTimezone=UTC";
        
    
    public Conexion() {
    
    try{
       //obtener el driver
       Class.forName("com.mysql.cj.jdbc.Driver");
        //obtener la conexion
        con=DriverManager.getConnection(url,usuario,password);
        
        if(con != null){
            System.out.println("Conexion exitosa con la BD: " + nombreBd);
        }
    }catch(ClassNotFoundException e){
        System.out.println("Ocurre una cClassnotFoundException: " + e.getMessage());
   }catch(SQLException e ){
         System.out.println("Ocurre una SQLException: " + e.getMessage());       
    }
    
   }
    
    
    public Connection getConnection(){
    return con;
    }
    
     public void desconectar(){
    con =null;
    }
    
    
    
    
    
}
