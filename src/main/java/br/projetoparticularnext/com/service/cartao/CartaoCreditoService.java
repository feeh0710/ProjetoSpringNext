package br.projetoparticularnext.com.service.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.projetoparticularnext.com.model.cartao.CartaoCredito;
import br.projetoparticularnext.com.model.cartao.Compra;
import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.repository.cartao.CartaoCreditoRepository;
import br.projetoparticularnext.com.utils.Utils;

@Service
public class CartaoCreditoService {

	@Autowired
	CartaoCreditoRepository cartaoCreditoRepository;

	public CartaoCredito criaCredito(CartaoCredito cartaoCredito) {
		return cartaoCreditoRepository.save(cartaoCredito);
	}

	public void delete(Long id) {
		cartaoCreditoRepository.deleteById(id);
	}

	public void verificaCartao(ModelAndView modelAndView, Conta conta) {
		if (conta.getCredito() != null) {
			modelAndView.addObject("limite", conta.getCredito().getLimite());
			modelAndView.addObject("fatura", conta.getCredito().getValorFatura());
			System.err.println("C CART");
			modelAndView.addObject("cadastrado", "cadastrado");
			if (conta.getCredito().isAtivo()) {
				modelAndView.addObject("ativado", "Bloquear");
				modelAndView.addObject("desbloqueado", "");
			} else {
				modelAndView.addObject("ativado", "Desbloquear");

			}
		} else {
			System.err.println("N CART");
			modelAndView.addObject("ncadastrado", "Criar Cartão");
		}
	}
	
	public CartaoCredito criar(String senha, String bandeira, Conta conta) {
		System.err.println("ENTROU NO CADASTRA");
		CartaoCredito credito = new CartaoCredito();
		credito.setSenha(senha);
		credito.setBandeira(bandeira);
		credito.setAtivo(true);
		credito.setNumero(Utils.geraBlocosNumeros(4));
		credito.setLimite(conta.getCliente().getTipo().getLimite());
		credito.setDataVencimento(Utils.getDateAdd1Month());
		conta.setCredito(credito);
		//credito.setConta(conta);
		return credito;
	}
	
	public Conta comprar(Compra compra, Conta conta) {
		conta.getCredito().setLimite(conta.getCredito().getLimite()-compra.getValor());
		conta.getCredito().setValorFatura(conta.getCredito().getValorFatura() + compra.getValor());
		compra.setDataCompra(Utils.dataAtual());
		compra.setCartao(conta.getCredito());
		conta.getCredito().getCompras().add(compra);
		return conta;
	}
	
	public Conta pagarFatura(Conta conta) {
		conta.setSaldo(conta.getSaldo() - conta.getCredito().getValorFatura());
		conta.getCredito().setLimite(conta.getCredito().getLimite() + conta.getCredito().getValorFatura());
		conta.getCredito().setValorFatura(0.0);
		return conta;
	}
}
