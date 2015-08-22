package br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico;

public class ProdutoPorUsuario {
	
	private int id;
	private Usuario usuario;
	private Produto produto;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}