/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author LN710Q
 */
public class FiltroDao implements Metodos <Filtro>{
    private static final String SQL_INSERT = "INSERT INTO productos (id,nombre,codigo,tipo,cantidad,precio,disponibilidad) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE productos SET  cantidad = ?, precio = ? , disponibilidad = ? WHERE codigo = ?";
    private static final String SQL_DELETE = " DELETE FROM productos WHERE codigo = ?";
    private static final String SQL_READ = "SELECT * FROM productos WHERE codigo = ?";
    private static final String SQL_READALL = "SELECT * FROM productos";

    public static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Filtro g) {

        PreparedStatement ps;

        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setInt(1, g.getId());
            ps.setString(2, g.getNombre());
            ps.setString(3, g.getCodigo());
            ps.setString(4, g.getTipo());
            ps.setInt(5, g.getCantidad());
            ps.setInt(6, g.getPrecio());
            ps.setBoolean(7, g.isDisponibilidad());
            if (ps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        try {
            PreparedStatement ps;
            
            ps = con.getCnx ().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Filtro c) {
        PreparedStatement ps;
        try {
            
            //id,nombre,codigo,tipo,cantidad,precio,disponibilidad
            System.out.println(c.getCodigo());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            
            ps.setInt(1, c.getCantidad());
            ps.setInt(2, c.getPrecio());
            ps.setBoolean(3, c.isDisponibilidad());
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Filtro read(Object key) {
        Filtro f = null;
        PreparedStatement ps;
        ResultSet rs;
        try {

            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());

            rs = ps.executeQuery();
            //id,nombre,codigo,tipo,cantidad,precio,disponibilidad

            while (rs.next()) {
                f = new Filtro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(4), rs.getInt(5) ,rs.getBoolean(7));
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            con.cerrarConexion();
        }
        return f;
    }

    @Override
    public ArrayList<Filtro> readAll() {
        ArrayList<Filtro> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try {

            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            
            while(rs.next()){
                all.add(new Filtro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(4), rs.getInt(5) ,rs.getBoolean(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }
}
