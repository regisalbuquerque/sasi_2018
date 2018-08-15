package ml.ac.model;

import java.util.ArrayList;
import java.util.List;

import ml.ac.util.CSVUtil;


public class ResultadoExperimento {
	
	private String nomeExperimento;
	private Double tempo;
	private Double acuracia;
	private Double acuraciaPrequencial;
	private int numInstancias;
	
	private int numAcertos;
	
	private List<ResultadoIteracao> listaResultadoIteracoes;

	public ResultadoExperimento(String nomeExperimento) {
		this.nomeExperimento = nomeExperimento;
		this.listaResultadoIteracoes = new ArrayList<>();
		
		this.numAcertos = 0;
		this.numInstancias = 0;
		this.acuraciaPrequencial = 0.0;
	}
	
	public void adicionarIteracao(boolean acertou)
	{
		this.numInstancias++;
		double predicao = 0.0; //Se errou
		if (acertou) 
		{
			this.numAcertos++;
			predicao = 1.0;
		}
		
		double acuraciaAtual = 100.0 * (double) this.numAcertos/ (double) this.numInstancias;
		double acuracia_prequencial_atual = this.acuraciaPrequencial + ( (predicao - this.acuraciaPrequencial) / this.numInstancias );
		this.acuraciaPrequencial = acuracia_prequencial_atual;
		
		ResultadoIteracao resultadoIteracao = new ResultadoIteracao(this.numInstancias, acertou, acuraciaAtual, acuracia_prequencial_atual);
		listaResultadoIteracoes.add(resultadoIteracao);
	}
	
	public void imprimeResultado()
	{
		System.out.println(this.nomeExperimento);
		System.out.println(this.getNumInstancias() + " instances processed with " + this.getAcuracia() + "% accuracy in "+this.getTempo()+" seconds.");
		for (ResultadoIteracao resultadoIteracao : listaResultadoIteracoes) {
			//System.out.println("ITERACAO " + resultadoIteracao.getIteracao() + " ACURÁCIA " + resultadoIteracao.getAcuracia() + " PREQUENCIAL " + resultadoIteracao.getAcuraciaPrequencial());
		}
	}
	
	public void gravaResultadoCSV(String path)
	{
		//Gravar o CSV
        CSVUtil csv = new CSVUtil(path, nomeExperimento + ".csv");
        
        csv.cabecalho("iteracao,acc_prequencial,acuracia");
        
        
        for (ResultadoIteracao resultadoIteracao : listaResultadoIteracoes) {
        	csv.registro(resultadoIteracao.getIteracao() + ","
        			+ resultadoIteracao.getAcuraciaPrequencial() + "," + resultadoIteracao.getAcuracia());
		}

        csv.fechar();
	}
	
	public void gravaResultado30PartesCSV(String path)
	{
		//Gravar o CSV
        CSVUtil csv = new CSVUtil(path, nomeExperimento + ".csv");
        
        csv.cabecalho("iteracao,acc_prequencial");
        
        int TAM = 30;
        int particao =  this.getNumInstancias()/TAM;		  
        
        for (int i = 1; i<TAM+1; i++) {
        	
        	int index = i * particao;

        	ResultadoIteracao resultadoIteracao = listaResultadoIteracoes.get(index);
        	csv.registro(resultadoIteracao.getIteracao() + ","
        			+ resultadoIteracao.getAcuraciaPrequencial());
		}

        csv.fechar();
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
