package br.edu.ifba.plugin.COMPRAeVENDA.controle;

import br.edu.ifba.plugin.COMPRAeVENDA.modelo.ModeloUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.ICadastroUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.IPesquisaUsuario;

public class ControleUsuario {

	private IPesquisaUsuario pesquisaUsuario;
	private ICadastroUsuario cadastroUsuario;
	private ModeloUsuario modeloUsuario;

	public void setPesquisaUsuario(IPesquisaUsuario pesquisa) {
		this.pesquisaUsuario = pesquisa;
	}

	public void setCadastroUsuario(ICadastroUsuario cadastro) {
		this.cadastroUsuario = cadastro;
	}

	public void setModeloUsuario(ModeloUsuario modelo) {
		this.modeloUsuario = modelo;
	}

	public void pesquisar() {
		modeloUsuario.setPesquisaUsuario(pesquisaUsuario);
		modeloUsuario.pesquisar();
	}

	public void pesquisarParaCadastro() {
		modeloUsuario.setCadastroUsuario(cadastroUsuario);
		modeloUsuario.pesquisarParaCadastro();
	}

	public void remover() {
		modeloUsuario.setPesquisaUsuario(pesquisaUsuario);
		modeloUsuario.remover();
	}

	public void gravar() {
		modeloUsuario.setCadastroUsuario(cadastroUsuario);
		
		if (cadastroUsuario.getId() == -1) {
			modeloUsuario.adicionar();
		} else {
			modeloUsuario.atualizar();
		}
	}

}
