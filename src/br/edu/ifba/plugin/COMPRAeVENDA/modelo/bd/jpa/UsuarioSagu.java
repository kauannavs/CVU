package br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "miolo_user")
public class UsuarioSagu {

	@Id
	@Column(name = "iduser", nullable = false)
	private int id;
	@Column(name = "name")
	private String nome;
	@Column(name = "login")
	private String login;
	@Column(name = "m_password")
	private String senha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
