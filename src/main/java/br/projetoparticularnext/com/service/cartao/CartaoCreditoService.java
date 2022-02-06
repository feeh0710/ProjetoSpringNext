package br.projetoparticularnext.com.service.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.projetoparticularnext.com.model.cartao.CartaoCredito;
import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.repository.cartao.CartaoCreditoRepository;

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
}
