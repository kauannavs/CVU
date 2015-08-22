package br.edu.ifba.plugin.COMPRAeVENDA.visao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.edu.ifba.plugin.COMPRAeVENDA.controle.ControleProduto;
import br.edu.ifba.plugin.COMPRAeVENDA.controle.ControleProdutoPorUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.ModeloProduto;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.ModeloProdutoPorUsuario;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Produto;
import br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico.Usuario;
import br.edu.ifba.plugin.COMPRAeVENDA.visao.IPesquisaProduto;

@ManagedBean(name = "pproduto")
@ViewScoped
public class PesquisaProduto implements IPesquisaProduto{
	
	private boolean naoEncontrado = false;

	private String id = "";
	private String nome = "";
	private String imagem ="";
	
	private Usuario usuario;

	private List<Produto> produtosEncontrados = new ArrayList<Produto>();

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void pesquisar() {
		naoEncontrado = false;
		
		System.out.println("pesquisar");

		ModeloProduto modelo = new ModeloProduto();
		ControleProduto controle = new ControleProduto();

		controle.setModeloProduto(modelo);
		controle.setPesquisaProduto(this);

		controle.pesquisar();
	}
	
	@Override
	public void atualizarProdutosEncontrados(List<Produto> produtos) {
		this.produtosEncontrados = produtos;	
	}
	
	public List<Produto> getProdutos() {
		return produtosEncontrados;
	}
	
	private void exibirCadastro(String id) {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.getSessionMap().put("idProduto", id);
		try {
			context.redirect("cadastro_produto.ifba");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ver(String id) {
		exibirCadastro(id);
	}

	public void adicionar() {
		exibirCadastro("");
	}

	public void remover(String id) {
		this.id = id;

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = (HttpSession) request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		this.usuario = usuario;
		String idUsuario = usuario.getId()+"";
		
		ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
		if(idUsuario != null && !idUsuario.isEmpty()){
			ModeloProduto modelo = new ModeloProduto();
			ControleProduto controle = new ControleProduto();
			ModeloProdutoPorUsuario modelo2 = new ModeloProdutoPorUsuario();
			
			controle.setModeloProduto(modelo);
			controle.setPesquisaProduto(this);
			controle.setModeloProdutoPorUsuario(modelo2);
			
			controle.remover();	
		}
	}

	public boolean getNaoEncontrado() {
		return naoEncontrado;
	}

	@Override
	public void notificarProdutosNaoEncontrados() {
		naoEncontrado = true;
	}

	@Override
	public void pesquisaProdutoPorUsuario() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = (HttpSession) request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("USUARIO");
		
		ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
		
		this.usuario = usuario;
		
		if(usuario == null){
			try {
				contexto.redirect("login.ifba");
			} catch (IOException e) {
				e.printStackTrace();
			}						
		}else{
			ModeloProdutoPorUsuario modelo = new ModeloProdutoPorUsuario();
			ControleProdutoPorUsuario controle = new ControleProdutoPorUsuario(); 
			
			controle.setModeloProdutoPorUsuario(modelo);
			controle.setPesquisaProduto(this);
			
			controle.pesquisaPorUsuario();			
		}
		
				
	}

}
