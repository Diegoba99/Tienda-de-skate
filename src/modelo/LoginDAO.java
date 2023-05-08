/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;



public class LoginDAO {
   
    Connection con = null;
    PreparedStatement ps;
    
    ResultSet rs;
    Conexion cn = new Conexion();

    public LoginDAO() {
    }
    
    
       
    public login log ( String correo, String pass){
        
        login l = new login();
        String sql = "SELECT * from usuarios WHERE correo = ? AND pass = ? ";
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,correo);
            ps.setString(2,pass);
            rs=ps.executeQuery();
            if(rs.next()){
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPass(rs.getString("pass"));
                l.setRol(rs.getString("rol"));
            }
            
        }catch(SQLException e ){
                
                System.out.println(e.toString());
            }
           return l;
        }
    
    public boolean registrar(login log){
        
        String sql = "INSERT INTO usuarios (nombre, correo,pass,rol) VALUES (?,?,?,?)";
        
        try {
            
            con = cn.getConnection();
            ps =con.prepareStatement(sql);
            ps.setString(1,log.getNombre());
            ps.setString(2,log.getCorreo());
            ps.setString(3,log.getPass());
            ps.setString(4,log.getRol());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        
    }

}
