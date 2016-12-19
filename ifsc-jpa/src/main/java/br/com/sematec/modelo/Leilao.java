package br.com.sematec.modelo;

import java.util.Calendar;
import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@javax.persistence.Entity
public class Leilao extends Entity {

	private String nome;
	private Double valorInicial;
	@OneToOne
	private Usuario usuario;
	private Calendar dataAbertura;
	private boolean usado;
	@OneToMany(mappedBy = "leilao")
	private List<Lance> lances;
	private boolean encerrado;

	public Calendar getDataAbertura() {
		return dataAbertura;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public String getNome() {
		return nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Double getValorInicial() {
		return valorInicial;
	}

	public boolean isEncerrado() {
		return encerrado;
	}

	public boolean isUsado() {
		return usado;
	}

	public void setDataAbertura(Calendar dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public void setEncerrado(boolean encerrado) {
		this.encerrado = encerrado;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUsado(boolean usado) {
		this.usado = usado;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}

}
