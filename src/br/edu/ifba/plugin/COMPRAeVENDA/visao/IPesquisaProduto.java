package br.edu.ifba.plugin.COMPRAeVENDA.visao;

import java.util.List;

import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Produto;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Usuario;

public interface IPesquisaProduto {
	
	public String getId();
	
	public String getNome();
	
	public Usuario getUsuario();
	
	/////////////////////////
	
	public void atualizarProdutosEncontrados(List<Produto> produtos);
	public void notificarProdutosNaoEncontrados();
	public void pesquisaProdutoPorUsuario();
	
}
