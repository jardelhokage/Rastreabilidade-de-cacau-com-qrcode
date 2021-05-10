/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JDBC.ConnectionFactory;
import Javabeans.CadastroMeieirosBEAB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author Jardel
 */
public class CadastroMeieirosDAO {
    
    private final Connection conecta;
    //Construtor
    public CadastroMeieirosDAO(){       
        this.conecta = new ConnectionFactory().conecta();       
    }
    
    //Método para cadastrar um novo meieiro:
    public void CriarUsuario(CadastroMeieirosBEAB obj){
        try{
            //Comando SQL:
            String cmdsql;
            cmdsql = "insert into cadastromeieiros(qrcode,nome,cpf,setor,rg,sexo,nasc,roca,cell) values(?,?,?,?,?,?,?,?,?)";
            
            //organização cmdsql:
            PreparedStatement stmt;
            stmt = conecta.prepareStatement(cmdsql);
            
            stmt.setString(1,obj.getQrcode());
            stmt.setString(2,obj.getNome());
            stmt.setString(3,obj.getCpf());
            stmt.setString(4,obj.getSetor());
            stmt.setString(5,obj.getRg());
            stmt.setString(6,obj.getSexo());
            stmt.setString(7,obj.getNascimento());
            stmt.setString(8,obj.getRoca());
            stmt.setString(9,obj.getCelular());
            
            //execução do comando:
            stmt.execute();
            
            //fim da conexão:
            stmt.close();
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados! "
                    + "","SQL Exception!", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(erro);
        }
    }
    
}
