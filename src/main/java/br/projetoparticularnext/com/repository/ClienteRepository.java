package br.projetoparticularnext.com.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.projetoparticularnext.com.model.cliente.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	@Query("Select c From Cliente c where c.cpf = ?1 and c.senha = ?2")
	Cliente findByCpfAndSenha(String cpf,String senha);
}
