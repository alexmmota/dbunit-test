package br.com.algartelecom.dbunittest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CT_PEDIDO")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ct_pedido_sq", sequenceName="ct_pedido_ctcdpedido_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ct_pedido_sq")
	@Column(name="CTCDPEDIDO")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="CTCDCLIENTE")
	private Cliente cliente;
	
	@Column(name="CTDATA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Column(name = "CTVALOR", precision = 20, scale = 2)
	private Double valor;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<PedidoItem> itens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public List<PedidoItem> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItem> itens) {
		this.itens = itens;
	}

}
