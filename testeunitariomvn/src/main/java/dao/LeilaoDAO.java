package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sematec.modelo.Lance;
import br.com.sematec.modelo.Leilao;

public class LeilaoDAO {
	private Connection conexao;

	int i = 0;
	int valor = 1;

	public LeilaoDAO() {
		try {
			this.conexao = DriverManager.getConnection(
					"jdbc:mysql://localhost/mocks", "root", "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int enviarEmail(Double double1, Leilao leilao) {
		return 3;
	}

	public int enviarEmail(int base, int pow) {
		if (i < pow) {
			i++;
			valor *= base;
			return enviarEmail(base, pow);
		}
		return valor;
	}

	public int enviarEmail(Leilao leilao, Lance lance) {
		return 4;
	}

	public int excluirUsuario() throws Exception {

		boolean usuarioComdivida = Math.random() > 0.5;
		if (usuarioComdivida) {
			throw new Exception();
		}
		return 1;
	}

	public List<Leilao> naoCcorrentes() {
		try {
			PreparedStatement stmt = conexao.prepareStatement("select XXX");
			ResultSet rs = stmt.executeQuery();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Leilao>();
	}

	public void save(Leilao leilao) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("insert XXX");
			stmt.executeQuery();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Leilao leilao) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("update XXX");
			stmt.executeQuery();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
