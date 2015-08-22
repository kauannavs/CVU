package br.edu.ifba.plugin.COMPRAeVENDA.controle;

import br.edu.ifba.plugin.COMPRAeVENDA.modelo.ModeloProdutoPorUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.IPesquisaProduto;

public class ControleProdutoPorUsuario {
	
	private IPesquisaProduto pesquisaProduto;
	private ModeloProdutoPorUsuario modeloProdutoPorUsuario;
	
	public void setPesquisaProduto(IPesquisaProduto pesquisaProduto) {
		this.pesquisaProduto = pesquisaProduto;
	}
	public void setModeloProdutoPorUsuario(ModeloProdutoPorUsuario modeloProdutoPorUsuario) {
		this.modeloProdutoPorUsuario = modeloProdutoPorUsuario;
	}
	
	public void pesquisaPorUsuario(){
		modeloProdutoPorUsuario.setPesquisaProduto(pesquisaProduto);
		modeloProdutoPorUsuario.pesquisar();
	}
}
