package br.projetoparticularnext.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.model.pix.Pix;
import br.projetoparticularnext.com.repository.PixRepository;

@Service
public class PixService {
	
	@Autowired
	PixRepository pixRepository;

	public Pix criaPix(Pix pix) {
		return pixRepository.save(pix);
	}

	public void verificaPix(ModelAndView modelAndView, Conta conta) {
		if (conta.getListPix() != null) {
			System.err.println("C PIX");
			modelAndView.addObject("cadastrar", "cadastrar");
			if (conta.getListPix().size() == 4) {
				modelAndView.addObject("limite", "");
				modelAndView.addObject("desbloqueado", "");
			} else {
				modelAndView.addObject("ativado", "Desbloquear");

			}
		} else {
			System.err.println("N CART");
			modelAndView.addObject("ncadastrado", "Criar Cartão");
		}
	}
}
