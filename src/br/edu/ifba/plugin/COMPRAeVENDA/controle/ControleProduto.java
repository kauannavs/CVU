package br.edu.ifba.plugin.COMPRAeVENDA.controle;

import br.edu.ifba.plugin.COMPRAeVENDA.modelo.ModeloProduto;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.ModeloProdutoPorUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.ICadastroProduto;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.IPesquisaProduto;

public class ControleProduto {

	private IPesquisaProduto pesquisaProduto;
	private ICadastroProduto cadastroProduto;
	private ModeloProduto modeloProduto;
	private ModeloProdutoPorUsuario modeloProdutoPorUsuario;
	
	public void setPesquisaProduto(IPesquisaProduto pesquisaProduto) {
		this.pesquisaProduto = pesquisaProduto;
	}
	public void setCadastroProduto(ICadastroProduto cadastroProduto) {
		this.cadastroProduto = cadastroProduto;
	}
	public void setModeloProduto(ModeloProduto modeloProduto) {
		this.modeloProduto = modeloProduto;
	}
	public void setModeloProdutoPorUsuario(ModeloProdutoPorUsuario modeloProdutoPorUsuario) {
		this.modeloProdutoPorUsuario = modeloProdutoPorUsuario;
	}
	
	public void pesquisar() {
		modeloProduto.setPesquisaProduto(pesquisaProduto);
		modeloProduto.pesquisar();
	}

	public void pesquisarParaCadastro() {
		modeloProduto.setCadastroProduto(cadastroProduto);
		modeloProduto.pesquisarParaCadastro();
	}

	public void remover() {
		modeloProduto.setPesquisaProduto(pesquisaProduto);
		modeloProdutoPorUsuario.setPesquisaProduto(pesquisaProduto);
		modeloProduto.remover();
		modeloProdutoPorUsuario.remover();
		
		modeloProdutoPorUsuario.pesquisar();
	}

	public void gravar() {
		modeloProduto.setCadastroProduto(cadastroProduto);
		modeloProdutoPorUsuario.setCadastroProduto(cadastroProduto);
		
		if (cadastroProduto.getId() == -1) {
			modeloProduto.adicionar();
			modeloProdutoPorUsuario.gravar();
		} else {
			modeloProduto.atualizar();
		}
	}
	
	public void pesquisaPorUsuario(){
		modeloProdutoPorUsuario.setPesquisaProduto(pesquisaProduto);
		modeloProduto.pesquisar();
	}
}
