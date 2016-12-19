package br.com.sematec.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Livro livro;
	private Integer quantidade;

	public Venda() {
	}

	public Venda(Livro livro, Integer quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
	}

	public Livro getLivro() {
		return livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
