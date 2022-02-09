package br.projetoparticularnext.com.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.model.pix.Pix;
import br.projetoparticularnext.com.model.pix.TipoChavePix;
import br.projetoparticularnext.com.repository.PixRepository;
import br.projetoparticularnext.com.utils.Utils;

@Service
public class PixService {

	@Autowired
	PixRepository pixRepository;

	public Pix criaPix(Pix pix) {
		return pixRepository.save(pix);
	}

	public Optional<Pix> findByChave(String chaveDestino) {
		return pixRepository.findByConteudoChave(chaveDestino);
	}

	public Pix getPixById(long id) {

		return pixRepository.getById(id);
	}

	public void delete(Pix pix) {
		pixRepository.delete(pix);
	}

	public void verificaPix(ModelAndView modelAndView, Conta conta, String caminho) {

		if (conta.getListPix().size() > 0) {
			System.err.println("C PIX");
			modelAndView.addObject("consultar", caminho + "consultar");
			modelAndView.addObject("transferir", caminho + "transferir");
			if (conta.getListPix().size() < 4) {
				modelAndView.addObject("cadastrar", caminho + "cadastrar");
			}
		} else {
			System.err.println("N CART");
			modelAndView.addObject("cadastrar", caminho + "cadastrar");
		}
	}

	public Pix criaPix(String tipochave, Conta conta) {
		Pix pix = new Pix();

		switch (tipochave) {
		case "0": {// cpf
			pix.ativarChave(TipoChavePix.CPF, conta.getCliente().getCpf(), true);
			return pix;
		}
		case "1": {// email
			pix.ativarChave(TipoChavePix.Email, conta.getCliente().getEmail(), true);
			return pix;
		}
		case "2": {// telefone
			pix.ativarChave(TipoChavePix.Telefone, conta.getCliente().getTelefone(), true);
			return pix;
		}
		case "3": {// chave aleatoria
			pix.ativarChave(TipoChavePix.Aleatorio, Utils.gerarAleatorio(), true);
			return pix;
		}
		default:
			return null;
		}
	}

	public void transferir(int valor, Conta conta, Conta contaDestino) {
		contaDestino.setSaldo(contaDestino.getSaldo() + valor);
		conta.setSaldo(conta.getSaldo() - valor);
	}

	public boolean verificaPixExistente(List<Pix> listPix, Pix novoPix) {
		for (Pix pix : listPix) {
			if (pix.tipoChave == novoPix.tipoChave) {
				return false;
			}
		}
		return true;
	}
}
