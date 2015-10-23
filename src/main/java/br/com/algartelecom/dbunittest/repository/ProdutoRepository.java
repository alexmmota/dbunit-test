package br.com.algartelecom.dbunittest.repository;

import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.algartelecom.dbunittest.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Serializable> {

}
