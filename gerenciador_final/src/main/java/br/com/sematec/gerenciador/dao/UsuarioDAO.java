package br.com.sematec.gerenciador.dao;

import java.util.HashMap;
import java.util.Map;

import br.com.sematec.modelo.Usuario;

public class UsuarioDAO {

	private final static Map<String, Usuario> USUARIOS = new HashMap<>();
	static {
		USUARIOS.put("professor@sematecsolucoes.com.br", new Usuario("professor@sematecsolucoes.com.br", "professor"));
		USUARIOS.put("diretor@sematecsolucoes.com.br", new Usuario("diretor@sematecsolucoes.com.br", "diretor"));
		USUARIOS.put("teste@teste.com", new Usuario("teste@teste.com", "teste"));
	}

	public Usuario buscaPorEmailESenha(String email, String senha) {
		if (!USUARIOS.containsKey(email)) {
			return null;
		}

		Usuario usuario = USUARIOS.get(email);
		if (usuario.getSenha().equals(senha)) {
			return usuario;
		}

		return null;
	}

}
