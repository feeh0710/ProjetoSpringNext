package br.projetoparticularnext.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.projetoparticularnext.com.model.conta.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
	List<Conta> findAllContaByClienteId(@Param("cliente_id") Long cliente_id);
}
