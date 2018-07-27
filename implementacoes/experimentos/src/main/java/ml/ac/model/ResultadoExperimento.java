package ml.ac.model;

import java.util.ArrayList;
import java.util.List;

public class ResultadoExperimento {
	
	private String nomeExperimento;
	private Double tempo;
	private Double acuracia;
	private int numInstancias;
	
	private int numAcertos;
	
	private List<ResultadoIteracao> listaResultadoIteracoes;

	public ResultadoExperimento(String nomeExperimento) {
		this.nomeExperimento = nomeExperimento;
		this.listaResultadoIteracoes = new ArrayList<>();
		
		this.numAcertos = 0;
		this.numInstancias = 0;
	}
	
	public void adicionarIteracao(boolean acertou)
	{
		this.numInstancias++;
		if (acertou) this.numAcertos++;
		
		double acuracia = 100.0 * (double) this.numAcertos/ (double) this.numInstancias;
		
		ResultadoIteracao resultadoIteracao = new ResultadoIteracao(this.numInstancias, acuracia);
		listaResultadoIteracoes.add(resultadoIteracao);
	}
	
	public void imprimeResultado()
	{
		System.out.println(this.nomeExperimento);
		System.out.println(this.getNumInstancias() + " instances processed with " + this.getAcuracia() + "% accuracy in "+this.getTempo()+" seconds.");
		for (ResultadoIteracao resultadoIteracao : listaResultadoIteracoes) {
			System.out.println("ITERACAO " + resultadoIteracao.getIteracao() + " ACURÁCIA " + resultadoIteracao.getAcuracia());
		}
	}

	public String getNomeExperimento() {
		return nomeExperimento;
	}

	public Double getTempo() {
		return tempo;
	}

	public void setTempo(Double tempo) {
		this.tempo = tempo;
	}

	public Double getAcuracia() {
		return acuracia;
	}

	public void setAcuracia(Double acuracia) {
		this.acuracia = acuracia;
	}

	public int getNumInstancias() {
		return numInstancias;
	}

	public void setNumInstancias(int numInstancias) {
		this.numInstancias = numInstancias;
	}

	public List<ResultadoIteracao> getListaResultadoIteracoes() {
		return listaResultadoIteracoes;
	}
	
	
	
}
