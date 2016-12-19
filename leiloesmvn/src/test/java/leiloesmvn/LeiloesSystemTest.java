package leiloesmvn;

import static org.junit.Assert.assertTrue;
import leiloesmvn.pages.leilao.LeiloesPage;
import leiloesmvn.pages.leilao.NovoLeilaoPage;
import leiloesmvn.pages.usuario.UsuariosPage;

import org.junit.Before;
import org.junit.Test;

public class LeiloesSystemTest extends BaseTest {

	private LeiloesPage leiloes;

	@Test
	public void deveCadastrarUmLeilao() {
		leiloes.visita();
		NovoLeilaoPage novoLeilao = leiloes.novo();
		novoLeilao.preenche("Geladeira", 123, "Usuario Teste", true);
		assertTrue(leiloes.existe("Geladeira", 123, "Usuario Teste", true));
	}

	@Override
	@Before
	public void inicializa() {
		super.inicializa();

		leiloes = new LeiloesPage(driver);
		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		usuarios.novo().cadastra("Usuario Teste", "usuario@teste.com");
	}

}
