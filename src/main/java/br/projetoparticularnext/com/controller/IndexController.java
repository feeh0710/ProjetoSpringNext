package br.projetoparticularnext.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.projetoparticularnext.com.dto.ClienteEndereco;
import br.projetoparticularnext.com.model.Endereco;
import br.projetoparticularnext.com.model.cliente.Cliente;
import br.projetoparticularnext.com.service.ClienteService;
import br.projetoparticularnext.com.utils.Const;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired // pede uma instancia de pedido service
	ClienteService clienteService;

	@GetMapping
	public String index(Model model) {
		return "index";
	}

	@PostMapping("escolhaconta")
	public String loginCliente(@ModelAttribute("cpfLogin") String cpf, @ModelAttribute("senhaLogin") String senha,
			Model model, RedirectAttributes redirAttrs) {
		System.err.println(cpf + senha);
//		cpf = "43546219830";
//		senha = "1234";
		Cliente c = clienteService.buscaCpfSenha(cpf, senha);
		if (c != null) {
			System.err.println(c.toString());
			Const.ID_CLIENTE_LOGADO = c.getId();

			return "redirect:/escolhaconta";
		} else {
			redirAttrs.addFlashAttribute("erro", "Usuário não encontrado!");
			return "redirect:/";
		}
	}
	@PostMapping("/cadastrobasico")
	public String cadastraCliente(@ModelAttribute("clienteendereco") ClienteEndereco cliend, RedirectAttributes redirAttrs) {
		System.out.println(cliend.toString());
		Endereco endereco = new Endereco(cliend.getCidade(), cliend.getEstado(), cliend.getBairro(), cliend.getNumero(), cliend.getLogradouro(), cliend.getCep());
		Cliente cliente = new Cliente(cliend.getSenha(), cliend.getEmail(), cliend.getCpf(), cliend.getRg(), cliend.getNome(),cliend.getTelefone(), endereco);
		
		if(clienteService.validarCampos(cliend)) {
			if(cliend.getTipo().equals("3")) {
				cliente = clienteService.ciarContas(cliente);
				clienteService.createCliente(cliente);
				Const.ID_CLIENTE_LOGADO = cliente.getId();
				redirAttrs.addFlashAttribute("cadastro", "Usuário e conta corrente+poupança cadastradas com sucesso!\n realize login");
				return "redirect:/";
			}else if(cliend.getTipo().equals("2")) {
				clienteService.criaContaPoupanca(cliente);
				clienteService.createCliente(cliente);
				Const.ID_CLIENTE_LOGADO = cliente.getId();
				redirAttrs.addFlashAttribute("cadastro", "Usuário e conta poupança cadastradas com sucesso!\n realize login!");
				return "redirect:/";
			}else {
				cliente = clienteService.criaContaCorrente(cliente);
				clienteService.createCliente(cliente);
				Const.ID_CLIENTE_LOGADO = cliente.getId();
				redirAttrs.addFlashAttribute("cadastro", "Usuário e conta corrente cadastradas com sucesso!\n realize login!");
				return "redirect:/";
			}
		}else {
			redirAttrs.addFlashAttribute("erro", "Erro!\n Voce precisa preencher todos os campos antes de clicar em cadastrar!");
			return "redirect:/";
		}
	}

	

	
	
	
}
