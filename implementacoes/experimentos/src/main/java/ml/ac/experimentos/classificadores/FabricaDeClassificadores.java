package ml.ac.experimentos.classificadores;

import moa.classifiers.Classifier;
import moa.classifiers.drift.DriftDetectionMethodClassifier;
import moa.classifiers.trees.HoeffdingTree;

public class FabricaDeClassificadores {

	public static Classifier classificadorSimples()
	{
		return new HoeffdingTree();
	}
	
	public static Classifier classificadorComDDM()
	{
		DriftDetectionMethodClassifier classificador = new DriftDetectionMethodClassifier();
        classificador.baseLearnerOption.setValueViaCLIString("trees.HoeffdingTree");
        classificador.driftDetectionMethodOption.setValueViaCLIString("DDM");
		return classificador;
	}
	
	public static Classifier classificadorComEDDM()
	{
		DriftDetectionMethodClassifier classificador = new DriftDetectionMethodClassifier();
        classificador.baseLearnerOption.setValueViaCLIString("trees.HoeffdingTree");
        classificador.driftDetectionMethodOption.setValueViaCLIString("EDDM");
		return classificador;
	}
	
	public static Classifier classificadorComADWIN()
	{
		DriftDetectionMethodClassifier classificador = new DriftDetectionMethodClassifier();
        classificador.baseLearnerOption.setValueViaCLIString("trees.HoeffdingTree");
        classificador.driftDetectionMethodOption.setValueViaCLIString("ADWINChangeDetector");
      	return classificador;
	}
	
	public static Classifier classificadorComDDM2()
	{
		ClassificadorWrapper classificador = new ClassificadorWrapper();
        classificador.baseLearnerOption.setValueViaCLIString("trees.HoeffdingTree");
        classificador.driftDetectionMethodOption.setValueViaCLIString("DDM");
		return classificador;
	}
	
	public static Classifier classificadorComEDDM2()
	{
		ClassificadorWrapper classificador = new ClassificadorWrapper();
        classificador.baseLearnerOption.setValueViaCLIString("trees.HoeffdingTree");
        classificador.driftDetectionMethodOption.setValueViaCLIString("EDDM");
		return classificador;
	}
	
	public static Classifier classificadorComADWIN2()
	{
		ClassificadorWrapper classificador = new ClassificadorWrapper();
        classificador.baseLearnerOption.setValueViaCLIString("trees.HoeffdingTree");
        classificador.driftDetectionMethodOption.setValueViaCLIString("ADWINChangeDetector");
      	return classificador;
	}
	
	
	
}
