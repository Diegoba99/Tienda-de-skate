/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ClienteDAO {

 Conexion cn = new Conexion();
 Connection con;
 PreparedStatement ps;
 ResultSet rs;
    
 public boolean registrarCliente(Cliente c1){
     
     String sql = "INSERT INTO clientes(dni,nombre,telefono,direccion,razon) VALUES (?,?,?,?,?)";
     
     try{
         con = cn.getConnection();
         ps= con.prepareStatement(sql);
         ps.setInt(1,c1.getDni() );
         ps.setString(2,c1.getNombre());
         ps.setInt(3, c1.getTelefono());
         ps.setString(4,c1.getDireccion());
         ps.setString(5,c1.getRazon());
         ps.execute();
         return true;
         
     }catch(SQLException e){
         JOptionPane.showMessageDialog(null,e.toString());
         return false;
     }/*finally{
         
         try{
             con.close();
         }catch(SQLException e){
             System.out.println(e.toString());
         }
  
     }*/
 }
 
 public List listarCliente(){
     
     List <Cliente> listaCl = new ArrayList();
     String sql = "SELECT * FROM clientes";
     
     try{
         
         con=cn.getConnection();
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         
         while(rs.next()){
             
             Cliente c1 = new Cliente();
             c1.setId(rs.getInt("id"));
             c1.setDni(rs.getInt("dni"));
             c1.setNombre(rs.getString("nombre"));
             c1.setTelefono(rs.getInt("telefono"));
             c1.setDireccion(rs.getString("direccion"));
             c1.setRazon(rs.getString("razon"));
             listaCl.add(c1);
            
         }
         
     }catch(SQLException e){
         System.out.println(e.toString());
     }
    return listaCl;
 }
 
 public boolean eliminarCliente(int id){
     
     String sql = "DELETE FROM clientes WHERE id = ?";
     
     try{
         ps = con.prepareStatement(sql);
         ps.setInt(1, id);
         ps.execute();
         return true;
     }catch(SQLException e){
         System.out.println(e.toString());
         return false;
     }/*finally{
         try{
             con.close();
         }catch(SQLException ex){
             System.out.println(ex.toString());
         }
     }*/
 }

public Cliente buscarCliente(int dni){
    
    Cliente cl = new Cliente();
    String sql = "SELECT * FROM clientes WHERE dni = ?";
    
    try{
        
        con = cn.getConnection();
        ps=con.prepareStatement(sql);
        ps.setInt(1, dni);
        rs = ps.executeQuery();
        
        while(rs.next()){
            cl.setNombre(rs.getString("nombre"));
            cl.setTelefono(rs.getInt("telefono"));
            cl.setDireccion(rs.getString("direccion"));
            cl.setRazon(rs.getString("razon"));
        }
        
    }catch(SQLException e){
        
        System.out.println(e.toString());
    }
    return cl;
}
 
 public boolean modificarCliente(Cliente cl){
     
     String sql = "UPDATE clientes SET dni=?, nombre=? , telefono=? , direccion=?,razon=? WHERE id = ?";
  
     try{
         
         ps = con.prepareStatement(sql);
         ps.setInt(1, cl.getDni());
         ps.setString(2, cl.getNombre());
         ps.setInt(3, cl.getTelefono());
         ps.setString(4, cl.getDireccion());
         ps.setString(5, cl.getRazon());
         ps.setInt(6, cl.getId());
         ps.execute(); // ejecuta la query
         return true;
         
     }   catch(SQLException e){
         System.out.println(e.toString());
         return false;   
     }/*finally{
         
         try{
             con.close();
         }catch(SQLException e){
             System.out.println(e.toString());
         }   
     } */ 
 }
 

    
}
