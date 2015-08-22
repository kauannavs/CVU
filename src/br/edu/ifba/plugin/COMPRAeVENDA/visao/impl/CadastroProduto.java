package br.edu.ifba.plugin.COMPRAeVENDA.visao.impl;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.ifba.plugin.COMPRAeVENDA.controle.ControleProduto;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.ModeloProduto;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.ModeloProdutoPorUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Produto;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Usuario;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.ICadastroProduto;

@ManagedBean(name="cadproduto")
@RequestScoped
public class CadastroProduto implements ICadastroProduto{

	private static final String ERRO_NOME_NAO_INFORMADO = "O nome deve ser informado!";
	private static final String ERRO_DESCRICAO_NAO_INFORMADO = "A descrição deve ser informado!";
	private static final String ERRO_TIPO_NAO_INFORMADO = "O tipo deve ser informado!";
	private static final String ERRO_MARCA_NAO_INFORMADO = "A marca deve ser informado!";
	private static final String ERRO_STATUS_NAO_INFORMADO = "O status deve ser informado!";
	private static final String ERRO_VALOR_NAO_INFORMADO = "O valor deve ser informado!";
	private static final String ERRO_VALOR_INVALIDO = "O campo valor está inválido!";

	public boolean gravado = false;
	public boolean ngravado = false;
	public boolean ngravadoinvalidos = false;
	
	private String id = "";
	private Produto produto = new Produto();
	private Usuario usuario = new Usuario();
		
	public CadastroProduto() {
		ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
		id = (String) contexto.getSessionMap().get("idProduto");
		
		if ((id != null) && (!id.isEmpty())) {
			recuperarProduto();
		}			
	}

	private void recuperarProduto() {
		ModeloProduto modelo = new ModeloProduto();
		ControleProduto controle = new ControleProduto();

		controle.setCadastroProduto(this);
		controle.setModeloProduto(modelo);

		controle.pesquisarParaCadastro();
	}

	@Override
	public int getId() {
		int iid = -1;

		if ((id != null) && (!id.isEmpty())) {
			iid = Integer.parseInt(id);
		}

		return iid;
	}

	@Override
	public Produto getProduto() {
		return produto;
	}

	@Override
	public void atualizarProdutoEncontrado(Produto produto) {
		this.produto = produto;		
	}
	
	@Override
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario buscaUsuario(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = (HttpSession) request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		return usuario;		
	}
	
	public void gravar() {
		gravado = false;
		ngravado = false;
		ngravadoinvalidos = false;
		
		this.usuario = buscaUsuario();	
		
		if(usuario == null){
			ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
			try {
				contexto.redirect("login.ifba");
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}else{
			if(validaCamposVazios()){
				gravarProduto();				
			}			
		}

	}
	
	private void gravarProduto() {
		ModeloProduto modelo = new ModeloProduto();
		ModeloProdutoPorUsuario modelo2 = new ModeloProdutoPorUsuario();
		ControleProduto controle = new ControleProduto();

		controle.setCadastroProduto(this);
		controle.setModeloProduto(modelo);
		controle.setModeloProdutoPorUsuario(modelo2);

		controle.gravar();
	}
	
	private boolean validaCamposVazios() {
		boolean verifica = true;
		if ((produto.getNome() == null) || (produto.getNome().isEmpty())) {
			FacesContext.getCurrentInstance().addMessage(
					"form:nome",
					new FacesMessage(ERRO_NOME_NAO_INFORMADO,
							ERRO_NOME_NAO_INFORMADO));
		} 
		if((produto.getDescricao() == null) || (produto.getDescricao().isEmpty())){
			FacesContext.getCurrentInstance().addMessage(
					"form:descricao",
					new FacesMessage(ERRO_DESCRICAO_NAO_INFORMADO,
							ERRO_DESCRICAO_NAO_INFORMADO));
		}
		if((produto.getTipo() == null) || (produto.getTipo().isEmpty())){
			FacesContext.getCurrentInstance().addMessage(
					"form:tipo",
					new FacesMessage(ERRO_TIPO_NAO_INFORMADO,
							ERRO_TIPO_NAO_INFORMADO));
		}
		if((produto.getMarca() == null) || (produto.getMarca().isEmpty())){
			FacesContext.getCurrentInstance().addMessage(
					"form:marca",
					new FacesMessage(ERRO_MARCA_NAO_INFORMADO,
							ERRO_MARCA_NAO_INFORMADO));
		}
		if((produto.getStatus() == null) || (produto.getStatus().isEmpty())){
			FacesContext.getCurrentInstance().addMessage(
					"form:status",
					new FacesMessage(ERRO_STATUS_NAO_INFORMADO,
							ERRO_STATUS_NAO_INFORMADO));
		}
		if((produto.getStatus() == null) || (produto.getStatus().isEmpty())){
			FacesContext.getCurrentInstance().addMessage(
					"form:status",
					new FacesMessage(ERRO_STATUS_NAO_INFORMADO,
							ERRO_STATUS_NAO_INFORMADO));
		}
		if((produto.getValor() == null)){
			FacesContext.getCurrentInstance().addMessage(
					"form:valor",
					new FacesMessage(ERRO_VALOR_NAO_INFORMADO,
							ERRO_VALOR_NAO_INFORMADO));
		}else if((produto.getValor()<0.01)){
			FacesContext.getCurrentInstance().addMessage(
					"form:valor",
					new FacesMessage(ERRO_VALOR_INVALIDO,
							ERRO_VALOR_INVALIDO));
		}
		
		
		if(FacesContext.getCurrentInstance().getMessageList().size() != 0){
			verifica = false;			
		}
		
		return verifica;
	}
	
	@Override
	public void notificarProdutoNaoEncontrado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificarProdutoGravado(Produto produto) {
		gravado = true;	
		ngravado = false;
		ngravadoinvalidos = false;
	}
	
	public boolean getGravado() {
		return gravado;
	}

	@Override
	public void notificarErroGravacao() {
		ngravado = true;
	}
	
	public boolean getNgravado() {
		return ngravado;
	}

	@Override
	public void notificarValoresInvalido() {
		ngravadoinvalidos = true;
	}
	
	public boolean getNgravadoinvalidos() {
		return ngravadoinvalidos;
	}

}
