package ml.ac.model;

public class ResultadoIteracao {
	
	private int iteracao;
	private Boolean acertou;
	private Double acuracia;
	private Double acuraciaPrequencial;
	

	public ResultadoIteracao(int iteracao, Boolean acertou, Double acuracia, Double acuraciaPrequencial) {
		this.iteracao = iteracao;
		this.acertou = acertou;
		this.acuracia = acuracia;
		this.acuraciaPrequencial = acuraciaPrequencial;
	}

	public int getIteracao() {
		return iteracao;
	}
	
	public Double getAcuracia() {
		return acuracia;
	}

	public Boolean getAcertou() {
		return acertou;
	}

	public Double getAcuraciaPrequencial() {
		return acuraciaPrequencial;
	}
	
	
	
}
