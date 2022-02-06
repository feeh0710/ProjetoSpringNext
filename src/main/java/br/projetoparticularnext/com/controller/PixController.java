package br.projetoparticularnext.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.service.ContaService;
import br.projetoparticularnext.com.service.PixService;
import br.projetoparticularnext.com.utils.Const;

@Controller
@RequestMapping("/arealogado/pix/")
public class PixController {

	@Autowired
	PixService PixService;

	@Autowired
	ContaService contaService;

	@GetMapping
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.setViewName("cartao/credito/cartaoCredito");
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		modelAndView.setViewName("/arealogado/pix");
		PixService.verificaPix(modelAndView, conta);
		return modelAndView;
	}
}
