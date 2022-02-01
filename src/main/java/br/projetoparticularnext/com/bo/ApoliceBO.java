package br.projetoparticularnext.com.bo;

import br.projetoparticularnext.com.model.cartao.Apolice;
import br.projetoparticularnext.com.model.cartao.Seguro;
import br.projetoparticularnext.com.model.cartao.TipoSeguro;
import br.projetoparticularnext.com.utils.Banco;

public class ApoliceBO {
	public static boolean CriaApolice(Apolice apolice) {
		return Banco.cadastraApolice(apolice);
	}
	public static Apolice CriaApoliceProvisoria(String buscaOpcaoSeguro,int ano) {
		Apolice apolice = new Apolice(new Seguro(buscaOpcaoSeguro(buscaOpcaoSeguro)),ano);
		return apolice;
	}

	public static TipoSeguro buscaOpcaoSeguro(String op) {
		if(op.equals("1")) {
			return TipoSeguro.MORTE;
		}else if(op.equals("2")) {
			return TipoSeguro.INVALIDEZ;
		}else if(op.equals("3")) {
			return TipoSeguro.DESEMPREGO;
		}
		return null;
	}
	public static Apolice buscaApolice() {
		return Banco.buscarApolice();
	}
	public static void deletaApolice() {
		Banco.deletarApolice();
	}
}
