/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProveedorDAO {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarProveedor(Proveedor pr){
        
       try{
      
        String sql = "INSERT INTO proveedor (dni,nombre,telefono,direccion,razon) VALUES (?,?,?,?,?) ";
        con = cn.getConnection();
        ps=con.prepareStatement(sql);
        ps.setInt(1, pr.getDni());
        ps.setString(2, pr.getNombre());
        ps.setInt(3, pr.getTelefono());
        ps.setString(4, pr.getDireccion());
        ps.setString(5, pr.getRazon());
        ps.execute();
        return true;
       }catch(SQLException e ){
           
           System.out.println(e.toString());
           return false;
       }/*finally{ si lo descomento me tira java.sql.SQLNonTransientConnectionException:
           try{
               con.close();
           }catch(SQLException e ){
               System.out.println(e.toString());
           }
       }*/  
    }
    
    public List listarProveedor(){
        
        List <Proveedor> listaProveedor = new ArrayList();
        String sql = "SELECT * FROM proveedor";
     
        try{
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
                
                Proveedor pr = new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setDni(rs.getInt("dni"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getInt("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setRazon(rs.getString("razon"));
                listaProveedor.add(pr);
            }
            
        }catch(SQLException e){
            
               System.out.println(e.toString());
        }
        return listaProveedor;
    }
    
    public boolean eliminarProveedor(int id){
        
        String sql = " DELETE FROM proveedor WHERE ID = ? ";
        
        try{
        con = cn.getConnection();
        ps=con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        return true;
            
        }catch(SQLException e){
            
            System.out.println(e.toString());
            return false;
        }/*finally{
            
            try{
                con.close();
           
            }catch(SQLException e){
                
                System.out.println(e.toString()); 
                
            }  
        }*/
        
    }
    
    public boolean modificarProveedor(Proveedor pr){
        
        String sql = " UPDATE proveedor SET dni = ?, nombre = ?, telefono = ? , direccion = ?, razon = ? WHERE id = ? " ;
        
    
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getDni());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.setInt(6, pr.getId());
            ps.execute();
            return true;
        
      
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }/*finally{
            
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
            
        }*/
        
    }
    
    
}
