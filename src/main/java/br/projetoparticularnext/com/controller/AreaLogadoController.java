package br.projetoparticularnext.com.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.projetoparticularnext.com.model.cliente.TipoCliente;
import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.service.ClienteService;
import br.projetoparticularnext.com.service.ContaService;
import br.projetoparticularnext.com.utils.Const;

@Controller
@RequestMapping("arealogado/")
public class AreaLogadoController {
	
	@Autowired
	ContaService contaService;
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping("{id}")
	public String buscaConta(@PathVariable(value = "id") long id,Model model) {
		System.err.println("ENTRO NA CONTA: "+id);
		Const.ID_CONTA_LOGADA = id;
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		model.addAttribute("conta",conta);
		
		return "arealogado";
	}
	
	
	
	@GetMapping("deposito/{id}")
	public String deposita(@PathVariable(value = "id") long id,Model model) {
		System.err.println("DEPOSITO CONTA: "+id);
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		
		model.addAttribute("conta",conta);
		return "arealogado";
	}
	
	@GetMapping("depositar")
    public String chamaDeposita(Model model) {
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		model.addAttribute("conta",conta);
        return "/depositar";
    }

    @GetMapping("sacar")
    public String chamaSaque(Model model) {
    	Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		model.addAttribute("conta",conta);
        return "/saque";
    }
    
    @GetMapping("transferencia")
    public String chamaTransferencia(Model model) {
    	Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		model.addAttribute("conta",conta);
        return "/transferir";
    }
    @GetMapping("areacartoes")
    public String chamaCartoes(Model model) {
    	Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		model.addAttribute("conta",conta);
        return "/cartoes";
    }
    @GetMapping("areapix")
    public String chamaAreaPix(Model model) {
    	Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		model.addAttribute("conta",conta);
        return "/pix";
    }
  
    
    
    
    @PostMapping("saca")
	public String sacar(Model model,int valor, RedirectAttributes redirAttrs) {
		System.err.println("SACANDO: "+valor);
		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
		if(conta.getSaldo() >= valor) {
			conta.setSaldo(conta.getSaldo()-valor);
			conta = TipoCliente.verificaTipoConta(conta);
			contaService.setConta(conta);
			redirAttrs.addFlashAttribute("ok", "Saque de R$"+valor+",00 reais realizada com sucesso");
			return "redirect:/arealogado/"+conta.getId();
			
		}else {
			redirAttrs.addFlashAttribute("erro", "Você não possui saldo suficiente!");
			return "redirect:/arealogado/"+conta.getId();
		}
	}
    @PostMapping("deposita")
   	public String depositar(Model model,int valor, RedirectAttributes redirAttrs) {
   		System.err.println("DEPOSITANDO: "+valor);
   		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
   		if(conta != null) {
   			conta.setSaldo(conta.getSaldo()+valor);
   			conta = TipoCliente.verificaTipoConta(conta);
   			contaService.setConta(conta);
   			redirAttrs.addFlashAttribute("ok", "Depósito de R$"+valor+",00 reais realizada com sucesso");
   			return "redirect:/arealogado/"+conta.getId();
   			
   		}else {
   			redirAttrs.addFlashAttribute("erro", "Erro no deposito!");
   			return "redirect:/arealogado/"+conta.getId();
   		}
   	}
	
    @PostMapping("tranfereentrecontas")
    public String transfere(Model model,int valor,int destino, RedirectAttributes redirAttrs) {
    	System.err.println("TRANSFERINDO: "+valor+" PARA: "+destino);
   		Conta conta = contaService.getConta(Const.ID_CONTA_LOGADA);
   		Conta contaDestino = contaService.getConta(destino);
   		if(conta != null && contaDestino != null) {
   			if(conta.getSaldo() >= valor) {
   				conta.setSaldo(conta.getSaldo()-valor);
   				contaDestino.setSaldo(contaDestino.getSaldo()+valor);
   	   			conta = TipoCliente.verificaTipoConta(conta);
   	   			contaService.setAll(Arrays.asList(conta,contaDestino));
   	   			redirAttrs.addFlashAttribute("ok", "Transferencia de R$"+valor+",00 reais para "+contaDestino.getCliente().getNome()+" realizada com sucesso!");
   	   			return "redirect:/arealogado/"+conta.getId();
   			}else {
   				redirAttrs.addFlashAttribute("erro", "Você não possui saldo para esta transferencia!");
	   			return "redirect:/arealogado/"+conta.getId();
   			}
   			
   		}else {
   			redirAttrs.addFlashAttribute("erro", "Não existe este numero cadastrado!");
   			return "redirect:/arealogado/"+conta.getId();
   		}
    }
    
    
}