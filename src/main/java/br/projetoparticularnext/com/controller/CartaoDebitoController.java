package br.projetoparticularnext.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.projetoparticularnext.com.model.cartao.CartaoDebito;
import br.projetoparticularnext.com.model.cartao.Compra;
import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.service.ContaService;
import br.projetoparticularnext.com.service.cartao.CartaoDebitoService;
import br.projetoparticularnext.com.utils.Const;
import br.projetoparticularnext.com.utils.Utils;

@Controller
@RequestMapping("/arealogado/cartao/debito/")
public class CartaoDebitoController {

	@Autowired
	CartaoDebitoService cartaoDebitoService;

	@Autowired
	ContaService contaService;
	
	
	
	@GetMapping
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.setViewName("cartao/debito/cartaoDebito");
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		cartaoDebitoService.verificaCartao(modelAndView, conta);
		return modelAndView;
	}
	
	@PostMapping("criar")
	public ModelAndView create( ModelAndView modelAndView,
			RedirectAttributes attributes, CartaoDebito debito, BindingResult result) {
		System.err.println("ANTES DO IF");
		if (!result.hasErrors()) {
			Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
			System.err.println("ENTROU NO CADASTRA");
			conta = cartaoDebitoService.criar(debito, conta);
			contaService.createConta(conta);
			modelAndView.setViewName("cartao/debito/cartaoDebito");
			modelAndView.addObject("ok", "Cartao de débito criado com sucesso!");
			cartaoDebitoService.verificaCartao(modelAndView, conta);
			return modelAndView;
		} else {
			modelAndView.addObject("erro", "Houve um erro no cartao!");
			modelAndView.setViewName("cartao/debito/cartaoDebito");
			return modelAndView;
		}
	}

	
	@GetMapping("criar")
    public ModelAndView chamaCriar( ModelAndView modelAndView,CartaoDebito debito) {
		System.err.println("entrou criar");
    	Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
    	modelAndView.addObject("conta",conta);
    	modelAndView.setViewName("cartao/debito/criarDebito");
		return modelAndView;
    }
	@GetMapping("ativar")
    public ModelAndView chamaAtivacao( ModelAndView modelAndView,CartaoDebito debito) {
		System.err.println("entrou Ativar");
    	Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
    	modelAndView.addObject("conta",conta);
    	conta.getDebito().setAtivo(!conta.getDebito().isAtivo());
    	cartaoDebitoService.verificaCartao(modelAndView, conta);
    	contaService.createConta(conta);
    	modelAndView.setViewName("cartao/debito/cartaoDebito");
		return modelAndView;
    }
	@GetMapping("deletar")
    public ModelAndView chamaDelete( ModelAndView modelAndView,CartaoDebito debito) {
		System.err.println("entrou deletar");
    	Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
    	modelAndView.addObject("debito",conta.getDebito());
    	modelAndView.setViewName("cartao/debito/deletarDebito");
		return modelAndView;
    }
	@PostMapping("deletar")
	public ModelAndView delete( ModelAndView modelAndView,
			RedirectAttributes attributes, CartaoDebito debito, BindingResult result) {
		System.err.println("ANTES DO IF");
		if (!result.hasErrors()) {
			Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
			System.err.println("ENTROU NO DELETE");
			cartaoDebitoService.delete(conta.getDebito().getId());
			conta.setDebito(null);
			contaService.createConta(conta);
			modelAndView.setViewName("cartao/debito/cartaoDebito");
			modelAndView.addObject("ok", "Cartao de débito deletado com sucesso!");
			cartaoDebitoService.verificaCartao(modelAndView, conta);
			return modelAndView;
		} else {
			modelAndView.addObject("erro", "Houve um erro ao deletar cartao!");
			modelAndView.setViewName("cartao/debito/cartaoDebito");
			return modelAndView;
		}
	}
	
	@GetMapping("comprar")
    public ModelAndView chamaComprar(ModelAndView modelAndView) {
		System.err.println("entrou comprar");
    	Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
    	modelAndView.addObject("conta",conta);
    	modelAndView.setViewName("cartao/debito/comprarDebito");
		return modelAndView;
    }
	
	@PostMapping("comprar")
	public ModelAndView comprar(ModelAndView modelAndView,
			RedirectAttributes attributes, Compra compra,String senha) {
		System.err.println("COMPRAR O QUE VEIO DO FOMR: "+senha+" "+compra.getDescricao()+" "+compra.getValor());
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		System.err.println("ENTROU NO COMPRA");
		if(conta.getDebito().getSenha().equals(senha)) {
			if(conta.getDebito().getLimitePorTransacao() >= compra.getValor()) {
				if(conta.getSaldo() >= compra.getValor()) {
					if(compra.getValor() > 0) {
						conta.setSaldo(conta.getSaldo() - compra.getValor());
						compra.setDataCompra(Utils.dataAtual());
						compra.setCartao(conta.getDebito());
						conta.getDebito().getCompras().add(compra);
						contaService.createConta(conta);
						modelAndView.setViewName("cartao/debito/comprarDebito");
						modelAndView.addObject("ok", "Compra efetuada com sucesso!");
						return modelAndView;
					}else {
						modelAndView.setViewName("cartao/debito/comprarDebito");
						modelAndView.addObject("erro", "Compra negada!");
						return modelAndView;
					}
				}else {
					modelAndView.setViewName("cartao/debito/comprarDebito");
					modelAndView.addObject("erro", "Saldo insuficiente!");
					return modelAndView;
				}
			}else {
				modelAndView.setViewName("cartao/debito/comprarDebito");
				modelAndView.addObject("erro", "O limite é inferior ao valor da compra!");
				return modelAndView;
			}
			//realizar compra
		}else {
			// senha invalida
			modelAndView.setViewName("cartao/debito/comprarDebito");
			modelAndView.addObject("erro", "Senha invalida!");
			return modelAndView;
		}
	}


	
	
	@GetMapping("consultar")
    public ModelAndView chamaConsulta( ModelAndView modelAndView) {
		System.err.println("entrou consulta");
    	Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
    	modelAndView.addObject("compras",conta.getDebito().getCompras());
    	modelAndView.addObject("debito",conta.getDebito());
    	modelAndView.setViewName("cartao/debito/consultarDebito");
    	System.err.println("SIZE COMPRAS "+conta.getDebito().getCompras().size());
		return modelAndView;
    }
	
	
}
