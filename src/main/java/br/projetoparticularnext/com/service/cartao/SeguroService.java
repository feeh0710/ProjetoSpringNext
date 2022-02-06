package br.projetoparticularnext.com.service.cartao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.projetoparticularnext.com.model.cartao.Seguro;
import br.projetoparticularnext.com.model.cartao.TipoSeguro;
import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.repository.cartao.SeguroRepository;
import br.projetoparticularnext.com.utils.Utils;

@Service
public class SeguroService {
	@Autowired
	SeguroRepository seguroRepository;

	public Seguro criaSeguro(Seguro seguro) {
		return seguroRepository.save(seguro);
	}
	/*
	 * <a th:href="${contratar}" th:if="${contratar}"> Contratar</a>
					 
				<a th:href="${acionar}" th:if="${acionar}"> Acionar </a> 
				
				<a th:href="${visualizar}" th:if="${visualizar}"> Visualizar</a> 
				
				<a th:href="${cancelar}" th:if="${cancelar}"> Cancelar </a> 
	 */
	public void verificaSeguro(ModelAndView modelAndView, Conta conta,String caminho) {
		
		if (conta.getCredito().getApolice() != null) {
			System.err.println("C CART");
			modelAndView.addObject("visualizar", caminho+"visualizar");
			modelAndView.addObject("cancelar", caminho+"cancelar");
			if (new Date().after(Utils.readConsoleData(conta.getCredito().getApolice().getDataCarencia()))) {
				modelAndView.addObject("acionar", caminho+"acionar");
			} else {
				modelAndView.addObject("carencia", "Periodo carencia: "+conta.getCredito().getApolice().getDataCarencia());
			}
		} else {
			System.err.println("N CART");
			modelAndView.addObject("contratar", caminho+"contratar");
		}
	}
	
	public TipoSeguro getTipo(String position) {
		if(position.equals("0")) {
			return TipoSeguro.MORTE;
		}else if (position.equals("1")) {
			return TipoSeguro.DESEMPREGO;
		}else {
			return TipoSeguro.INVALIDEZ;
		}
	}
}
