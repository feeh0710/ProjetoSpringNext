package br.projetoparticularnext.com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.projetoparticularnext.com.model.pix.Pix;

@Repository
public interface PixRepository extends JpaRepository<Pix, Long> {
	Optional<Pix> findByConteudoChave(String conteudoChave);
}
