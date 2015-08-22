package br.edu.ifba.plugin.COMPRAeVENDA.visao;

import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "padrao")
public class Padrao {

	private String texto = "texto";
	private int selecao = 0;
	private Date data = new Date();
	private Date horario = new Date();
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}


	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}
	
	public void acao() {
		System.out.println(texto);
	}

	public int getSelecao() {
		return selecao;
	}

	public void setSelecao(int selecao) {
		this.selecao = selecao;
	}
}
