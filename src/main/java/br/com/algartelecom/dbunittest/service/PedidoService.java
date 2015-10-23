package br.com.algartelecom.dbunittest.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.algartelecom.dbunittest.model.Pedido;
import br.com.algartelecom.dbunittest.model.PedidoItem;
import br.com.algartelecom.dbunittest.model.Produto;
import br.com.algartelecom.dbunittest.repository.PedidoItemRepository;
import br.com.algartelecom.dbunittest.repository.PedidoRepository;
import br.com.algartelecom.dbunittest.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PedidoItemRepository pedidoItemRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public void save(Pedido pedido){
		pedido.setData(new LocalDate().toDate());
		pedidoRepository.save(pedido);
	}
	
	@Transactional
	public Pedido find(Long pedidoId){
		return pedidoRepository.findOne(pedidoId);
	}

	@Transactional
	public void addItem(Long pedidoId, Long produtoId, Long qtd){
		Pedido pedido = pedidoRepository.findOne(pedidoId);
		Produto produto = produtoRepository.findOne(produtoId);
		List<PedidoItem> itens = pedido.getItens() != null ? pedido.getItens() : new ArrayList<PedidoItem>();

		PedidoItem item = new PedidoItem();
		item.setProduto(produto);
		item.setPedido(pedido);
		item.setQuantidade(qtd);
		item.setValor(produto.getValor()*qtd);
		itens.add(item);

		pedido.setValor(pedido.getValor()+item.getValor());	
		
		produto.setQuantidade(produto.getQuantidade()-qtd);
		
		pedidoRepository.save(pedido);
		pedidoItemRepository.save(item);
		produtoRepository.save(produto);
	}
	
}
