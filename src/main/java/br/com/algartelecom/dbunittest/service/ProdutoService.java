package br.com.algartelecom.dbunittest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.algartelecom.dbunittest.model.Produto;
import br.com.algartelecom.dbunittest.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public void save(Produto produto){
		this.produtoRepository.save(produto);
	}

	@Transactional
	public void remove(Long produtoId){
		this.produtoRepository.delete(produtoId);
	}

	@Transactional
	public void add(Long produtoId, Long qtd){
		Produto produto = produtoRepository.findOne(produtoId);
		produto.setQuantidade(produto.getQuantidade()+qtd);
		this.produtoRepository.save(produto);
	}

}
