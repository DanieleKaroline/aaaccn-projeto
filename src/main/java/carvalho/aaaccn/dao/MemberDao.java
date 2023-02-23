package carvalho.aaaccn.dao;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import carvalho.aaaccn.model.Member;

public class MemberDao{

    private DataSource ds;

    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }

    public Member findByNomeMembro(String string) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ds.getConnection();
            ps = con.prepareStatement("select * from member where nome  = ?");
            ps.setString(1, string);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Member member =  new Member();
                member.setMatricula(rs.getInt("matricula"));
                member.setCpf(rs.getInt("cpf"));
                member.setNome(rs.getString("nome"));
                member.setEmail(rs.getString("email"));
                member.setDatanasc(rs.getString("datanasc"));
                member.setAlecond(rs.getString("alecond"));
                member.setEndereco(rs.getString("endereco"));
                member.setCurso(rs.getString("curso"));
                member.setModalidade(rs.getString("modalidade"));
                member.setPhoneNumber(rs.getString("telefone"));
                
                return member;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*public void atualizarUltimoAcesso(Integer id, Date data) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ds.getConnection();
            ps = con.prepareStatement("update membro set ultimo_acesso = ? where id = ?");
            ps.setTimestamp(1, new java.sql.Timestamp(data.getTime()));
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

}