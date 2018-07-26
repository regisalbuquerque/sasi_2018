package ml.ac.model;

import java.util.ArrayList;
import java.util.List;

public class ResultadoExperimento {
	
	private String nomeExperimento;
	private Double tempo;
	private Double acuracia;
	private Integer numInstancias;
	
	private List<ResultadoIteracao> listaResultadoIteracoes;

	public ResultadoExperimento(String nomeExperimento) {
		this.nomeExperimento = nomeExperimento;
		listaResultadoIteracoes = new ArrayList<>();
	}
	
	public void adicionarIteracao(ResultadoIteracao resultadoIteracao)
	{
		listaResultadoIteracoes.add(resultadoIteracao);
	}
	
	public void imprimeResultado()
	{
		System.out.println(this.nomeExperimento);
		System.out.println(this.getNumInstancias() + " instances processed with " + this.getAcuracia() + "% accuracy in "+this.getTempo()+" seconds.");
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

	public Integer getNumInstancias() {
		return numInstancias;
	}

	public void setNumInstancias(Integer numInstancias) {
		this.numInstancias = numInstancias;
	}

	public List<ResultadoIteracao> getListaResultadoIteracoes() {
		return listaResultadoIteracoes;
	}
	
	
	
}
