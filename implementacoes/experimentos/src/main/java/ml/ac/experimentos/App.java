package ml.ac.experimentos;

import com.yahoo.labs.samoa.instances.Instance;

import ml.ac.bases.FabricaDeBases;
import ml.ac.experimentos.classificadores.FabricaDeClassificadores;
import moa.classifiers.Classifier;
import moa.classifiers.drift.DriftDetectionMethodClassifier;
import moa.classifiers.trees.HoeffdingTree;
import moa.core.TimingUtils;
import moa.options.AbstractOptionHandler;
import moa.streams.ArffFileStream;
import moa.streams.InstanceStream;

public class App 
{
	public static void experimento1(InstanceStream streamInstances, Classifier classificador)
	{
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
                        }
                }
                numberSamples++;
                learner.trainOnInstance(trainInst);
        }
        double accuracy = 100.0 * (double) numberSamplesCorrect/ (double) numberSamples;
        double time = TimingUtils.nanoTimeToSeconds(TimingUtils.getNanoCPUTimeOfCurrentThread()- evaluateStartTime);
        System.out.println(numberSamples + " instances processed with " + accuracy + "% accuracy in "+time+" seconds.");
	}
	
    public static void main( String[] args )
    {
    	
    	System.out.println("********* BASE LINE **************************");
    	System.out.println("Experimento com HoeffdingTree (Simples)");
        experimento1(FabricaDeBases.baseLine(), FabricaDeClassificadores.classificadorSimples());
        
        System.out.println("Experimento com HoeffdingTree (DDM)");
        experimento1(FabricaDeBases.baseLine(), FabricaDeClassificadores.classificadorComDDM());
        
        System.out.println("Experimento com HoeffdingTree (EDDM)");
        experimento1(FabricaDeBases.baseLine(), FabricaDeClassificadores.classificadorComEDDM());
        
        System.out.println("Experimento com HoeffdingTree (ADMIN)");
        experimento1(FabricaDeBases.baseLine(), FabricaDeClassificadores.classificadorComADWIN2());
    }
}
