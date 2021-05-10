
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Aluno;

/**
 *
 * @author jarde
 */
public class AlunoDao {
    public List<Aluno> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Aluno> alunos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM aluno");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Aluno aluno = new Aluno();

                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("Nome"));
                aluno.setCPF(rs.getString("CPF"));
                aluno.setEmail(rs.getString("Email"));
                aluno.setSexo(rs.getString("Sexo"));
                aluno.setCurso(rs.getString("Curso"));
                aluno.setPeriodo(rs.getString("Periodo"));
                aluno.setCelular(rs.getString("Celular"));
                aluno.setEixo(rs.getString("Eixo"));
                aluno.setDisciplinas(rs.getString("Disciplinas"));
                aluno.setNascimento(rs.getString("Nascimento"));
                alunos.add(aluno);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return alunos;
        

    }
    public List<Aluno> readForDesc(String desc) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Aluno> alunos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM aluno WHERE id LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Aluno aluno = new Aluno();

                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("Nome"));
                aluno.setCPF(rs.getString("CPF"));
                aluno.setEmail(rs.getString("Email"));
                aluno.setSexo(rs.getString("Sexo"));
                aluno.setCurso(rs.getString("Curso"));
                aluno.setPeriodo(rs.getString("Periodo"));
                aluno.setCelular(rs.getString("Celular"));
                aluno.setEixo(rs.getString("Eixo"));
                aluno.setDisciplinas(rs.getString("Disciplinas"));
                aluno.setNascimento(rs.getString("Nascimento"));
                alunos.add(aluno);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return alunos;

    }
    public void update(Aluno p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE aluno SET Nome = ?,CPF = ?,Email= ?,Sexo= ?,Curso= ?,Periodo= ?,Celular= ?,Eixo= ?,Disciplinas= ?,Nascimento= ? WHERE id = ?");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCPF());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getSexo());
            stmt.setString(5, p.getCurso());
            stmt.setString(6, p.getPeriodo());
            stmt.setString(7, p.getCelular());
            stmt.setString(8, p.getEixo());
            stmt.setString(9, p.getDisciplinas());
            stmt.setString(10, p.getNascimento());
            stmt.setInt(11, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Aluno p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM aluno WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    
}
