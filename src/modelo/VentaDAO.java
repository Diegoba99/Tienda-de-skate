/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class VentaDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    int r;
    ResultSet rs;
    
    public int idVenta(){
    
    int id =0;
    String sql = "SELECT max(id) FROM ventas";
    
    try{
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs=ps.executeQuery();
        
        if(rs.next()){
            
           id=rs.getInt(1);
        }
        
    }catch(SQLException e){
        System.out.println(e.toString());
    }
    return id;   
    }
    
    
    
    public int registrarVenta(Venta vta){
        
        String sql = "INSERT INTO ventas (cliente,vendedor,total,fecha) VALUES (?,?,?,?)";
        
        try{
            
            con=cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, vta.getCliente());
            ps.setString(2, vta.getVendedor());
            ps.setDouble(3, vta.getTotal());
            ps.setString(4, vta.getFecha());
            ps.execute();
           
            
        }catch(SQLException e){
            
            System.out.println(e.toString());
        }
    
     return r;
    }
    
    public int registrarDetalle(Detalle dv){
        
        String sql = "INSERT INTO detalle(cod_producto,cantidad,precio,id_venta)VALUES(?,?,?,?)";
        
        try{
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dv.getCodPro());
            ps.setInt(2, dv.getCantidad());
            ps.setDouble(3, dv.getPrecio());
            ps.setInt(4, dv.getId());
            ps.execute();
            
        }catch(SQLException e){
           
            System.out.println(e.toString());
            
        }
        
        return r;
    }
    
    public boolean actualizarStock( int cantidad, String codigo ){
        
        String sql = "UPDATE productos SET stock = ? WHERE codigo = ?";
        
        try {
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setString(2, codigo);
            ps.execute();
            return true;
            
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
        
    }
    
    
     public List listarVentas(){
     
     List<Venta> listaVentas = new ArrayList();
     String sql = "SELECT * FROM ventas";
     
     try{
         
         con=cn.getConnection();
         ps = con.prepareStatement(sql);
         rs = ps.executeQuery();
         
         while(rs.next()){
             
             Venta ven = new Venta ();
             ven.setId(rs.getInt("id"));
             ven.setCliente(rs.getString("cliente"));
             ven.setVendedor(rs.getString("vendedor"));
             ven.setTotal(rs.getDouble("total"));
             listaVentas.add(ven);
            
         }
         
     }catch(SQLException e){
         System.out.println(e.toString());
     }
    return listaVentas;
 }
        
}
