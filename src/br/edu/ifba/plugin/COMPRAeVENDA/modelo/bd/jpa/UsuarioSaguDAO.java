package br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.ConexaoBD;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.jpa.UsuarioSagu;

/**
 * Acesso ao banco de dados do SAGU
 * 
 * @author PLUGIN
 */
public class UsuarioSaguDAO {

	public List<UsuarioSagu> getUsuariosPorLoginSenha(String login, String senha) {
		List<UsuarioSagu> encontrados = new ArrayList<UsuarioSagu>();

		EntityManager em = ConexaoBD.getInstancia().getEntityManager();
		TypedQuery<UsuarioSagu> query = em
				.createQuery(
						"select u from UsuarioSagu u where u.login = :login and u.senha = :senha",
						UsuarioSagu.class);
		query.setParameter("login", login);
		query.setParameter("senha", senha);

		try {
			encontrados = query.getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}

		return encontrados;
	}

}
