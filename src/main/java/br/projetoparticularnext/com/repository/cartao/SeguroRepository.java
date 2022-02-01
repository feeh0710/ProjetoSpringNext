package br.projetoparticularnext.com.repository.cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.projetoparticularnext.com.model.cartao.Seguro;

@Repository
public interface SeguroRepository extends JpaRepository<Seguro, Long> {

}
