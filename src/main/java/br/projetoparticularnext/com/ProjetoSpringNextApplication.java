package br.projetoparticularnext.com;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.projetoparticularnext.com.model.Endereco;
import br.projetoparticularnext.com.model.cartao.CartaoCredito;
import br.projetoparticularnext.com.model.cliente.Cliente;
import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.model.conta.TipoConta;
import br.projetoparticularnext.com.service.ClienteService;
import br.projetoparticularnext.com.service.ContaService;

@SpringBootApplication
public class ProjetoSpringNextApplication implements CommandLineRunner {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ContaService contaService;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringNextApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cliente cliente = new Cliente("1234", "ze@ze.com", "435.462.198-30", "447215450", "João da Silva",
				new Endereco("Cesario longe", "SP", "Centro", "2220", "Rua do zé", "182850000"));
		
		Conta conta = new Conta(cliente,TipoConta.CONTACORRENTE);
		conta.setSaldo(1000);
		CartaoCredito cartaoCredito = new CartaoCredito("elo", "1234", true, 3000, "05/03/2022");
		
		conta.setCredito(cartaoCredito);
		cliente.setListConta(Arrays.asList(conta));
		
		clienteService.createCliente(cliente);
//		
//		Conta contaByClienteId = contaService.getContaByClienteId(0);
//		System.out.println(contaByClienteId.getTipoConta());
//		System.out.println(contaByClienteId.getCliente().getId());
	}

}
