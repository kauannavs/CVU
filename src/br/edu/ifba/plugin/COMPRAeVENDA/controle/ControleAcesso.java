package br.edu.ifba.plugin.COMPRAeVENDA.controle;

import br.edu.ifba.plugin.COMPRAeVENDA.modelo.ModeloUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.IAcessoUsuario;

/**
 * Classe para controle de acesso.
 * 
 * Realiza a integracao entre o modelo {@link #modeloUsuario} e a interface de
 * componente grafico {@link #acessoUsuario}. Esta integracao ocorre a partir da
 * injecao da interface no modelo. O modelo eh ativo (i.e. o MVC implementado se
 * baseia no principio de Modelo ativo) e aciona atualizacoes na interface.
 * 
 * @author PLUGIN
 */
public class ControleAcesso {

	private IAcessoUsuario acessoUsuario = null;
	private ModeloUsuario modeloUsuario = null;

	public void setAcessoUsuario(IAcessoUsuario acesso) {
		this.acessoUsuario = acesso;
	}

	public void setModeloUsuario(ModeloUsuario modelo) {
		this.modeloUsuario = modelo;
	}

	public void validarAcesso() {
		modeloUsuario.setAcessoUsuario(acessoUsuario);
		modeloUsuario.validarAcesso();
	}

}
