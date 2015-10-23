package br.com.algartelecom.dbunittest.repository;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.algartelecom.dbunittest.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Serializable> {

}
