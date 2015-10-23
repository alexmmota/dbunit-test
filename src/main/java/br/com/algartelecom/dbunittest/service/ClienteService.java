package br.com.algartelecom.dbunittest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.algartelecom.dbunittest.model.Cliente;
import br.com.algartelecom.dbunittest.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public void save(Cliente cliente){
		this.clienteRepository.save(cliente);
	}

	@Transactional
	public void remove(Long clienteId){
		this.clienteRepository.delete(clienteId);
	}

	@Transactional
	public void changeAddress(Long clienteId, String newAddress){
		Cliente cliente = clienteRepository.findOne(clienteId);
		cliente.setEndereco(newAddress);
		this.clienteRepository.save(cliente);
	}
	
}
