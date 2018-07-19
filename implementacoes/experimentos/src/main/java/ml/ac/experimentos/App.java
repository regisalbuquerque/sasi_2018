package ml.ac.experimentos;

import com.yahoo.labs.samoa.instances.Instance;

import moa.classifiers.Classifier;
import moa.classifiers.drift.DriftDetectionMethodClassifier;
import moa.classifiers.trees.HoeffdingTree;
import moa.core.TimingUtils;
import moa.options.AbstractOptionHandler;
import moa.streams.ArffFileStream;
import moa.streams.InstanceStream;

public class App 
{
	public static void experimento1(String base, Classifier classificador)
	{
		boolean isTesting = true;
		
		Classifier learner = classificador;
		
		AbstractOptionHandler stream = new ArffFileStream(base, -1);
		stream.prepareForUse();
		InstanceStream streamInstances = (InstanceStream)stream;
		
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
    	String baseLine = "src/main/resources/bases/line.arff";
    	
    	System.out.println("********* BASE LINE **************************");
    	System.out.println("Experimento com HoeffdingTree (Simples)");
    	HoeffdingTree classificador1 = new HoeffdingTree();
        experimento1(baseLine, classificador1);
        
        System.out.println("Experimento com HoeffdingTree (DDM)");
        DriftDetectionMethodClassifier classificador2 = new DriftDetectionMethodClassifier();
        classificador2.baseLearnerOption.setValueViaCLIString("trees.HoeffdingTree");
        classificador2.driftDetectionMethodOption.setValueViaCLIString("DDM");
        experimento1(baseLine, classificador2);
        
        System.out.println("Experimento com HoeffdingTree (EDDM)");
        DriftDetectionMethodClassifier classificador3 = new DriftDetectionMethodClassifier();
        classificador3.baseLearnerOption.setValueViaCLIString("trees.HoeffdingTree");
        classificador3.driftDetectionMethodOption.setValueViaCLIString("EDDM");
        experimento1(baseLine, classificador3);
        
//        System.out.println("Experimento com HoeffdingTree (ADMIN)");
//        DriftDetectionMethodClassifier classificador4 = new DriftDetectionMethodClassifier();
//        classificador4.baseLearnerOption.setValueViaCLIString("trees.HoeffdingTree");
//        classificador4.driftDetectionMethodOption.setValueViaCLIString("ADWINChangeDetector");
//        experimento1(baseLine, classificador4);
    }
}
