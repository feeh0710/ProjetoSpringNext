package br.projetoparticularnext.com.controller;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.model.pix.Pix;
import br.projetoparticularnext.com.service.ContaService;
import br.projetoparticularnext.com.service.PixService;
import br.projetoparticularnext.com.utils.Const;

@Controller
@RequestMapping("arealogado/pix/")
public class PixController {

	@Autowired
	PixService pixService;

	@Autowired
	ContaService contaService;

	@GetMapping
	public ModelAndView index(ModelAndView modelAndView) {
		System.out.println("ENTROU NO INDEX DO PIX");
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		modelAndView.setViewName("pix/pix");
		pixService.verificaPix(modelAndView, conta,"");
		return modelAndView;
	}
	
	@GetMapping("cadastrar")
	public ModelAndView chamaCadastrar(ModelAndView modelAndView) {
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		modelAndView.addObject("listPix", conta.getListPix());
		modelAndView.setViewName("pix/cadastroPix");
		return modelAndView;
	}
	@GetMapping("transferir")
	public ModelAndView chamaTransferir(ModelAndView modelAndView) {
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		modelAndView.setViewName("pix/transferirPix");
		return modelAndView;
	}
	
	@GetMapping("deletar/{id}")
	public ModelAndView update(@PathVariable(value = "id") long id, ModelAndView modelAndView) {
		System.err.println(id);
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		Pix pix = pixService.getPixById(id);
		System.err.println("CONTA PIX   "+pix.getConta().getCliente().getCpf());
		conta.getListPix().remove(pix);
		contaService.createConta(conta);
		pixService.delete(pix);
		modelAndView.addObject("ok","Pix deletado com sucesso!");
		modelAndView.addObject("listPix", conta.getListPix());
		modelAndView.setViewName("pix/consultaPix");
		
		return modelAndView;
	}

	
	@GetMapping("consultar")
	public ModelAndView chamaConsultar(ModelAndView modelAndView) {
		modelAndView.setViewName("cartao/credito/cartaoCredito");
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		modelAndView.addObject("listPix", conta.getListPix());
		modelAndView.setViewName("pix/consultaPix");
		return modelAndView;
	}
	@PostMapping("transferir")
	public ModelAndView cadastrar(ModelAndView modelAndView,String chavedestino,int valor) {
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		Conta contaDestino = null;
		try {
			Optional<Pix> listaPix = pixService.findByChave(chavedestino);
   			contaDestino = listaPix.get().getConta();
		} catch (NoSuchElementException e) {
			System.err.println("não encontrado");
		}
		if (conta.getSaldo() >= valor ) {
			if(contaDestino != null) {
				if(valor > 0) {
					pixService.transferir(valor, conta, contaDestino);
					contaService.createConta(contaDestino);
					contaService.createConta(conta);
					modelAndView.addObject("ok", "Transferencia para "
					+contaDestino.getCliente().getNome()+" de "+valor+",00 realizada com sucesso!");
				}else {
					modelAndView.addObject("erro", "Erro na transferencia!");
				}
			}else {
				modelAndView.addObject("erro", "Pix não encontrado");
			}
		}else {
			modelAndView.addObject("erro", "Saldo insuficiente");
		}
		modelAndView.setViewName("pix/pix");
		pixService.verificaPix(modelAndView, conta,"");
		return modelAndView;
	}

	
	@PostMapping("cadastrar")
	public ModelAndView cadastrar(ModelAndView modelAndView,String tipochave) {
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		Pix pix = pixService.criaPix(tipochave,conta);
		if(pixService.verificaPixExistente(conta.getListPix(),pix)) {
			conta.getListPix().add(pix);
			pix.setConta(conta);
			contaService.createConta(conta);
			pixService.verificaPix(modelAndView, conta,"");
			modelAndView.addObject("ok", "Pix criado com sucesso!");
			modelAndView.setViewName("pix/pix");
			return modelAndView;

		}else {
			modelAndView.addObject("erro", "O pix já existe!");
			modelAndView.setViewName("pix/pix");
			pixService.verificaPix(modelAndView, conta,"");
			return modelAndView;
		}
	}

	
}
