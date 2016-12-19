package br.com.sematec.modelo;

import java.util.Calendar;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.sematec.exception.UsuarioInvalidoException;
import br.com.sematec.exception.ValorInvalidoException;

@javax.persistence.Entity
public class Lance extends Entity implements Comparable<Lance> {

	private double valor;
	private Calendar data;
	@OneToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	@ManyToOne
	private Leilao leilao;

	public Lance() {
	}

	public Lance(Usuario usuario, Double valor) throws UsuarioInvalidoException, ValorInvalidoException {
		if (usuario == null) {
			throw new UsuarioInvalidoException();
		}

		if (valor == null || valor <= 0D) {
			throw new ValorInvalidoException();
		}

		this.usuario = usuario;
		this.valor = valor;
	}

	@Override
	public int compareTo(Lance obj) {
		return new Double(getValor()).compareTo(new Double(obj.getValor()));
	}

	@Override
	public boolean equals(Object obj) {
		return new Double(getValor()).equals(new Double(((Lance) obj).getValor()));
	}

	public Calendar getData() {
		return data;
	}

	public Leilao getLeilao() {
		return leilao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public double getValor() {
		return valor;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
