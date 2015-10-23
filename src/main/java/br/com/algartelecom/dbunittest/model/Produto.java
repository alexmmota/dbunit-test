package br.com.algartelecom.dbunittest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CT_PRODUTO")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ct_produto_sq", sequenceName="ct_produto_ctcdproduto_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ct_produto_sq")
	@Column(name="CTCDPRODUTO")
	private Long id;
	
	@Column(name="CTNMPRODUTO")
	private String nome;
	
	@Column(name="CTVALOR", nullable = false, precision = 20, scale = 2)
	private Double valor;
	
	@Column(name="CTQUANTIDADE", nullable = false, precision = 20, scale = 0)
	private Long quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
