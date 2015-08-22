package br.edu.ifba.plugin.COMPRAeVENDA.visao.impl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.edu.ifba.plugin.COMPRAeVENDA.controle.ControleUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.ModeloUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Usuario;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.ICadastroUsuario;

@ManagedBean(name = "cadusuario")
@RequestScoped
public class CadastroUsuario implements ICadastroUsuario {

	private static final String ERRO_CPF_NAO_INFORMADO = "Cpf deve ser informado!";
	private static final String ERRO_EMAIL_NAO_INFORMADO = "Email deve ser informado!";
	private static final String ERRO_NOME_NAO_INFORMADO = "Nome deve ser informado!";
	private static final String ERRO_DATANASCIMENTO_NAO_INFORMADO = "Data de nascimento deve ser informado!";
	private static final String ERRO_TELEFONE_NAO_INFORMADO = "Telefone deve ser informado!";
	private static final String ERRO_CELULAR_NAO_INFORMADO = "Celular deve ser informado!";
	private static final String ERRO_LOGIN_NAO_INFORMADO = "Login deve ser informado!";
	private static final String ERRO_SENHA_NAO_INFORMADO = "Senha deve ser informado!";
	private static final String ERRO_RUA_NAO_INFORMADO = "Rua deve ser informado!";
	private static final String ERRO_NUMERO_NAO_INFORMADO = "Numero deve ser informado!";
	private static final String ERRO_BAIRRO_NAO_INFORMADO = "Bairro deve ser informado!";
	private static final String ERRO_CIDADE_NAO_INFORMADO = "Cidade deve ser informado!";
	private static final String ERRO_CEP_NAO_INFORMADO = "Cep deve ser informado!";
	private static final String ERRO_SENHA_INVALIDO = "Senha inválido!";

	
	public boolean gravado = false;

	private String id = "";
	private Usuario usuario = new Usuario();

	
	public CadastroUsuario() {
		ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
		id = (String) contexto.getSessionMap().get("idUsuario");

		if ((id != null) && (!id.isEmpty())) {
			recuperarUsuario();
		}
	}

	private void recuperarUsuario() {
		ModeloUsuario modelo = new ModeloUsuario();
		ControleUsuario controle = new ControleUsuario();

		controle.setCadastroUsuario(this);
		controle.setModeloUsuario(modelo);

		controle.pesquisarParaCadastro();
	}

	@Override
	public int getId() {
		int iid = -1;

		if ((id != null) && (!id.isEmpty())) {
			iid = Integer.parseInt(id);
		}

		return iid;
	}

	@Override
	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public void atualizarUsuarioEncontrado(Usuario usuario) {
		this.usuario = usuario;
	}

	public void gravar() {
		gravado = false;


		if (validaCamposVazios()) {
			
			gravarUsuario();			
		}
	}
	
	private boolean validaCamposVazios() {
		boolean verifica = true;
		if ((usuario.getCpf() == null) || (usuario.getCpf().isEmpty())){
			FacesContext.getCurrentInstance().addMessage(
					"form:cpf",
					new FacesMessage(ERRO_CPF_NAO_INFORMADO,ERRO_CPF_NAO_INFORMADO));
		} 
		if((usuario.getEmail() == null) || (usuario.getEmail().isEmpty())){
			FacesContext.getCurrentInstance().addMessage(
					"form:email",
					new FacesMessage(ERRO_EMAIL_NAO_INFORMADO,ERRO_EMAIL_NAO_INFORMADO));
		}
		if((usuario.getNome() == null) || (usuario.getNome().isEmpty())){
			FacesContext.getCurrentInstance().addMessage(
					"form:nome",
					new FacesMessage(ERRO_NOME_NAO_INFORMADO,ERRO_NOME_NAO_INFORMADO));
		}
		if((usuario.getDataNascimento() == null)){
			FacesContext.getCurrentInstance().addMessage(
					"form:datanascimento",
					new FacesMessage(ERRO_DATANASCIMENTO_NAO_INFORMADO,ERRO_DATANASCIMENTO_NAO_INFORMADO));
		}
		if((usuario.getTel() == null) || (usuario.getTel().isEmpty())){
			FacesContext.getCurrentInstance().addMessage(
					"form:telefone",
					new FacesMessage(ERRO_TELEFONE_NAO_INFORMADO,ERRO_TELEFONE_NAO_INFORMADO));
		}
		if((usuario.getCel() == null) || (usuario.getCel().isEmpty())){
			FacesContext.getCurrentInstance().addMessage(
					"form:celular",
					new FacesMessage(ERRO_CELULAR_NAO_INFORMADO,ERRO_CELULAR_NAO_INFORMADO));
		}
		if((usuario.getLogin() == null) || (usuario.getLogin().isEmpty()) ){
			FacesContext.getCurrentInstance().addMessage(
					"form:login",
					new FacesMessage(ERRO_LOGIN_NAO_INFORMADO,ERRO_LOGIN_NAO_INFORMADO));
		
		}
		if((usuario.getSenha() == null) || (usuario.getSenha().isEmpty()) ){
			FacesContext.getCurrentInstance().addMessage(
					"form:senha",
					new FacesMessage(ERRO_SENHA_NAO_INFORMADO,ERRO_SENHA_NAO_INFORMADO));
		
		}else if (usuario.getSenha().length()<3){
			FacesContext.getCurrentInstance().addMessage(
					"form:senha",
					new FacesMessage(ERRO_SENHA_INVALIDO,ERRO_SENHA_INVALIDO));

		}
		if((usuario.getRua() == null) || (usuario.getRua().isEmpty()) ){
			FacesContext.getCurrentInstance().addMessage(
					"form:rua",
					new FacesMessage(ERRO_RUA_NAO_INFORMADO,ERRO_RUA_NAO_INFORMADO));
		
		}
		if((usuario.getNumero() == null) || (usuario.getNumero().isEmpty()) ){
			FacesContext.getCurrentInstance().addMessage(
					"form:numero",
					new FacesMessage(ERRO_NUMERO_NAO_INFORMADO,ERRO_NUMERO_NAO_INFORMADO));
		
		}
		if((usuario.getBairro() == null ) || (usuario.getBairro().isEmpty()) ){
			FacesContext.getCurrentInstance().addMessage(
					"form:bairro",
					new FacesMessage(ERRO_BAIRRO_NAO_INFORMADO,ERRO_BAIRRO_NAO_INFORMADO));
		
		}
		if((usuario.getCidadeEstado() == null) || (usuario.getCidadeEstado().isEmpty()) ){
			FacesContext.getCurrentInstance().addMessage(
					"form:cidade",
					new FacesMessage(ERRO_CIDADE_NAO_INFORMADO,ERRO_CIDADE_NAO_INFORMADO));
		
		}
		if((usuario.getCep() == null) || (usuario.getCep().isEmpty()) ){
			FacesContext.getCurrentInstance().addMessage(
					"form:cep",
					new FacesMessage(ERRO_CEP_NAO_INFORMADO,ERRO_CEP_NAO_INFORMADO));
		
		}
		
		if(FacesContext.getCurrentInstance().getMessageList().size() != 0){
			verifica = false;			
		}
		
		return verifica;
	}

	private void gravarUsuario() {
		ModeloUsuario modelo = new ModeloUsuario();
		ControleUsuario controle = new ControleUsuario();

		controle.setCadastroUsuario(this);
		controle.setModeloUsuario(modelo);

		controle.gravar();
	}

	@Override
	public void notificarUsuarioNaoEncontrado() {
		// TODO Auto-generated method stub
	}

	@Override
	public void notificarUsuarioGravado(Usuario usuario) {
		gravado = true;
	}

	public boolean getGravado() {
		return gravado;
	}

	@Override
	public void notificarErroGravacao() {
		// TODO Auto-generated method stub
	}
	
}
