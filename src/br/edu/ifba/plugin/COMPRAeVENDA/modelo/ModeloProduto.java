package br.edu.ifba.plugin.COMPRAeVENDA.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Produto;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.ProdutoDAO;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.ICadastroProduto;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.IPesquisaProduto;


public class ModeloProduto {

	private IPesquisaProduto pesquisaProduto = null;
	private ICadastroProduto cadastroProduto = null;
	
	public void setPesquisaProduto(IPesquisaProduto pesquisaProduto) {
		this.pesquisaProduto = pesquisaProduto;
	}
	
	public void setCadastroProduto(ICadastroProduto cadastroProduto) {
		this.cadastroProduto = cadastroProduto;
	}
	
	public void pesquisar(){
		List<Produto> produtos = new ArrayList<Produto>();

		String criterio = pesquisaProduto.getNome();
		if (!criterio.equals("")) {
			produtos = ProdutoDAO.getProdutoProNome(criterio);
		}else{
			produtos = ProdutoDAO.getTodos();
		}

		pesquisaProduto.atualizarProdutosEncontrados(produtos);

		if (produtos.isEmpty()) {
			pesquisaProduto.notificarProdutosNaoEncontrados();
		}
	}
	
	public void pesquisarParaCadastro() {
		Produto produto = ProdutoDAO.getProduto(cadastroProduto.getId());

		if (produto == null) {
			cadastroProduto.notificarProdutoNaoEncontrado();
		} else {
			cadastroProduto.atualizarProdutoEncontrado(produto);
		}
	}
	
	public void remover() {
		ProdutoDAO.remover(Integer.parseInt(pesquisaProduto.getId()));
	}
	
	public void adicionar() {
		Produto produto = cadastroProduto.getProduto();

		produto.setId(-1);
		if(produto.getStatus().equalsIgnoreCase("Disponivel") && produto.getQuantidade()>0){
			if (ProdutoDAO.gravar(produto) > 0) {
				cadastroProduto.notificarErroGravacao();
			} else {
				cadastroProduto.notificarProdutoGravado(produto);
			}			
		}else{
			cadastroProduto.notificarValoresInvalido();
		}
	}

	public void atualizar() {
		Produto produto = cadastroProduto.getProduto();

		if (ProdutoDAO.gravar(produto) > 0) {
			cadastroProduto.notificarErroGravacao();
		} else {
			cadastroProduto.notificarProdutoGravado(produto);
		}
	}
	
}
