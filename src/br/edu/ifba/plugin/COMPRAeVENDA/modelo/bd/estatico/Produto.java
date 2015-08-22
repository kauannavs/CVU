package br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico;

public class Produto {

	private int id;
	private String nome;
	private String descricao;
	private String tipo;
	private String status;
	private String marca;
	private int quantidade;
	private Double valor = 00.00;
	private String imagem;
	
	private Usuario usuario;
	
	public Produto() {
	}
	
	public Produto(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "ID = " + id + ". NOME = " + nome;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getImagem() {
		return imagem != null ? imagem : "img/produto.png";
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
}
