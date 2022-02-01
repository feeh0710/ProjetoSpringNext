package br.projetoparticularnext.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.projetoparticularnext.com.model.cliente.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
