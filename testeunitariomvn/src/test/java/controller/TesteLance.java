package controller;
import org.junit.Test;

import br.com.sematec.exception.UsuarioInvalidoException;
import br.com.sematec.exception.ValorInvalidoException;
import br.com.sematec.modelo.Lance;
import br.com.sematec.modelo.Usuario;

public class TesteLance extends BaseTeste {

	/** nao deve permitir lance sem usuario */
	@Test(expected = UsuarioInvalidoException.class)
	public void deveGerarExcecaoComLanceSemUsuario()
			throws UsuarioInvalidoException {
		logMethodName();
		try {
			new Lance(null, 2000.00);
		} catch (ValorInvalidoException e) {
			// ERRO IGNORADO
		}
	}

	/** nao deve permitir valores inexistente, menor ou igual a zero */
	@Test(expected = ValorInvalidoException.class)
	public void deveGerarExcecaoComValorInvalido()
			throws ValorInvalidoException {
		logMethodName();
		try {
			new Lance(new Usuario(""), null);
		} catch (UsuarioInvalidoException e) {
			// ERRO IGNORADO
		}
	}
}
