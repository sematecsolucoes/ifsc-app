package br.com.sematec.produtos.dao;

import java.util.HashMap;
import java.util.Map;

import br.com.sematec.modelo.Usuario;

public class UsuarioDAO {
	private final static Map<String, Usuario> USUARIOS = new HashMap<>();
	static {
		USUARIOS.put("professor@sematecsolucoes.com.br", new Usuario(1L, "professor", "professor@sematecsolucoes.com.br", "professor"));
		USUARIOS.put("diretor@sematecsolucoes.com.br", new Usuario(2L, "diretor", "diretor@sematecsolucoes.com.br", "diretor"));
		USUARIOS.put("teste@teste.com", new Usuario(3L, "teste", "teste@teste.com", "teste"));
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

	public Usuario buscaUsuarioPorLoginESenha(Usuario usuario) {
		if (!USUARIOS.containsKey(usuario.getLogin())) {
			return null;
		}
		String login = usuario.getLogin();
		String senha = usuario.getSenha();
		usuario = USUARIOS.get(login);
		if (usuario.getSenha().equals(senha)) {
			return usuario;
		}
		return null;
	}
}
