package br.projetoparticularnext.com.utils;

import java.util.ArrayList;

import br.projetoparticularnext.com.bo.ContaBO;
import br.projetoparticularnext.com.model.cartao.Apolice;
import br.projetoparticularnext.com.model.conta.Conta;

public class Menus {
	private static Utils utils = new Utils();
	static ArrayList<String> listText = new ArrayList<String>();

	// EXIBE MENU COM AS OP��ES DE PIX
	public static void exibeMenuPix() {
		String[] textos = new String[7];
		UtilFormatConsole.writeConsole("    ----------  MENU PIX   ----------");
		textos[0] = "  ";
		textos[1] = "1 - CADASTRAR CHAVE PIX          ";
		textos[2] = "2 - VISUALIZAR CHAVES PIX        ";
		textos[3] = "3 - TRANSFERIR VIA PIX           ";
		textos[4] = "4 - DELETAR CHAVE PIX            ";
		textos[5] = "0 - VOLTAR AO MENU ANTERIOR      ";
		textos[6] = "  ";

		UtilFormatConsole.writeConsoleArray(textos, textos.length);
	}

	public static void exibeTiposChavesPix() {
		String[] textos = new String[6];
		UtilFormatConsole.writeConsole("    ----------  TIPO PIX   ----------");
		textos[0] = "  ";
		textos[1] = "0 - CPF          ";
		textos[2] = "1 - EMAIL        ";
		textos[3] = "2 - TELEFONE     ";
		textos[4] = "3 - ALEATORIO    ";
		textos[5] = "  ";
		UtilFormatConsole.writeConsoleArray(textos, textos.length);
	}

	// EXIBE SAIR PARA OUTRO MENU
	public static boolean exibeOpcaoConta(String titulo) {
		if (ContaBO.id == 3) {
			String[] textos = new String[3];
			textos[0] = "  ";
			textos[1] = " ----------  " + titulo + "   ----------";
			textos[2] = "      0 - CORRENTE | 1 - POUPANCA       ";
			UtilFormatConsole.writeConsoleArray(textos, textos.length);
			String resposta = utils.lerConsole("DIGITE A OPÇÃO: ");
			if (resposta.equals("0")) {
				return false;
			} else {
				return true;
			}
		} else if (ContaBO.id == 2) {
			return true;
		} else if (ContaBO.id == 1) {
			return false;
		}
		return true;

	}

	public static void exibeOpcoesConta() {
		UtilFormatConsole.writeConsole(" ----------  MENU PRINCIPAL   ----------");
		String[] textos = new String[6];
		textos[0] = "  ";
		textos[1] = " 1 - TRANSFERIR        2 - DEPOSITO ";
		textos[2] = " 3 - CONSULTAR SALDO   4 - SACAR    ";
		textos[3] = " 5 - CRÉDITO           6 - DÉBITO   ";
		textos[4] = " 7 - PIX               0 - SAIR     ";
		textos[5] = "  ";
		UtilFormatConsole.writeConsoleArray(textos, textos.length);
	}

	// EXIBE OP�OES DE CART�ES
	public static void exibeOpcoesCartaoCredito(boolean cadastrado, boolean ativado) {
		listText.clear();
		UtilFormatConsole.writeConsole(" --------  CARTÃO DE CRÉDITO  --------");
		listText.add("");
		if (!cadastrado)
			listText.add(" * - CRIAR CARTÃO DE CRÉDITO");
		if (cadastrado)
			listText.add(" 1 - COMPRAR                ");
		if (cadastrado)
			listText.add(" 2 - CONSULTAR FATURA       ");
		if (cadastrado)
			listText.add(" 3 - ALTERAR VENCIMENTO     ");
		if (cadastrado)
			listText.add(" 4 - PAGAMENTO DE FATURA    ");
		if (cadastrado && ativado)
			listText.add(" 5 - BLOQUEAR CARTAO        ");
		if (cadastrado && !ativado)
			listText.add(" 5 - DESBLOQUEAR CARTAO     ");
		if (cadastrado && ativado)
			listText.add(" 6 - SEGURO    ");
		listText.add(" 0 - VOLTAR MENU ANTERIOR   ");
		listText.add("");
		UtilFormatConsole.writeConsole(listText, listText.size());
	}

	// EXIBE OPÇOES DE CARTÕES
	public static void exibeOpcoesCartaoDebito(boolean cadastrado, boolean ativado) {
		listText.clear();
		UtilFormatConsole.writeConsole(" --------  CARTÃO DE DÉBITO  --------");
		listText.add("");
		if (!cadastrado)
			listText.add(" * - CRIAR CARTÃO DE DÉBITO   ");
		if (cadastrado)
			listText.add(" 1 - COMPRAR                   ");
		if (cadastrado)
			listText.add(" 2 - CONSULTAR EXTRATO         ");
		if (cadastrado)
			listText.add(" 3 - ALTERAR LIMITE P/TRANSACAO");
		if (cadastrado && ativado)
			listText.add(" 4 - BLOQUEAR CARTAO        ");
		if (cadastrado && !ativado)
			listText.add(" 4 - DESBLOQUEAR CARTAO     ");
		listText.add(" 0 - VOLTAR MENU ANTERIOR   ");
		listText.add("");
		UtilFormatConsole.writeConsole(listText, listText.size());
	}

	public static void exibeBandeirasCartoes() {
		listText.clear();
		UtilFormatConsole.writeConsole(" --------  ESCOLHA A BANDEIRA  --------");
		listText.add("");
		listText.add(" 1 - VISA");
		listText.add(" 2 - MASTERCARD");
		listText.add(" 3 - ELO");
		listText.add("");
		UtilFormatConsole.writeConsole(listText, listText.size());
	}

	public static void exibeDatasVencimento() {
		listText.clear();
		UtilFormatConsole.writeConsole(" ---------------  DATA   ---------------");
		listText.add("");
		listText.add(" 1 - DIA 1    2 - DIA 5");
		listText.add(" 3 - DIA 10   4 - DIA 15");
		listText.add(" 0 - CANCELAR");
		listText.add("");
		UtilFormatConsole.writeConsole(listText, listText.size());
	}

	public static void exibeLimites() {
		listText.clear();
		UtilFormatConsole.writeConsole(" -----------  LIMITES P/TRANSACAO   -----------");
		listText.add("");
		listText.add(" 1 - R$100,00    2 - R$500,00");
		listText.add(" 3 - R$1000,00   4 - R$5000,00");
		listText.add(" 5 - R$10000,00");
		listText.add(" ");
		UtilFormatConsole.writeConsole(listText, listText.size());
	}

	public static void dadosCartoes(String numero, String validade, double fatura, double limite, String descrLimite,
			boolean status) {
		listText.clear();
		String tipo = "";
		if (status)
			tipo = "desbloqueado";
		else
			tipo = "bloqueado";
		listText.add(" --------  DADOS DO CARTAO   -----------");
		listText.add(" Limite " + descrLimite + ":" + Utils.convertToReais(limite) + "         ");
		listText.add(" numero:" + numero.toUpperCase() + "         ");
		if (!validade.equals(""))
			listText.add(" vencimento:" + validade.toUpperCase() + "       ");
		if (!validade.equals(""))
			listText.add(" fatura:" + Utils.convertToReais(fatura) + "     status:" + tipo);
		listText.add("");
		UtilFormatConsole.writeConsole(listText, listText.size());
	}

	public static void exibeMenuOpcoesSeguro() {
		listText.clear();
		listText.add("");
		UtilFormatConsole.writeConsole(" --------  AQUISIÇÃO DE SEGURO  -------- ");
		listText.add(" -----  ESCOLHA A OPÇÃO DE SEGURO  ---- ");
		listText.add("");
		listText.add(" 1 - SEGURO DE MORTE");
		listText.add(" 2 - SEGURO DE INVALIDEZ ");
		listText.add(" 3 - SEGURO DE DESEMPREGO ");
		UtilFormatConsole.writeConsole(listText, listText.size());
	}

	public static void exibeMenuCompra() {
		UtilFormatConsole.writeConsole("     -------  NOVA COMPRA  -------");
	}

	public static void exibeOpcoesApolice(boolean cadastrado) {
		listText.clear();
		UtilFormatConsole.writeConsole(" -------  APÓLICE DO SEGURO  ----------");
		listText.add("");
		if (!cadastrado)
			listText.add(" * - CONTRATAR SEGURO   ");
		if (cadastrado)
			listText.add(" 1 - VISUALIZAR APÓLICE DO SEGURO                ");
		if (cadastrado)
			listText.add(" 2 - CANCELAR APÓLICE DO SEGURO                  ");
		if (cadastrado)
			listText.add(" 3 - ACIONAR APÓLICE DO SEGURO                   ");
		listText.add(" 0 - VOLTAR MENU ANTERIOR   ");
		listText.add("");
		UtilFormatConsole.writeConsole(listText, listText.size());
	}

	public static void exibeApolice(Apolice apolice, Conta conta) {
		listText.clear();
		listText.add(" --------  CONTRATO DO SEGURO   --------");
		listText.add(" -------  DADOS DO CONTRATANTE  --------");
		listText.add("-Nome do segurado: " + conta.getCliente().getNome());
		listText.add("-CPF do segurado: " + conta.getCliente().getCpf());
		listText.add("-Data da Contratação: " + apolice.getDataAssinatura());
		listText.add("-Data Limite Carencia: " + apolice.getDataCarencia());
		listText.add("-Validade desta Apólice: " + apolice.getDataValidade());
		listText.add("-Tipo de Seguro contratado: " + apolice.getSeguro().getTipoSeguro().name());
		listText.add("-Taxa da Apolice: " + Utils.convertToReais(apolice.getSeguro().getTaxa()) + "/ano");
		listText.add("       --------  REGRAS   --------");
		for (int x = 0; x < apolice.getSeguro().getRegras().length; x++) {
			listText.add(apolice.getSeguro().getRegras()[x]);
		}
		listText.add("");
		listText.add("       --------  CONDIÇÃO   --------");
		listText.add("Eu " + conta.getCliente().getNome() + " aceito todas as    condições propostas "
				+ "neste contrato estipulado na presente data.");
		listText.add("Todos os nossos seguros garantem recuperação de 100% do valor investido pelo    " + "segurado.");
		listText.add("     --------  ACIONAR SEGURO   --------");
		listText.add("Valor do seguro: " + Utils.convertToReais(apolice.getSeguro().getValor()));
		listText.add("Taxa anual do seguro: " + Utils.convertToReais(apolice.getSeguro().getTaxa()));
		listText.add("Vigencia da solicitação apartir de " + apolice.getDataCarencia());
		listText.add("Valor que será debitado no ato da contratação com base em " + apolice.getAnos()
				+ " anos de contrato: " + Utils.convertToReais((apolice.getAnos() * apolice.getSeguro().getTaxa())));

		listText.add("Cesário Lange 30 de Fevereiro de 1850          Empresa Fulano de tal LTDA");
		UtilFormatConsole.writeConsole(listText, listText.size());
	}

	public static void exibeDetalhesApolice(Apolice apolice) {
		listText.clear();
		listText.add("       --- DETALHES DA APÓLICE --- ");
		listText.add("-Data da Contratação: " + apolice.getDataAssinatura());
		listText.add("-Tipo de Seguro: " + apolice.getSeguro().getNome());
		listText.add("-Carencia do seguro: " + apolice.getDataCarencia());
		listText.add("-Taxa anual apólice: " + Utils.convertToReais(apolice.getSeguro().getTaxa()));
		listText.add("-Validade do contrato: " + apolice.getDataValidade());
		listText.add("-Valor total apólice: " + Utils.convertToReais(apolice.getAnos() * apolice.getSeguro().getTaxa()));
		listText.add("");
		UtilFormatConsole.writeConsole(listText, listText.size());
	}

	public static void exibeMenuOpcoesConfirmacao(String positivo, String negativo, String titulo,String subtitulo) {
		listText.clear();
		listText.add("       --- " + titulo + " --- ");
		if(!subtitulo.isEmpty())listText.add(subtitulo);
		listText.add("       " + positivo + " " + negativo);
		listText.add("");
		UtilFormatConsole.writeConsole(listText, listText.size());
	}
}