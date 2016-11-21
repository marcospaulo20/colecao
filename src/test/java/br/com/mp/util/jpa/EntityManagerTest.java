package br.com.mp.util.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

public class EntityManagerTest {

	private static EntityManager em = null;
	
	@Test
	public void test() {
		if (em == null) {
            em = (EntityManager) Persistence.createEntityManagerFactory("colecao").createEntityManager();
            System.out.println("Abriu conexao");
        } else {
        	System.out.println("Não abriu conexao");
        }
	}
}
