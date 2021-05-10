
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
import model.bean.Professor;

/**
 *
 * @author jarde
 */
public class ProfessorDao {
    public List<Professor> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Professor> professores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM professor");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Professor professor = new Professor();

                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("Nome"));
                professor.setCPF(rs.getString("CPF"));
                professor.setEmail(rs.getString("Email"));
                professor.setSexo(rs.getString("Sexo"));
                professor.setCurso(rs.getString("Curso"));
                professor.setPeriodo(rs.getString("Periodo"));
                professor.setCelular(rs.getString("Celular"));
                professor.setEixo(rs.getString("Eixo"));
                professor.setDisciplinas(rs.getString("Disciplinas"));
                professor.setNascimento(rs.getString("Nascimento"));
                professores.add(professor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return professores;
        

    }
    public List<Professor> readForDesc(String desc) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Professor> professores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM professor WHERE id LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Professor professor = new Professor();

                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("Nome"));
                professor.setCPF(rs.getString("CPF"));
                professor.setEmail(rs.getString("Email"));
                professor.setSexo(rs.getString("Sexo"));
                professor.setCurso(rs.getString("Curso"));
                professor.setPeriodo(rs.getString("Periodo"));
                professor.setCelular(rs.getString("Celular"));
                professor.setEixo(rs.getString("Eixo"));
                professor.setDisciplinas(rs.getString("Disciplinas"));
                professor.setNascimento(rs.getString("Nascimento"));
                professores.add(professor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return professores;

    }
    public void update(Professor p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE professor SET Nome = ?,CPF = ?,Email= ?,Sexo= ?,Curso= ?,Periodo= ?,Celular= ?,Eixo= ?,Disciplinas= ?,Nascimento= ? WHERE id = ?");
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
    public void delete(Professor p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM professor WHERE id = ?");
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
