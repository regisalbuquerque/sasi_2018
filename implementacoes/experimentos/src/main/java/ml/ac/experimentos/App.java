package ml.ac.experimentos;

import com.yahoo.labs.samoa.instances.Instance;

import ml.ac.bases.FabricaDeBases;
import ml.ac.experimentos.classificadores.FabricaDeClassificadores;
import ml.ac.model.ResultadoExperimento;
import moa.classifiers.Classifier;
import moa.core.TimingUtils;
import moa.streams.InstanceStream;

public class App 
{
	public static ResultadoExperimento experimento1(String nomeExperimento, InstanceStream streamInstances, Classifier classificador)
	{
		ResultadoExperimento resultadoExperimento = new ResultadoExperimento(nomeExperimento);
		
		boolean isTesting = true;
		
		Classifier learner = classificador;
		
		learner.setModelContext(streamInstances.getHeader());
        learner.prepareForUse();

        int numberSamplesCorrect = 0;
        int numberSamples = 0;
        boolean preciseCPUTiming = TimingUtils.enablePreciseTiming();
        long evaluateStartTime = TimingUtils.getNanoCPUTimeOfCurrentThread();
        while (streamInstances.hasMoreInstances()) {
                Instance trainInst = streamInstances.nextInstance().getData();
                if (isTesting) {
                        if (learner.correctlyClassifies(trainInst)){
                                numberSamplesCorrect++;
                                resultadoExperimento.adicionarIteracao(true);
                        }
                        else
                        {
                        	resultadoExperimento.adicionarIteracao(false);
                        }
                }
                numberSamples++;
                learner.trainOnInstance(trainInst);
        }
        double accuracy = 100.0 * (double) numberSamplesCorrect/ (double) numberSamples;
        double time = TimingUtils.nanoTimeToSeconds(TimingUtils.getNanoCPUTimeOfCurrentThread()- evaluateStartTime);
        resultadoExperimento.setNumInstancias(numberSamples);
        resultadoExperimento.setTempo(time);
        resultadoExperimento.setAcuracia(accuracy);
        resultadoExperimento.gravaResultado30PartesCSV("/Users/regisalbuquerque/experimentos/");
        return resultadoExperimento;
	}
	
    public static void main( String[] args )
    {
    	String EXPERIMENTO_CLASS_SIMPLES1 = "Experimento com HoeffdingTree (Simples)";
    	String EXPERIMENTO_CLASS_DDM = "Experimento com HoeffdingTree (DDM)";
    	String EXPERIMENTO_CLASS_EDDM = "Experimento com HoeffdingTree (EDDM)";
    	String EXPERIMENTO_CLASS_ADWIN = "Experimento com HoeffdingTree (ADWIN)";
    	
        experimento1("LINE-" + EXPERIMENTO_CLASS_SIMPLES1, FabricaDeBases.baseLine(), FabricaDeClassificadores.classificadorSimples());
        experimento1("LINE-" + EXPERIMENTO_CLASS_DDM, FabricaDeBases.baseLine(), FabricaDeClassificadores.classificadorComDDM());
        experimento1("LINE-" + EXPERIMENTO_CLASS_EDDM, FabricaDeBases.baseLine(), FabricaDeClassificadores.classificadorComEDDM());
        experimento1("LINE-" + EXPERIMENTO_CLASS_ADWIN,FabricaDeBases.baseLine(), FabricaDeClassificadores.classificadorComADWIN2());
        
        
        
        experimento1("CIRCLE-" + EXPERIMENTO_CLASS_SIMPLES1, FabricaDeBases.baseCircle(), FabricaDeClassificadores.classificadorSimples());
        experimento1("CIRCLE-" + EXPERIMENTO_CLASS_DDM, FabricaDeBases.baseCircle(), FabricaDeClassificadores.classificadorComDDM());
        experimento1("CIRCLE-" + EXPERIMENTO_CLASS_EDDM, FabricaDeBases.baseCircle(), FabricaDeClassificadores.classificadorComEDDM());
        experimento1("CIRCLE-" + EXPERIMENTO_CLASS_ADWIN,FabricaDeBases.baseCircle(), FabricaDeClassificadores.classificadorComADWIN2());
        
        experimento1("GAUSS-" + EXPERIMENTO_CLASS_SIMPLES1, FabricaDeBases.baseGauss(), FabricaDeClassificadores.classificadorSimples());
        experimento1("GAUSS-" + EXPERIMENTO_CLASS_DDM, FabricaDeBases.baseGauss(), FabricaDeClassificadores.classificadorComDDM());
        experimento1("GAUSS-" + EXPERIMENTO_CLASS_EDDM, FabricaDeBases.baseGauss(), FabricaDeClassificadores.classificadorComEDDM());
        experimento1("GAUSS-" + EXPERIMENTO_CLASS_ADWIN,FabricaDeBases.baseGauss(), FabricaDeClassificadores.classificadorComADWIN2());
    
        experimento1("SINE1-" + EXPERIMENTO_CLASS_SIMPLES1, FabricaDeBases.baseSine1(), FabricaDeClassificadores.classificadorSimples());
        experimento1("SINE1-" + EXPERIMENTO_CLASS_DDM, FabricaDeBases.baseSine1(), FabricaDeClassificadores.classificadorComDDM());
        experimento1("SINE1-" + EXPERIMENTO_CLASS_EDDM, FabricaDeBases.baseSine1(), FabricaDeClassificadores.classificadorComEDDM());
        experimento1("SINE1-" + EXPERIMENTO_CLASS_ADWIN,FabricaDeBases.baseSine1(), FabricaDeClassificadores.classificadorComADWIN2());
    
        experimento1("ELEC-" + EXPERIMENTO_CLASS_SIMPLES1, FabricaDeBases.baseElec(), FabricaDeClassificadores.classificadorSimples());
        experimento1("ELEC-" + EXPERIMENTO_CLASS_DDM, FabricaDeBases.baseElec(), FabricaDeClassificadores.classificadorComDDM());
        experimento1("ELEC-" + EXPERIMENTO_CLASS_EDDM, FabricaDeBases.baseElec(), FabricaDeClassificadores.classificadorComEDDM());
        experimento1("ELEC-" + EXPERIMENTO_CLASS_ADWIN,FabricaDeBases.baseElec(), FabricaDeClassificadores.classificadorComADWIN2());
    
        experimento1("SPAM-" + EXPERIMENTO_CLASS_SIMPLES1, FabricaDeBases.baseSpam(), FabricaDeClassificadores.classificadorSimples());
        experimento1("SPAM-" + EXPERIMENTO_CLASS_DDM, FabricaDeBases.baseSpam(), FabricaDeClassificadores.classificadorComDDM());
        experimento1("SPAM-" + EXPERIMENTO_CLASS_EDDM, FabricaDeBases.baseSpam(), FabricaDeClassificadores.classificadorComEDDM());
        experimento1("SPAM-" + EXPERIMENTO_CLASS_ADWIN,FabricaDeBases.baseSpam(), FabricaDeClassificadores.classificadorComADWIN2());
        
        experimento1("KDD-" + EXPERIMENTO_CLASS_SIMPLES1, FabricaDeBases.baseKDD(), FabricaDeClassificadores.classificadorSimples());
        experimento1("KDD-" + EXPERIMENTO_CLASS_DDM, FabricaDeBases.baseKDD(), FabricaDeClassificadores.classificadorComDDM());
        experimento1("KDD-" + EXPERIMENTO_CLASS_EDDM, FabricaDeBases.baseKDD(), FabricaDeClassificadores.classificadorComEDDM());
        experimento1("KDD-" + EXPERIMENTO_CLASS_ADWIN,FabricaDeBases.baseKDD(), FabricaDeClassificadores.classificadorComADWIN2());

    	
    }
}
