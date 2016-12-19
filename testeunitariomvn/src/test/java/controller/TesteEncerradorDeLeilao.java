package controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.sematec.exception.UsuarioInvalidoException;
import br.com.sematec.exception.ValorInvalidoException;
import br.com.sematec.modelo.Leilao;
import br.com.sematec.modelo.LeilaoBuilder;
import dao.LeilaoDAO;

public class TesteEncerradorDeLeilao {

	@Test
	public void deveEncerrarInicioMaisDeUmaSemana() throws UsuarioInvalidoException, ValorInvalidoException {

		Calendar data1 = Calendar.getInstance();
		data1.set(2016, 11, 1);
		Leilao leilao1 = new LeilaoBuilder()//
		.comData(data1).comNome("Fusca")//
		.comLance("Maria", 1000.00).build();

		Calendar data2 = Calendar.getInstance();
		Leilao leilao2 = new LeilaoBuilder()//
		.comData(data2).comNome("Fusca")//
		.comLance("Maria", 1000.00).build();

		Leilao leilao3 = new LeilaoBuilder()//
		.comData(data1).comNome("Fusca")//
		.comLance("Maria", 1000.00).build();

		List<Leilao> listaLeilaoAntigo = Arrays.asList(leilao1, leilao3);

		LeilaoDAO leilaoDAO = mock(LeilaoDAO.class);
		when(leilaoDAO.naoCcorrentes()).thenReturn(listaLeilaoAntigo);

		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(leilaoDAO);
		encerrador.encerrar();

		Assert.assertTrue(leilao1.isEncerrado());
		Assert.assertFalse(leilao2.isEncerrado());

		System.out.println(leilaoDAO.enviarEmail(2, 3));
		System.out.println(leilaoDAO.enviarEmail(2, 3));

		LeilaoDAO leilaoDAO2 = mock(LeilaoDAO.class);
		System.out.println(leilaoDAO2.enviarEmail(2, 3));
		System.out.println(leilaoDAO2.enviarEmail(2, 3));

	}

	@Test(expected = UsuarioInvalidoException.class)
	public void deveverificarExcecao() throws Exception {
		LeilaoDAO leilaoDAO = mock(LeilaoDAO.class);
		when(leilaoDAO.excluirUsuario()).thenThrow(Exception.class);

		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(leilaoDAO);

		for (int i = 5; i < 15; i++) {
			encerrador.excluiUsuario(i);
		}

	}

	@Test
	public void deveVerificarRepeticao() throws Exception {
		LeilaoDAO leilaoDAO = mock(LeilaoDAO.class);
		for (int i = 1; i < 10; i++) {
			System.out.println(leilaoDAO.enviarEmail(i, i + 1));
			System.out.println(leilaoDAO.enviarEmail(1 + 2, i));
		}
		verify(leilaoDAO, times(18)).enviarEmail(any(int.class), any(int.class));
		verify(leilaoDAO, atMost(2)).enviarEmail(eq(1), any(int.class));
		verify(leilaoDAO, atLeast(2)).enviarEmail(any(int.class), eq(3));
	}

}
