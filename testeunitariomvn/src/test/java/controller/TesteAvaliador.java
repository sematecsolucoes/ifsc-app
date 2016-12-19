package controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static util.matcher.NaoPossuiSequenciaMatcher.naoPossuiSequencia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.sematec.exception.LeilaoDesertoException;
import br.com.sematec.exception.RestException;
import br.com.sematec.exception.UsuarioInvalidoException;
import br.com.sematec.exception.ValorInvalidoException;
import br.com.sematec.modelo.Lance;
import br.com.sematec.modelo.Leilao;
import br.com.sematec.modelo.LeilaoBuilder;
import br.com.sematec.modelo.Usuario;

public class TesteAvaliador extends BaseTeste {

	double menorValorEsperado = 1000.00d;

	double maiorValorEsperado = 3000.00d;

	Leilao leilao = getLeilaoBuilder().build();

	List<Lance> lances = new ArrayList<>();

	@Test(expected = RestException.class)
	public void _naoDevePermitirLeilaoDeserto2() throws RestException {
		logMethodName();
		leilao.setLances(lances);

		Avaliador avaliador = new Avaliador();
		try {
			avaliador.avalia2(leilao);
		} catch (RestException e) {
			assertEquals(RestException.CLASS_NOT_FOUND.getCodigoErro(), e.getCodigoErro());
			throw e;
		}
	}

	// Um leilão com 5 lances, deve encontrar os três maiores
	@Test
	public void deveEncontrarTresMaiores() throws Exception {
		logMethodName();
		Leilao leilao = getLeilaoBuilder().comLance("João", 2000.00).comLance("José", 1000.00).comLance("Maria", 3000.00).comLance("João", 2500.00).comLance("José", 1500.00)
				.comLance("Maria", 3500.00).build();

		Avaliador avaliador = new Avaliador();
		List<Lance> tresMaiores = avaliador.getTresMaiores(leilao);

		assertThat(tresMaiores, hasItems(new Lance(new Usuario("Maria"), 3500.00), new Lance(new Usuario("Maria"), 3000.00), new Lance(new Usuario("João"), 2500.00)));

	}

	@Test
	public void deveEntenderOrdemAleatoria() throws Exception {
		logMethodName();
		lances.add(new Lance(new Usuario("João"), 2000.00));
		lances.add(new Lance(new Usuario("José"), 1000.00));
		lances.add(new Lance(new Usuario("Maria"), 3000.00));

		leilao.setLances(lances);

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		assertThat(avaliador.getMenorLance(), is(equalTo(menorValorEsperado)));
		assertThat(avaliador.getMaiorLance(), is(equalTo(maiorValorEsperado)));

	}

	@Test
	public void deveEntenderOrdemAleatoria2() throws LeilaoDesertoException, UsuarioInvalidoException, ValorInvalidoException {
		logMethodName();
		Leilao leilao = getLeilaoBuilder().comLance("João", 2000.00).comLance("José", 1000.00).comLance("Maria", 3000.00).build();

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		assertEquals(menorValorEsperado, avaliador.getMenorLance(), 0.0000001);
		assertEquals(maiorValorEsperado, avaliador.getMaiorLance(), 0.0000001);
	}

	@Test
	public void deveEntenderOrdemCrescente() throws Exception {
		logMethodName();

		lances.add(new Lance(new Usuario("José"), 1000.00));
		lances.add(new Lance(new Usuario("João"), 2000.00));
		lances.add(new Lance(new Usuario("Maria"), 3000.00));

		leilao.setLances(lances);

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		assertEquals(menorValorEsperado, avaliador.getMenorLance(), 0.0000001);
		assertEquals(maiorValorEsperado, avaliador.getMaiorLance(), 0.0000001);
	}

	@Test
	public void deveGerarExcecaoLancesEmSequencia() throws UsuarioInvalidoException, ValorInvalidoException {
		lances.add(new Lance(new Usuario("João"), 2000.00));
		lances.add(new Lance(new Usuario("Maria"), 3000.00));
		lances.add(new Lance(new Usuario("Maria"), 1000.00));
		leilao.setLances(lances);
		assertThat(leilao, naoPossuiSequencia(leilao));
	}

	private LeilaoBuilder getLeilaoBuilder() {
		return new LeilaoBuilder().comData(Calendar.getInstance()).comNome("leilão do fusca");
	}

	@Before
	public void instanciaDados() {
		logMethodName();
		leilao = getLeilaoBuilder().build();
		lances = new ArrayList<>();
	}

	@After
	public void limpaDados() {
		logMethodName();
		leilao = null;
		lances = null;
	}

	@Test(expected = LeilaoDesertoException.class)
	public void naoDevePermitirLeilaoDeserto() throws LeilaoDesertoException {
		logMethodName();
		leilao.setNome("leilão do fusca");

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
	}
}
