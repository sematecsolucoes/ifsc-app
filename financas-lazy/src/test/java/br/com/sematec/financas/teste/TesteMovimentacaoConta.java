package br.com.sematec.financas.teste;

import javax.persistence.EntityManager;

import br.com.sematec.financas.util.JPAUtil;
import br.com.sematec.modelo.Movimentacao;

public class TesteMovimentacaoConta {
	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();
		Movimentacao movimentacao = manager
				.find(Movimentacao.class, 2); // id 2 deve existir no banco

		System.out.println(movimentacao.getConta().getTitular());

	}
}
