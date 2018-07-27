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
        resultadoExperimento.imprimeResultado();
        return resultadoExperimento;
	}
	
    public static void main( String[] args )
    {
        experimento1("Experimento com HoeffdingTree (Simples)", FabricaDeBases.baseLine(), FabricaDeClassificadores.classificadorSimples());
        experimento1("Experimento com HoeffdingTree (DDM)", FabricaDeBases.baseLine(), FabricaDeClassificadores.classificadorComDDM());
        experimento1("Experimento com HoeffdingTree (EDDM)", FabricaDeBases.baseLine(), FabricaDeClassificadores.classificadorComEDDM());
        experimento1("Experimento com HoeffdingTree (ADWIN)",FabricaDeBases.baseLine(), FabricaDeClassificadores.classificadorComADWIN2());
    }
}
