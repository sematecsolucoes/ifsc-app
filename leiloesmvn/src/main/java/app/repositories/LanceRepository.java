package app.repositories;

import br.com.sematec.modelo.Lance;

public interface LanceRepository {

	void salvar(Lance lance);
	Lance porId(Long id);
}
