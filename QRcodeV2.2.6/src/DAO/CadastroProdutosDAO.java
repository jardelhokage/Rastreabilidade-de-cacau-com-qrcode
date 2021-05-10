/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import JDBC.ConnectionFactory;
import Javabeans.CadastroMeieirosBEAB;
import Javabeans.CadastroMeieirosPROD;
import Javabeans.CadastroProdutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Tiago Fochesatto
 */
public class CadastroProdutosDAO {
    private final Connection conecta;
    //Construtor
    public CadastroProdutosDAO(){       
        this.conecta = new ConnectionFactory().conecta();       
    }
    
    /**
     * Metodo para armazenar os registros por nome e/ou matrícula em um vetor:
     * @param qrcode
     * @return 
     */
    public List<CadastroMeieirosPROD> ListarAlunosPorNomeouMat(String qrcode){
        try{
            //Cria o vetor que armazena os registros do banco:
            List<CadastroMeieirosPROD> lista = new ArrayList<>();
            
            //Cria o comando SQL:
            String cmdSql = "select *from cadastromeieiros where qrcode like ?;";
            PreparedStatement stmt = conecta.prepareStatement(cmdSql);
            stmt.setString(1, qrcode);
            
            //Guarda o resultado do select dentro do objeto rs (ResultSet):
            ResultSet rs = stmt.executeQuery();
            
            
            //Enquanto houver registro, guarda na lista:
            while(rs.next()){
                CadastroMeieirosPROD v = new CadastroMeieirosPROD();
                v.setCod(rs.getInt("cod"));
                v.setQrcode(rs.getString("qrcode"));
                v.setNome(rs.getString("nome"));
                v.setCpf(rs.getString("cpf"));
                v.setRg(rs.getString("rg"));
                v.setSexo(rs.getString("sexo"));
                v.setNascimento(rs.getString("nasc"));
                v.setCelular(rs.getString("cell"));
                v.setRoca(rs.getString("roca"));
                v.setSetor(rs.getString("setor"));
                
                lista.add(v);
            }
            return lista;
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao acessar dados no registro!","DAO error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
    }
    
    //Método para cadastrar um novo meieiro:
    public void CadastraProduto(CadastroProdutos obj){
        try{
            //Comando SQL:
            String cmdsql;
            cmdsql = "insert into cadastroprodutos(tipo,quant,cotacao,dat,valor,lote,cod_meieiro) values(?,?,?,?,?,?,?)";
            
            //organização cmdsql:
            PreparedStatement stmt;
            stmt = conecta.prepareStatement(cmdsql);
            
            stmt.setString(1,obj.getTipo());
            stmt.setString(2,obj.getQuant());
            stmt.setString(3,obj.getCot());
            stmt.setString(4,obj.getData());
            stmt.setString(5,obj.getVlr());
            stmt.setString(6,obj.getLote());
            stmt.setInt(7,obj.getCod());
            
            //execução do comando:
            stmt.execute();
            
            //fim da conexão:
            stmt.close();
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Falha na inserção dos dados no banco de dados! "
                    + "","SQL Exception!", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(erro);
        }
    }
    
}
