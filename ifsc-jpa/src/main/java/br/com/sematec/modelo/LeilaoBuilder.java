package br.com.sematec.modelo;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.sematec.exception.UsuarioInvalidoException;
import br.com.sematec.exception.ValorInvalidoException;

public class LeilaoBuilder {

	private Leilao instancia;

	public LeilaoBuilder() {
		instancia = new Leilao();
		instancia.setLances(new ArrayList<Lance>());
	}

	public Leilao build() {
		return instancia;
	}

	public LeilaoBuilder comData(Calendar data) {
		instancia.setDataAbertura(data);
		return this;
	}

	public LeilaoBuilder comLance(String nomeUsuario, double valor) throws UsuarioInvalidoException, ValorInvalidoException {
		instancia.getLances().add(new Lance(new Usuario(nomeUsuario), valor));
		return this;
	}

	public LeilaoBuilder comNome(String nome) {
		instancia.setNome(nome);
		return this;
	}

}
