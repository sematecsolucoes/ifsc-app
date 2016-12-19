package br.com.sematec.financas.teste;

import javax.persistence.EntityManager;

import br.com.sematec.financas.util.JPAUtil;
import br.com.sematec.modelo.Conta;

public class TesteGerenciamentoJPA {
	public static void main(String[] args) {

		EntityManager manager = new JPAUtil().getEntityManager();
		manager.getTransaction().begin();

		// ID de uma conta que exista no banco de dados, no caso ID: 1
		Conta conta = manager.find(Conta.class, 1);

		// commit antes da altera��o
		manager.getTransaction().commit();

		// Altera��o do titular da conta
		conta.setTitular("sematec");

		manager.getTransaction().begin();
		manager.merge(conta);
		manager.getTransaction().commit();

		manager.close();
	}
}
