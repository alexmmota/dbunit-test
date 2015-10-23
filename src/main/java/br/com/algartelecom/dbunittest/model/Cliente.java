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
@Table(name="CT_CLIENTE")
public class Cliente implements Serializable {

	public Cliente(){}

	public Cliente(Long id){
		this.id = id;
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ct_cliente_sq", sequenceName="ct_cliente_ctcdcliente_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ct_cliente_sq")
	@Column(name="CTCDCLIENTE")
	private Long id;
	
	@Column(name="CTNMCLIENTE")
	private String nome;
	
	@Column(name="CTTELEFONE")
	private String telefone;
	
	@Column(name="CTENDERECO")
	private String endereco;

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
