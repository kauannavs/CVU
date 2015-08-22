package br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd.estatico;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProdutoDAO {

	private static Map<Integer, Produto> produtos = new TreeMap<Integer, Produto>();
	
	static{
		Produto p1 = new Produto();
		p1.setId(1);
		p1.setNome("Pen Drive");
		p1.setDescricao("Pen Drive");
		p1.setMarca("mutilaser");
		p1.setQuantidade(2);
		p1.setValor(2.4);
		p1.setImagem("img/pendrive.png");
		
		produtos.put(p1.getId(), p1);
		
		Produto p2 = new Produto();
		p2.setId(2);
		p2.setNome("Notebook HP 14-v066Br");
		p2.setDescricao("Notebook Intel Core i7 8GB 1TB + "
				+ "2GB de Memória Dedicada Tela 14 Windows 8.1 - Branco");
		p2.setMarca("dell");
		p2.setQuantidade(2);
		p2.setValor(2253.0);
		p2.setImagem("img/notebook.png");
		
		produtos.put(p2.getId(), p2);
		
		Produto p3 = new Produto();
		p3.setId(3);
		p3.setNome("Samsung Galaxy S6 Preto");
		p3.setDescricao("Samsung Galaxy S6 Preto Desbloqueado 32GB 4G Android 5.0 Tela 5.1"+
						"Octa Core Câmera 16MP");
		p3.setMarca("Galaxy");
		p3.setQuantidade(2);
		p3.setValor(1253.0);
		p3.setImagem("img/celular.png");

		
		produtos.put(p3.getId(), p3);
		
		Produto p4 = new Produto();
		p4.setId(4);
		p4.setNome("Geladeira / Refrigerador Brastemp");
		p4.setDescricao("Samsung Galaxy S6 Preto Desbloqueado 32GB 4G Android 5.0 Tela 5.1"+
						"Octa Core Câmera 16MP");
		p4.setMarca("Galaxy");
		p4.setQuantidade(5);
		p4.setValor(6253.0);
		p4.setImagem("img/geladeira.png");
		
		produtos.put(p4.getId(), p4);
		
	}
	
	public static List<Produto> getProdutoProNome(String nome) {
		List<Produto> encontrados = new ArrayList<Produto>();
		
		for (Produto p : produtos.values()) {
			if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
				encontrados.add(p);
			}
		}
		
		return encontrados;
	}
	
	public static List<Produto> getTodos() {
		List<Produto> encontrados = new ArrayList<Produto>();
		
		for (Produto p : produtos.values()) {
				encontrados.add(p);
		}
		
		return encontrados;
	}
	
	public static Produto getProduto(int id) {
		return produtos.get(id);
	}
	
	public static void remover(int id) {
		produtos.remove(id);
	}
	
	public static int gravar(Produto produto) {
		if (produto.getId() != -1) {
			remover(produto.getId());
			produtos.put(produto.getId(), produto);
		} else {
			int ultimoId = 0;
			for (int id : produtos.keySet()) {
				ultimoId = id;
			}
			produto.setId(ultimoId + 1);
			produto.setImagem("img/produto.png");
			produtos.put(ultimoId + 1, produto);
		}
		
		return 0;
	}
}
