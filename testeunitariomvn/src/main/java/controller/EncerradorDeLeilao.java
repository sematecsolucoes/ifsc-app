package controller;

import java.util.List;

import br.com.sematec.exception.UsuarioInvalidoException;
import br.com.sematec.modelo.Leilao;
import dao.LeilaoDAO;

public class EncerradorDeLeilao {

	private LeilaoDAO leilaoDAO;

	public EncerradorDeLeilao(LeilaoDAO leilaoDAO) {
		this.leilaoDAO = leilaoDAO;
	}

	public void encerrar() {

		List<Leilao> listaLeilao = leilaoDAO.naoCcorrentes();

		for (Leilao leilao : listaLeilao) {
			leilao.setEncerrado(true);
			leilaoDAO.update(leilao);
		}

	}

	public void excluiUsuario(int id) throws UsuarioInvalidoException {
		System.out.println("1");
		System.out.println("2");

		if (id == 10) {
			try {
				leilaoDAO.excluirUsuario();
			} catch (Exception e) {
				throw new UsuarioInvalidoException();
			}
		}
		System.out.println("3");
		System.out.println("4");

	}
}
