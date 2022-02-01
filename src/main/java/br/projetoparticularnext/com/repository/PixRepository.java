package br.projetoparticularnext.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.projetoparticularnext.com.model.pix.Pix;

@Repository
public interface PixRepository extends JpaRepository<Pix, Long> {

}
