package br.edu.ifba.plugin.COMPRAeVENDA.visao;

import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Produto;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Usuario;

public interface ICadastroProduto {
	
	public int getId();
	
	public Produto getProduto();
	
	public Usuario getUsuario();

	////////////////////////////
	
	public void atualizarProdutoEncontrado(Produto produto);
	public void notificarProdutoNaoEncontrado();
	public void notificarProdutoGravado(Produto produto);
	public void notificarErroGravacao();
	public void notificarValoresInvalido();
}
