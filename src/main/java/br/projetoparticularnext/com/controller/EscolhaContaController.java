package br.projetoparticularnext.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.service.ContaService;
import br.projetoparticularnext.com.utils.Const;

@Controller
@RequestMapping("escolhaconta")
public class EscolhaContaController {
	
	@Autowired
	ContaService contaService;
	
	@GetMapping
	public String escolhaconta(Model model) {
		System.err.println("ENTRO NO ESCOLHA CONTA: "+Const.ID_CLIENTE_LOGADO);
		List<Conta> contas = contaService.getAllContas(Const.ID_CLIENTE_LOGADO);
		model.addAttribute("contas",contas);
		return "escolhaconta";
	}
	
	@GetMapping("arealogado/{id}")
	public String tipoConta(@PathVariable(value = "id") long id, Model model) {
		Conta conta = contaService.getConta(id);
		Const.ID_CONTA_LOGADA = conta.getId();
//		Const.TIPO_CONTA_LOGADO = conta.getTipoConta().ordinal();
		System.err.println("entrou no escolha conta tipo");
		model.addAttribute("conta",conta);
		return "/arealogado";
	}
}
