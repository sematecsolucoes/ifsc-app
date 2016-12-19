package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.sematec.exception.LeilaoDesertoException;
import br.com.sematec.exception.RestException;
import br.com.sematec.modelo.Lance;
import br.com.sematec.modelo.Leilao;

public class Avaliador {

	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;

	public void avalia(Leilao leilao) throws LeilaoDesertoException {
		if (leilao.getLances() == null || leilao.getLances().size() == 0) {
			throw new LeilaoDesertoException();
		}

		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorLance) {
				maiorLance = lance.getValor();
			}
			if (lance.getValor() < getMenorLance()) {
				menorLance = lance.getValor();
			}
		}
	}

	public void avalia2(Leilao leilao) throws RestException {
		throw RestException.CLASS_NOT_FOUND;

	}

	public double getMaiorLance() {
		return maiorLance;
	}

	public double getMenorLance() {
		return menorLance;
	}

	public List<Lance> getTresMaiores(Leilao leilao) {
		List<Lance> lances = leilao.getLances();
		Collections.sort(lances);
		Collections.reverse(lances);
		List<Lance> retorno = new ArrayList<>();

		int i = 0;
		while (i < 3 && i < lances.size()) {
			retorno.add(leilao.getLances().get(i));
			i++;
		}

		return retorno;
	}

	public void setMenorLance(double menorLance) {
		this.menorLance = menorLance;
	}

}
