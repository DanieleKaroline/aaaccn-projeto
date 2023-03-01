package carvalho.aaaccn.dao;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import carvalho.aaaccn.model.Member;

public class MemberDao{
	@Inject
	//Cria a conexão e controla a transação com o SGBD (usado pelo Hibernate)
    private EntityManager em;
	
	public Member encontrarId(Integer id) {
        return em.find(Member.class, id);
    }
	
	//Query usando a API Criteria do Hibernate
	//Indicada para consultas complexas
	public Boolean ehMemberUnico(String u) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        criteria.select(member);
        criteria.where(cb.like(member.get("member"), u));
        if (em.createQuery(criteria).getResultList().isEmpty())
        	return true;
        return false;
    }
	
	//Query usando a linguagem HQL do Hibernate
	//Idnicada para consultas simples
	public List<Member> listarTodos() {
	    return em.createQuery("SELECT matricula, nome, curso, email, telefone FROM membro;", Member.class).getResultList();      
	}
	
	public void salvar(Member m) {
		em.persist(m);
	}
	
	public void atualizar(Member m) {
		em.merge(m);
	}
	
	public void excluir(Member m) {
		em.remove(em.getReference(Member.class, m.getCpf()));
	}
	
}
