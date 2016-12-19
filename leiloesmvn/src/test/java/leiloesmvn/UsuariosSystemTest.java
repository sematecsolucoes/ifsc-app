package leiloesmvn;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import leiloesmvn.pages.usuario.NovoUsuarioPage;
import leiloesmvn.pages.usuario.UsuariosPage;

import org.junit.Before;
import org.junit.Test;

public class UsuariosSystemTest extends BaseTest {
	private UsuariosPage usuarios;

	@Test
	public void deveAdicionarUmUsuario() {
		usuarios.visita();
		usuarios.novo().cadastra("Usuario Teste", "usuario@teste.com");
		assertTrue(usuarios.existeNaListagem("Usuario Teste", "usuario@teste.com"));
	}

	@Test
	public void deveAlterarUmUsuario() {
		usuarios.visita();
		usuarios.novo().cadastra("Usuario Teste", "usuario@teste.com");
		usuarios.altera(1).para("José da Silva", "jose@silva.com");
		assertFalse(usuarios.existeNaListagem("Usuario Teste", "usuario@teste.com"));
		assertTrue(usuarios.existeNaListagem("José da Silva", "jose@silva.com"));
	}

	@Test
	public void deveDeletarUmUsuario() {
		usuarios.visita();
		usuarios.novo().cadastra("Usuario Teste", "usuario@teste.com");
		assertTrue(usuarios.existeNaListagem("Usuario Teste", "usuario@teste.com"));
		usuarios.deletaUsuarioNaPosicao(1);
		assertFalse(usuarios.existeNaListagem("Usuario Teste", "usuario@teste.com"));
	}

	@Override
	@Before
	public void inicializa() {
		super.inicializa();
		this.usuarios = new UsuariosPage(driver);
	}

	@Test
	public void naoDeveAdicionarUmUsuarioSemNome() {
		usuarios.visita();
		NovoUsuarioPage form = usuarios.novo();
		form.cadastra("", "usuario@teste.com");
		assertTrue(form.validacaoDeNomeObrigatorio());
	}

	@Test
	public void naoDeveAdicionarUmUsuarioSemNomeOuEmail() {
		// TODO
	}

}
