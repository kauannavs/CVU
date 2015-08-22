package br.edu.ifba.plugin.COMPRAeVENDA.visao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.ifba.plugin.COMPRAeVENDA.controle.ControleUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.ModeloUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Usuario;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.IPesquisaUsuario;

@ManagedBean(name = "pusuario")
@ViewScoped
public class PesquisaUsuario implements IPesquisaUsuario {

	private String erro;
	private String sucesso;
	
	private String id = "";
	private String email = "";
	private String cpf = "";
	private String nome = "";

	private List<Usuario> usuariosEncontrados = new ArrayList<Usuario>();

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void pesquisar() {
		erro = null;
		
		System.out.println("pesquisar");

		ModeloUsuario modelo = new ModeloUsuario();
		ControleUsuario controle = new ControleUsuario();

		controle.setModeloUsuario(modelo);
		controle.setPesquisaUsuario(this);

		controle.pesquisar();
	}

	@Override
	public void atualizarUsuariosEncontrados(List<Usuario> usuarios) {
		this.usuariosEncontrados = usuarios;
	}

	public List<Usuario> getUsuarios() {
		return this.usuariosEncontrados;
	}


	private void exibirCadastro(String id) {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.getSessionMap().put("idUsuario", id);
		try {
			context.redirect("cadastro_usuario.ifba");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ver(String id) {
		exibirCadastro(id);
	}

	public void adicionar() {
		exibirCadastro("");
	}

	public void remover(String id) {
		sucesso = null;
		erro = null;
		
		this.id = id;

		ModeloUsuario modelo = new ModeloUsuario();
		ControleUsuario controle = new ControleUsuario();

		controle.setModeloUsuario(modelo);
		controle.setPesquisaUsuario(this);

		controle.remover();
	}

	public String getErro() {
		return erro;
	}

	public String getSucesso() {
		return sucesso;
	}
	
	@Override
	public void notificarUsuariosNaoEncontrados() {
		erro = "Nenhum usuário foi encontrado";
	}

	@Override
	public void notificarUsuarioRemovido() {
		sucesso = "Usuário removido com sucesso";
	}

	@Override
	public void notificarErroRemocao() {
		erro = "Não foi possível remover o usuário";
	}
	
}
