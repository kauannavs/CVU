package br.edu.ifba.plugin.COMPRAeVENDA.visao;

import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Usuario;

/**
 * Define um fluxo de operacoes necessarias para a(s) regra(s) de negocio(s)
 * tratada(s):
 * 
 * 1. Recupera uma informacao de login, {@link IAcessoUsuario#getLogin()}, e
 * senha, {@link IAcessoUsuario#getSenha()} 
 * 
 * 2. Realiza chamada aa classe de controle, ControleAcesso
 * 
 * 3. O modelo aciona a execucao de um dos metodos:
 * 3.1. Usuario com login e senha encontrado: {@link IAcessoUsuario#atualizarUsuarioComPermissao(Usuario)}
 * 3.2. Usuario nao encontrado (invalido): {@link IAcessoUsuario#notificarSemPermissao()}
 * 
 * @author PLUGIN
 */
public interface IAcessoUsuario {

	public String getLogin();

	public String getSenha();

	public void atualizarUsuarioComPermissao(Usuario usuario);

	public void notificarSemPermissao();

	public void redirecionar(Usuario usuario);
		
	public void acessar();
	
	public void cadastrar();
}
