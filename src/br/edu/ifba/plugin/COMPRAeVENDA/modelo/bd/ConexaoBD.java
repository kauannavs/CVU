package br.edu.ifba.plugin.COMPRAeVENDA.modelo.bd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexaoBD {

	private EntityManagerFactory managerFactory = null;	

	// instancia singleton para acesso a conexoes
	private static ConexaoBD instancia = null;
	
	public static ConexaoBD getInstancia() {
		if (instancia == null) {
			instancia = new ConexaoBD();
			instancia.iniciar();
		}
		
		return instancia;
	}
	
	private ConexaoBD() {
	}
	//
	
	public void iniciar(){
		managerFactory = Persistence.createEntityManagerFactory("plugin");
	}
	
	public void fechar(){
		managerFactory.close();
	}

	public EntityManager getEntityManager(){
		return managerFactory.createEntityManager();
	}	
	
}