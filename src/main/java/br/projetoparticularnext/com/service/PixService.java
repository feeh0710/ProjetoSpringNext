package br.projetoparticularnext.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projetoparticularnext.com.model.pix.Pix;
import br.projetoparticularnext.com.repository.PixRepository;

@Service
public class PixService {
	@Autowired
	PixRepository pixRepository;

	public Pix criaPix(Pix pix) {
		return pixRepository.save(pix);
	}
}
