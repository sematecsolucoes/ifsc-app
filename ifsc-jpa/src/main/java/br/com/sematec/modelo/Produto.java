package br.com.sematec.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private Date dataInicioVenda;
	private boolean usado;

	public Produto() {
		super();
	}

	public Produto(String nome, String descricao, Double preco, String dataInicioVenda, boolean usado) {
		this();
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		try {
			this.dataInicioVenda = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicioVenda);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.usado = usado;
	}

	public Date getDataInicioVenda() {
		return dataInicioVenda;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getPreco() {
		return preco;
	}

	public boolean isUsado() {
		return usado;
	}

	public void setDataInicioVenda(Date dataInicioVenda) {
		this.dataInicioVenda = dataInicioVenda;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public void setUsado(boolean usado) {
		this.usado = usado;
	}
}
