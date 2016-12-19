package br.com.sematec.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.sematec.financas.util.JPAUtil;
import br.com.sematec.modelo.Conta;
import br.com.sematec.modelo.Movimentacao;

public class TesteConsulta {

	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();

		Conta conta = new Conta();
		conta.setId(1);

		Query query = manager
				.createQuery("select m from Movimentacao m where m.conta.id="
						+ conta.getId());

		List<Movimentacao> movimentacoes = query.getResultList();

		for (Movimentacao m : movimentacoes) {
			System.out.println("\nDescricao ..: " + m.getDescricao());
			System.out.println("Valor ......: R$ " + m.getValor());
		}
		manager.close();

	}
	
}
