package br.projetoparticularnext.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.projetoparticularnext.com.model.cartao.Apolice;
import br.projetoparticularnext.com.model.cartao.CartaoCredito;
import br.projetoparticularnext.com.model.cartao.Seguro;
import br.projetoparticularnext.com.model.cartao.TipoSeguro;
import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.service.ContaService;
import br.projetoparticularnext.com.service.cartao.ApoliceService;
import br.projetoparticularnext.com.service.cartao.CartaoCreditoService;
import br.projetoparticularnext.com.service.cartao.SeguroService;
import br.projetoparticularnext.com.utils.Const;
import br.projetoparticularnext.com.utils.UtilFormatConsole;
import br.projetoparticularnext.com.utils.Utils;

@Controller
@RequestMapping("/arealogado/cartao/credito/seguro")
public class SeguroController {
	
	@Autowired
	CartaoCreditoService cartaoCreditoService;

	@Autowired
	ContaService contaService;
	
	@Autowired
	SeguroService seguroService;
	
	@Autowired
	ApoliceService apoliceService;
	
	
	@GetMapping
	public ModelAndView index(ModelAndView modelAndView) {
		modelAndView.setViewName("cartao/credito/seguro/seguros");
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		seguroService.verificaSeguro(modelAndView, conta,"seguro/");
		return modelAndView;
	}
	@GetMapping("contratar")
    public ModelAndView chamaCriar( ModelAndView modelAndView,CartaoCredito credito) {
		System.err.println("entrou contratar");
    	Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
    	modelAndView.addObject("conta",conta);
    	modelAndView.setViewName("cartao/credito/seguro/contratarSeguro");
		return modelAndView;
    }
	@GetMapping("visualizar")
    public ModelAndView chamaVisualizar( ModelAndView modelAndView) {
		System.err.println("entrou visualizar");
    	Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
    	modelAndView.addObject("conta",conta);
    	modelAndView.setViewName("cartao/credito/seguro/visualizarApolice");
		return modelAndView;
    }
	@GetMapping("cancelar")
    public ModelAndView chamaCancelar( ModelAndView modelAndView) {
		System.err.println("entrou cancelar");
    	Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
    	modelAndView.addObject("conta",conta);
    	modelAndView.setViewName("cartao/credito/seguro/cancelarApolice");
		return modelAndView;
    }
	@PostMapping("cancelar")
	public ModelAndView cancelar( ModelAndView modelAndView, BindingResult result) {
		System.err.println("ANTES DO IF");
		if (!result.hasErrors()) {
			Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
			System.err.println("ENTROU NO CANCELAR");
			
			apoliceService.delete(conta.getCredito().getApolice().getId());
			
			conta.getCredito().setApolice(null);
			Const.APOLICE_PROVISORIA = null;
			Const.TAXA_TOTAL_APOLICE = 0.0;
			contaService.createConta(conta);
			seguroService.verificaSeguro(modelAndView, conta, "");
			modelAndView.addObject("ok", "Seguro cancelar com sucesso!");
			modelAndView.setViewName("cartao/credito/seguro/seguros");
			return modelAndView;
		} else {
			modelAndView.addObject("erro", "Houve um erro no cartao!");
			modelAndView.setViewName("cartao/credito/seguro/seguros");
			return modelAndView;
		}
	}
	
	@PostMapping("simular")
	public ModelAndView simular( ModelAndView modelAndView,
			RedirectAttributes attributes, String tipo,int tempo, BindingResult result) {
		System.err.println("ANTES DO IF");
		if (!result.hasErrors()) {
			Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
			System.err.println("ENTROU NO SIMULAR");
			Apolice apolice = new Apolice(new Seguro(seguroService.getTipo(tipo)), tempo);
			modelAndView.setViewName("cartao/credito/seguro/apoliceProvisoria");
			conta.getCredito().setApolice(apolice);
			Const.APOLICE_PROVISORIA = conta.getCredito().getApolice();
			modelAndView.addObject("conta", conta);
			modelAndView.addObject("regras", TipoSeguro.MORTE.getRegras(apolice.getSeguro().getRegras()));
			modelAndView.addObject("valortotal",(apolice.getAnos() * apolice.getSeguro().getTaxa()));
			Const.TAXA_TOTAL_APOLICE = (apolice.getAnos() * apolice.getSeguro().getTaxa());
			return modelAndView;
		} else {
			modelAndView.addObject("erro", "Houve um erro no cartao!");
			modelAndView.setViewName("cartao/credito/cartaoCredito");
			return modelAndView;
		}
	}
	
	@PostMapping("contratar")
	public ModelAndView contratar( ModelAndView modelAndView,
			RedirectAttributes attributes, BindingResult result) {
		Apolice apolice = Const.APOLICE_PROVISORIA;
		System.err.println("RECEBENDO CONTA DO FORM SEGURO   \n"+apolice.toString());
		if (!result.hasErrors()) {
			Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
			if(conta.getSaldo() >= Const.TAXA_TOTAL_APOLICE) {
				conta.setSaldo(conta.getSaldo()-Const.TAXA_TOTAL_APOLICE);
				conta.getCredito().setApolice(apolice);
				contaService.createConta(conta);
				seguroService.verificaSeguro(modelAndView, conta, "");
				modelAndView.addObject("ok", "Apolice criada com sucesso! \n Debito de "+Const.TAXA_TOTAL_APOLICE+",00 aplicado");
				modelAndView.setViewName("cartao/credito/seguro/seguros");
				return modelAndView;
			}else {
				seguroService.verificaSeguro(modelAndView, conta, "");
				modelAndView.addObject("erro", "Você não possui saldo para contratar seguro");
				modelAndView.setViewName("cartao/credito/seguro/seguros");
				return modelAndView;
			}
			
		} else {
			modelAndView.addObject("erro", "Houve um erro no cartao!");
			modelAndView.setViewName("cartao/credito/cartaoCredito");
			return modelAndView;
		}
	}
	
	
	
	
	
}
