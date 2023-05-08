/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author diego
 */
public class ProductosDAO {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public ProductosDAO() {
    }
    
    
    
 public boolean registrarProductos(Productos pro){
        
        try{
            String sql = "INSERT INTO productos (codigo, nombre,proveedor, stock, precio) VALUES (?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setDouble(5, pro.getPrecio());
            ps.execute();
            return true;
            
        }catch(SQLException e){
            
            System.out.println(e.toString());
            return false;
        }
  
    }

 public void consultarProveedor(JComboBox proveedor){
     
     String sql = "SELECT nombre FROM proveedor";
     
     try {
         con = cn.getConnection();
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         
         while(rs.next()){
             proveedor.addItem(rs.getString("nombre"));
         }
         
     }catch(SQLException e){
         
         System.out.println(e.toString());
         
     }
 }
 
 public List listarProductos(){
     
     List <Productos> listaPro = new ArrayList();
     String sql = "SELECT * FROM productos";
     
     try{
         
         con=cn.getConnection();
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         
         while(rs.next()){
             
             Productos pro = new Productos();
             pro.setId(rs.getInt("id"));
             pro.setCodigo(rs.getString("codigo"));
             pro.setNombre(rs.getString("nombre"));
             pro.setProveedor(rs.getString("proveedor"));
             pro.setStock(rs.getInt("stock"));
             pro.setPrecio(rs.getDouble("precio"));
             listaPro.add(pro);
            
         }
         
     }catch(SQLException e){
         System.out.println(e.toString());
     }
    return listaPro;
 }
  
 public boolean eliminarProducto(int id){
     
     String sql = "DELETE FROM productos WHERE id = ?";
     
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
  
   
 public boolean modificarProducto(Productos pro){
     
     String sql = "UPDATE productos SET codigo=?, nombre=? , proveedor=? , stock=?,precio=? WHERE id = ?";
  
     try{
         
         ps = con.prepareStatement(sql);
         ps.setString(1, pro.getCodigo());
         ps.setString(2, pro.getNombre());
         ps.setString(3, pro.getProveedor());
         ps.setInt(4, pro.getStock());
         ps.setDouble(5, pro.getPrecio());
         ps.setInt(6, pro.getId());
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
         
     }  */
 }
 
 public Productos buscarPro(String cod){
     
     Productos pro = new Productos();
     String sql ="SELECT * from Productos WHERE codigo = ?";
     
     try{
         
         con = cn.getConnection();
         ps = con.prepareStatement(sql);
         ps.setString(1,cod);
         rs = ps.executeQuery();
         
         if(rs.next()){
             
             pro.setNombre(rs.getString("nombre"));
             pro.setPrecio(rs.getDouble("precio"));
             pro.setStock(rs.getInt("stock"));
         }
     }catch(SQLException e){
         
         System.out.println(e.toString());
         
     }
     return pro;
 }
 
  public Config buscarDatos(){
     
     Config conf = new Config();
     String sql ="SELECT * from config";
     
     try{
         
         con = cn.getConnection();
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         
         if(rs.next()){
             
             conf.setCif(rs.getInt("cif"));
             conf.setDireccion(rs.getString("direccion"));
             conf.setId(rs.getInt("id"));
             conf.setNombre(rs.getString("nombre"));
             conf.setRazon(rs.getString("razon"));
             conf.setTelefono(rs.getInt("telefono"));
         }
     }catch(SQLException e){
         
         System.out.println(e.toString());
        
     }
     return conf;
 }
 
 
   public boolean modificarDatos(Config conf){
     
     String sql = "UPDATE config SET cif=?, nombre=? , telefono=? , direccion=?,razon=? WHERE id = ?";
  
     try{
         
         ps = con.prepareStatement(sql);
         ps.setInt(1, conf.getCif());
         ps.setInt(2, conf.getTelefono());
         ps.setString(3, conf.getDireccion());
         ps.setString(4, conf.getRazon());
         ps.setInt(5, conf.getId());
         ps.execute(); // ejecuta la query
         return true;
         
     }   catch(SQLException e){
         System.out.println(e.toString());
         return false;   
     }finally{
         
         try{
             con.close();
         }catch(SQLException e){
             System.out.println(e.toString());
         }
         
     } 
 }
 
  
  
  
  
    
}
