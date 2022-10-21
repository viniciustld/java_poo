package model;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class clientedao {
    conectar conectar;
    
    public clientedao(){
        conectar = new conectar();
    }
    
    public String insertcliente(String cpf, String nome, String rua, int nr_rua, String dt_nasc, Double renda){
        String respReg = null;
        try{
            Connection acessoDB = conectar.getconnection();
            CallableStatement cs = acessoDB.prepareCall("{call sp_insert(?,?,?,?,?,?)}");
            cs.setString(1, cpf);
            cs.setString(2, nome);
            cs.setString(3, rua);
            cs.setInt(4, nr_rua);
            cs.setString(5, dt_nasc);
            cs.setDouble(6, renda);
            
            int registros = cs.executeUpdate();
            
            if(registros > 0){
                respReg = "Registro Gravado";
            }
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "deu zica" + ex);
        }
        return respReg;
    }
    
    public ArrayList<cliente> listacliente(){
        ArrayList listacliente;
        listacliente = new ArrayList();
        cliente cliente;
        
        try{
            Connection acessoDB = conectar.getconnection();
            PreparedStatement ps = acessoDB.prepareStatement("select cpf, nome, rua, nr_rua, dt_nasc, renda from cliente");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                cliente = new cliente();
                cliente.setCpf(rs.getString(1));
                cliente.setNome(rs.getString(2));
                cliente.setRua(rs.getString(3));
                cliente.setNr_rua(rs.getInt(4));
                cliente.setDt_nasc(rs.getString(5));
                cliente.setRenda(rs.getDouble(6));
                listacliente.add(cliente);
            }
        }
        catch(Exception ex){
        }
        return listacliente;
    }
    
    public int excluircliente(String cpf){
        int numreg = 0;
        try{
            Connection acessoDB = conectar.getconnection();
            CallableStatement cs = acessoDB.prepareCall("{call sp_delete(?)}");
            cs.setString(1, cpf);
            
            numreg = cs.executeUpdate();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "deu zica" + ex);
        }
        return numreg;
    }
    
    public ArrayList<cliente> searchcliente(String cpf){
        ArrayList<cliente> listacliente = new ArrayList();
        cliente cliente;
        
        try{
            Connection acessoDB = conectar.getconnection();
            CallableStatement cs = acessoDB.prepareCall("{call sp_search(?)}");
            cs.setString(1, cpf);
            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                cliente = new cliente();
                cliente.setCpf(rs.getString(1));
                cliente.setNome(rs.getString(2));
                cliente.setRua(rs.getString(3));
                cliente.setNr_rua(rs.getInt(4));
                cliente.setDt_nasc(rs.getString(5));
                cliente.setRenda(rs.getDouble(6));
                listacliente.add(cliente);
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "deu zica" + ex);
        }
        return listacliente;
    }
    
    public int updatecliente(String cpf, String nome, String rua, int nr_rua, String dt_nasc, Double renda){
        int numReg = 0;
        try{
            Connection acessoDB = conectar.getconnection();
            CallableStatement cs = acessoDB.prepareCall("{call sp_update(?,?,?,?,?,?)}");
            cs.setString(1, cpf);
            cs.setString(2, nome);
            cs.setString(3, rua);
            cs.setInt(4, nr_rua);
            cs.setString(5, dt_nasc);
            cs.setDouble(6, renda);
            
            numReg = cs.executeUpdate();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "deu zica" + ex);
        }
        return numReg;
    }
    
    
}