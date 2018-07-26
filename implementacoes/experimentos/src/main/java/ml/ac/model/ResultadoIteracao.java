package ml.ac.model;

public class ResultadoIteracao {
	
	private int iteracao;
	private Double acuracia;
	
	public ResultadoIteracao(int iteracao, Double acuracia) {
		this.iteracao = iteracao;
		this.acuracia = acuracia;
	}
	
	public int getIteracao() {
		return iteracao;
	}
	
	public Double getAcuracia() {
		return acuracia;
	}
	
	
}
