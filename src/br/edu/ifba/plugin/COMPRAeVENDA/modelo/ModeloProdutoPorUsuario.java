package br.edu.ifba.plugin.COMPRAeVENDA.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Produto;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.ProdutoPorUsuarioDAO;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Usuario;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.ICadastroProduto;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.IPesquisaProduto;

public class ModeloProdutoPorUsuario {
	
	private IPesquisaProduto pesquisaProduto = null;
	private ICadastroProduto cadastroProduto = null;
	
	public void setCadastroProduto(ICadastroProduto cadastroProduto) {
		this.cadastroProduto = cadastroProduto;
	}
	public void setPesquisaProduto(IPesquisaProduto pesquisaProduto) {
		this.pesquisaProduto = pesquisaProduto;
	}
	
	public void pesquisar() {
		List<Produto> produtos = new ArrayList<Produto>();

		Usuario criterio = pesquisaProduto.getUsuario();
		String nome = pesquisaProduto.getNome();
		if (!criterio.equals("")) {
			if(nome.isEmpty()){
				produtos = ProdutoPorUsuarioDAO.getProdutosPorUsuario(criterio);				
			}else{
				produtos = ProdutoPorUsuarioDAO.getProdutosPorNome(criterio,nome);
			}
		} 
		
		pesquisaProduto.atualizarProdutosEncontrados(produtos);

		if (produtos.isEmpty()) {
			pesquisaProduto.notificarProdutosNaoEncontrados();
		}
	}
	
	public void gravar(){
		Usuario usuario = cadastroProduto.getUsuario();
		Produto produto = cadastroProduto.getProduto();
		
		ProdutoPorUsuarioDAO.gravar(produto, usuario);
		
	}
	
	public void remover(){
		Usuario usuario = pesquisaProduto.getUsuario();
		String produto = pesquisaProduto.getId();
		
		ProdutoPorUsuarioDAO.remover(produto, usuario);
		
		pesquisar();
	}

}
