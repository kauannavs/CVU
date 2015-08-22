package br.edu.ifba.plugin.COMPRAeVENDA.visao.impl;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.ifba.plugin.COMPRAeVENDA.controle.ControleAcesso;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.ModeloUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Usuario;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.IAcessoUsuario;

/**
 * Concretizacao de interface para validacao de credenciais (login + senha) de
 * acesso do usuario.
 * 
 * Esta concretizacao pode ser utilizada para validar requisitos relacionados ao
 * controle de acesso de usuarios aas funcionalidades do sistema. Nao sao
 * tratados recursos graficos/visuais de interacao do usuario (e.g. tela de
 * login). Toda a interacao eh realizada atraves do console da aplicacao.
 * 
 * @author PLUGIN
 */
@ManagedBean(name = "acesso")
@ViewScoped
public class AcessoUsuario implements IAcessoUsuario {

	private String login, senha;
	private boolean semPermissao = false;
	
	public void setLogin(String login) {

		this.login = login;
	}

	@Override
	public String getLogin() {
		return login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String getSenha() {
		return senha;
	}

	@Override
	public void acessar() {
		ModeloUsuario modelo = new ModeloUsuario();
		ControleAcesso controle = new ControleAcesso();

		controle.setModeloUsuario(modelo);
		controle.setAcessoUsuario(this);

		controle.validarAcesso();
	}
	
	@Override
	public void cadastrar() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		if(context != null && context.getRequestServletPath() != null){
			
			try {
				context.redirect("cadastro_usuario.ifba");
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
		}
	}

	@Override
	public void atualizarUsuarioComPermissao(Usuario usuario) {
		System.out.println("Usuario com permissao de acesso = "
				+ usuario.getNome());

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("USUARIO", usuario);
		
	}

	@Override
	public void notificarSemPermissao() {
		semPermissao = true;
	}

	public boolean getSemPermissao() {
		return semPermissao;
	}

	public void sair(){
		String usuarioLogado = pesquisarUsuarioLogado();
		if(!usuarioLogado.equals("Usuário!")){
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.setAttribute("USUARIO", null);
		}
		
		redirecionar(getUsuario());
	}
	
	public void validaAcesso() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		if(context != null && context.getRequestServletPath() != null){
			if(!context.getRequestServletPath().equals("/pesquisa_produto.ifba")&&
				!context.getRequestServletPath().equals("/cadastro_usuario.ifba") &&
				!context.getRequestServletPath().equals("/cadastro_produto.ifba")){
				
				if(pesquisarUsuarioLogado().equals("Usuário!")){
					try {
						context.redirect("login.ifba");
					} catch (IOException e) {
						e.printStackTrace();
					}					
				}
			}else{
//				if(context.getRequestServletPath().equals("/cadastro_produto.ifba")){
//						context.getSessionMap().get("idProduto");
//					
//				}
			}
		}
	}

	public Usuario getUsuario(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = (HttpSession) request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		return usuario;
		
	}
	public String pesquisarUsuarioLogado() {	
		Usuario usuario = getUsuario();
		
		String nome = usuario !=null && usuario.getNome() != null ? usuario.getNome(): "Usuário!";
		return nome;
	}

	@Override
	public void redirecionar(Usuario usuario) {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		try {
			context.redirect("pesquisa_produto.ifba");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
