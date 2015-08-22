package br.edu.ifba.plugin.COMPRAeVENDA.visao;

import java.util.List;

import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Usuario;

public interface IPesquisaUsuario {

	public String getId();
	
	public String getEmail();

	public String getCpf();

	public String getNome();

	///////////////////////
	
	public void atualizarUsuariosEncontrados(List<Usuario> usuarios);

	public void notificarUsuariosNaoEncontrados();
	
	public void notificarUsuarioRemovido();
	
	public void notificarErroRemocao();

}
