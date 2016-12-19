package leiloesmvn;

import static org.junit.Assert.assertTrue;
import leiloesmvn.pages.leilao.DetalhesDoLeilaoPage;
import leiloesmvn.pages.leilao.LeiloesPage;
import leiloesmvn.pages.usuario.UsuariosPage;

import org.junit.Before;
import org.junit.Test;

public class LanceSystemTest extends BaseTest {
	private LeiloesPage leiloes;

	@Test
	public void deveFazerUmLance() {
		DetalhesDoLeilaoPage lances = leiloes.detalhes(1);
		lances.lance("Teste Usuario", 150);
		assertTrue(lances.existeLance("Teste Usuario", 150));
	}

	@Override
	@Before
	public void inicializa() {
		super.inicializa();
		UsuariosPage usuarios = new UsuariosPage(driver);
		usuarios.visita();
		usuarios.novo().cadastra("Usuario Teste", "usuario@teste.com");
		usuarios.novo().cadastra("Teste Usuario", "teste@usuario.com");
		leiloes = new LeiloesPage(driver);
		leiloes.visita();
		leiloes.novo().preenche("Geladeira", 100, "Usuario Teste", false);
	}
}
