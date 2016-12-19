package util.matcher;

import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.sematec.modelo.Lance;
import br.com.sematec.modelo.Leilao;

/** http://www.jmock.org/custom-matchers.html */
public class NaoPossuiSequenciaMatcher extends TypeSafeMatcher<Leilao> {
	@Factory
	public static Matcher<Leilao> naoPossuiSequencia(Leilao leilao) {
		return new NaoPossuiSequenciaMatcher(leilao);
	}

	private Leilao leilao;

	public NaoPossuiSequenciaMatcher(Leilao leilao) {
		this.leilao = leilao;
	}

	@Override
	public void describeTo(Description description) {
		List<Lance> lances = leilao.getLances();
		description.appendText("lance em sequencia para usuario ").appendValue(
				lances.get(lances.size() - 1).getUsuario().getNome());
	}

	@Override
	public boolean matchesSafely(Leilao leilao) {
		boolean equals = false;
		List<Lance> lances = leilao.getLances();
		int size = lances.size();
		if (size > 1) {
			String nomeUltimo = lances.get(size - 1).getUsuario().getNome();
			String nomePenultimo = lances.get(size - 2).getUsuario().getNome();
			equals = nomePenultimo.equals(nomeUltimo);
		}
		return equals;
	}
}