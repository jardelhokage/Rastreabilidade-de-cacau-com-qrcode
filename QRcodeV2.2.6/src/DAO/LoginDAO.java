/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JDBC.ConnectionFactory;
import Javabeans.Login;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author sadar
 */
public class LoginDAO {
    private final Connection conecta;
    //Construtor
    public LoginDAO(){       
        this.conecta = new ConnectionFactory().conecta();       
    }
    
    //Método para verificar o Login
    public boolean TestaLog(String user,String senha) throws SQLException{
        try{
            //Comando SQL:
            String cmdsql = "select * from login where (usuario=? or email=?) and senha=?;";
            
            PreparedStatement stmt = conecta.prepareStatement(cmdsql);
            stmt.setString(1, user);
            stmt.setString(2, user);
            stmt.setString(3, senha);
            
            ResultSet rs = stmt.executeQuery();
            
            //Verifica se foi possível encontrar o registro no BD:
            return rs.first(); 
        }
        catch(SQLException erro){
            throw new RuntimeException(erro);
        }       
    }
    //Método para verificar se o cpf já existe no bd:
    public boolean RepeticaoCpf(String cpf) throws SQLException{
        try{
            //Comando SQL:
            String cmdsql = "select * from login where cpf=?;";
            
            PreparedStatement stmt = conecta.prepareStatement(cmdsql);
            stmt.setString(1, cpf);
            
            ResultSet rs = stmt.executeQuery();
            
            //Verifica se foi possível encontrar o registro no BD:
            return rs.first(); 
        }
        catch(SQLException erro){
            throw new RuntimeException(erro);
        }       
    }
    //Método para verificar se o usuário já existe no bd:
    //telaOpc
    public boolean RepeticaoUser(String user) throws SQLException{
        try{
            //Comando SQL:
            String cmdsql = "select * from login where usuario=?;";
            
            PreparedStatement stmt = conecta.prepareStatement(cmdsql);
            stmt.setString(1, user);
            
            ResultSet rs = stmt.executeQuery();
            
            //Verifica se foi possível encontrar o registro no BD:
            return rs.first(); 
        }
        catch(SQLException erro){
            throw new RuntimeException(erro);
        }       
    }
    
    //Método para verificar se o cpf já existe no bd:
    //TelaMeieiro
    public boolean RepeticaoCpfMeieiro(String cpf) throws SQLException{
        try{
            //Comando SQL:
            String cmdsql = "select * from cadastromeieiros where cpf=?;";
            
            PreparedStatement stmt = conecta.prepareStatement(cmdsql);
            stmt.setString(1, cpf);
            
            ResultSet rs = stmt.executeQuery();
            
            //Verifica se foi possível encontrar o registro no BD:
            return rs.first(); 
        }
        catch(SQLException erro){
            throw new RuntimeException(erro);
        }       
    }    
    
    //Método para adicionar um novo login:
    public void CriarUsuario(Login obj){
        try{
            //Comando SQL:
            String cmdsql;
            cmdsql = "insert into login(usuario, email, senha, nome, cpf, sexo, nasc, fone) values(?,?,?,?,?,?,?,?)";
            
            //organização cmdsql:
            PreparedStatement stmt;
            stmt = conecta.prepareStatement(cmdsql);
            
            stmt.setString(1,obj.getUser());
            stmt.setString(2,obj.getEmail());
            stmt.setString(3,obj.getSenha());
            stmt.setString(4,obj.getNome());
            stmt.setString(5,obj.getCpf());
            stmt.setString(6,obj.getSexo());
            stmt.setString(7,obj.getNasc());
            stmt.setString(8,obj.getFone());
            
            //execução do comando:
            stmt.execute();
            
            //fim da conexão:
            stmt.close();
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados!1 "
                    + "","SQL Exception!", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(erro);
        }
    }
    
    
    public boolean PossuiLogCadast() throws SQLException{
        //Comando SQL:
        String cmdsql = "select * from login where cod = 2;";
        PreparedStatement stmt = conecta.prepareStatement(cmdsql);
        
         ResultSet rs = stmt.executeQuery();
         //Verifica se foi possível encontrar o registro no BD:
            return rs.first();
    }
    public boolean possuiLogPredef() throws SQLException{
        //Comando SQL:
        String cmdsql = "select * from login where cod = 1;";
        PreparedStatement stmt = conecta.prepareStatement(cmdsql);
        
         ResultSet rs = stmt.executeQuery();
         //Verifica se foi possível encontrar o registro no BD:
            return rs.first();
    }
    
    public void ExcluirLogPredef(){
        try{
            //Comando SQL:
            String cmdsql;
            cmdsql = "delete from login where cod = 1";
            
            //organização cmdsql:
            PreparedStatement stmt;
            stmt = conecta.prepareStatement(cmdsql);
            
            
            //execução do comando:
            stmt.execute();
            
            //fim da conexão:
            stmt.close();
        }
        catch(SQLException erro){
            throw new RuntimeException(erro);
        }
    }
    
    //Validador de telefone:
    public boolean isTelefone(String fone) {
        try{
            int test;
            test = Integer.parseInt(fone.substring(13));
            return true;
        }
        catch(NumberFormatException | HeadlessException error){
            return false;
        }
        
    }
    //Validador de telefone:
    public boolean isData(String data) {
        try{
            int test;
            test = Integer.parseInt(data.substring(0));
            return true;
        }
        catch(NumberFormatException | HeadlessException error){
            return false;
        }
        
    }
}


