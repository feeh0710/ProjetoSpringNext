package br.projetoparticularnext.com.model.cartao;

import br.projetoparticularnext.com.utils.Const;

	public enum TipoSeguro {
		MORTE(Const.NUM_REGRA_MORTE,Const.VALOR_SEGURO_ANUAL_MORTE,Const.TAXA_SEGURO_MORTE),
		DESEMPREGO(Const.NUM_REGRA_DESEMPREGO,Const.VALOR_SEGURO_ANUAL_DESEMPREGO,Const.TAXA_SEGURO_DESEMPREGO),
		INVALIDEZ(Const.NUM_REGRA_INVALIDEZ,Const.VALOR_SEGURO_ANUAL_INVALIDEZ,Const.TAXA_SEGURO_INVALIDEZ);
	
		private int regra;
		private double valorSeguro;
		private double taxa;
		private String[] regras;
		
		TipoSeguro(int regra, double valorSeguro, double taxa) {
			this.regra = regra;
			this.valorSeguro = valorSeguro;
			this.taxa = taxa;
		}
		
		public int getRegra() {
			return this.regra;
		}
		
		public double getValorSeguro() {
			return this.valorSeguro;
		}
	
		double getTaxa() {
			return this.taxa;
		}
		
		public String[] getRegras(int position) {
			if(position == 0) {
				return Const.REGRA_MORTE;
			}else if (position == 1) {
				return Const.REGRA_DESEMPREGO;
			}else {
				return Const.REGRA_INVALIDEZ;
			}
		}
		
	}
