package br.com.sematec.financas.util;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ifsc-jpa");

	public void close(EntityManager em) {
		em.close();
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
