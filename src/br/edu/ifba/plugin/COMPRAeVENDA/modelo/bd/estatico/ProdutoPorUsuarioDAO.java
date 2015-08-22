package br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;

public class ProdutoPorUsuarioDAO {

	private static Map<Integer, ProdutoPorUsuario> produtosPORusuarios = new TreeMap<Integer, ProdutoPorUsuario>();
	
	static {
		//Produtos
		Produto p1 = new Produto();
		p1.setId(1);
		p1.setNome("Pen Drive");
		p1.setDescricao("Pen Drive");
		p1.setMarca("mutilaser");
		p1.setQuantidade(2);
		p1.setValor(2.4);
		p1.setImagem("img/pendrive.png");
		
		Produto p2 = new Produto();
		p2.setId(2);
		p2.setNome("Notebook HP 14-v066Br");
		p2.setDescricao("Notebook Intel Core i7 8GB 1TB + "
				+ "2GB de Memória Dedicada Tela 14 Windows 8.1 - Branco");
		p2.setMarca("dell");
		p2.setQuantidade(2);
		p2.setValor(2253.0);
		p2.setImagem("img/notebook.png");
		
		Produto p3 = new Produto();
		p3.setId(3);
		p3.setNome("Samsung Galaxy S6 Preto");
		p3.setDescricao("Samsung Galaxy S6 Preto Desbloqueado 32GB 4G Android 5.0 Tela 5.1"+
						"Octa Core Câmera 16MP");
		p3.setMarca("Galaxy");
		p3.setQuantidade(2);
		p3.setValor(1253.0);
		p3.setImagem("img/celular.png");
		
		Produto p4 = new Produto();
		p4.setId(4);
		p4.setNome("Geladeira / Refrigerador Brastemp");
		p4.setDescricao("Samsung Galaxy S6 Preto Desbloqueado 32GB 4G Android 5.0 Tela 5.1"+
						"Octa Core Câmera 16MP");
		p4.setMarca("Galaxy");
		p4.setQuantidade(5);
		p4.setValor(6253.0);
		p4.setImagem("img/geladeira.png");
				
		//Usuarios
		Usuario u1 = new Usuario();
		u1.setId(1);
		u1.setCpf("123-4");
		u1.setNome("Astrogildo da Silva");
		u1.setLogin("astro");
		u1.setSenha("123");
			
		Usuario u2 = new Usuario();
		u2.setId(2);
		u2.setCpf("789-0");
		u2.setNome("Estelvania da Silva");
		u2.setLogin("telva");
		u2.setSenha("456");
		
		//relacionamento
		ProdutoPorUsuario pu1 = new ProdutoPorUsuario();
		pu1.setId(1);
		pu1.setUsuario(u1);
		pu1.setProduto(p1);
		
		produtosPORusuarios.put(pu1.getId(), pu1);
		
		ProdutoPorUsuario pu2 = new ProdutoPorUsuario();
		pu2.setId(2);
		pu2.setUsuario(u1);
		pu2.setProduto(p2);
		
		produtosPORusuarios.put(pu2.getId(), pu2);
		
		ProdutoPorUsuario pu3 = new ProdutoPorUsuario();
		pu3.setId(3);
		pu3.setUsuario(u2);
		pu3.setProduto(p3);
		
		produtosPORusuarios.put(pu3.getId(), pu3);
		
		ProdutoPorUsuario pu4 = new ProdutoPorUsuario();
		pu4.setId(3);
		pu4.setUsuario(u2);
		pu4.setProduto(p3);
		
		produtosPORusuarios.put(pu4.getId(), pu3);
		
	}
	
	public static List<Produto> getProdutosPorUsuario(Usuario usuario) {
		List<Produto> produtos = new ArrayList<Produto>();
		for (ProdutoPorUsuario pu : produtosPORusuarios.values()) {
			if(pu.getUsuario().getCpf().equals(usuario.getCpf())){
				produtos.add(pu.getProduto());
			}
		}
		
		return produtos;
	}
			
	public static void remover(String idProduto, Usuario usuario) {
		int id = Integer.parseInt(idProduto);
		int idParaRemover = 0;
		for (ProdutoPorUsuario pu : produtosPORusuarios.values()) {
			if(pu.getUsuario().getCpf().equals(usuario.getCpf()) && (pu.getProduto().getId()==id)){	
				idParaRemover = pu.getId();
			}
		}
		produtosPORusuarios.remove(idParaRemover);
	}
	
	public static int gravar(Produto produto, Usuario usuario) {
			int ultimoId = 0;
			for(int id : produtosPORusuarios.keySet()){
				ultimoId = id;
			}
			
			ProdutoPorUsuario pu = new ProdutoPorUsuario();
			pu.setProduto(produto);
			pu.setUsuario(usuario);
			pu.setId(ultimoId + 1);
			
			produtosPORusuarios.put(ultimoId + 1, pu);		
				
		return 0;
	}

	public static List<Produto> getProdutosPorNome(Usuario usuario, String nome) {
		List<Produto> produtos = new ArrayList<Produto>();
		for (ProdutoPorUsuario pu : produtosPORusuarios.values()) {
			if(pu.getUsuario().getCpf().equals(usuario.getCpf()) && (pu.getProduto().getNome().toLowerCase().contains(nome))){
				produtos.add(pu.getProduto());
			}
		}
		
		return produtos;
	}
}
