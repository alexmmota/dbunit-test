package br.com.algartelecom.dbunittest.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CT_PEDIDOITEM")
public class PedidoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ct_pedidoitem_sq", sequenceName="ct_pedidoitem_ctcdpedidoitem_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ct_pedidoitem_sq")
	@Column(name="CTCDPEDIDOITEM")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CTCDPEDIDO")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name="CTCDPRODUTO")
	private Produto produto;

	@Column(name = "CTQUANTIDADE", nullable = false, precision = 20, scale = 0)
	private Long quantidade;
	
	@Column(name = "CTVALOR", nullable = false, precision = 20, scale = 2)
	private Double valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
