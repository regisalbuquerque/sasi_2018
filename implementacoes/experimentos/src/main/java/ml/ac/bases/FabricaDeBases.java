package ml.ac.bases;

import moa.options.AbstractOptionHandler;
import moa.streams.ArffFileStream;
import moa.streams.InstanceStream;

public class FabricaDeBases {
	
	
	public static InstanceStream baseLine()
	{
		AbstractOptionHandler stream = new ArffFileStream("src/main/resources/bases/line.arff", -1);
		stream.prepareForUse();
		InstanceStream streamInstances = (InstanceStream)stream;
		return streamInstances;
	}
	
	public static InstanceStream baseCircle()
	{
		AbstractOptionHandler stream = new ArffFileStream("src/main/resources/bases/circle.arff", -1);
		stream.prepareForUse();
		InstanceStream streamInstances = (InstanceStream)stream;
		return streamInstances;
	}
	
	public static InstanceStream baseGauss()
	{
		AbstractOptionHandler stream = new ArffFileStream("src/main/resources/bases/gauss.arff", -1);
		stream.prepareForUse();
		InstanceStream streamInstances = (InstanceStream)stream;
		return streamInstances;
	}
	
	public static InstanceStream baseSine1()
	{
		AbstractOptionHandler stream = new ArffFileStream("src/main/resources/bases/sine1.arff", -1);
		stream.prepareForUse();
		InstanceStream streamInstances = (InstanceStream)stream;
		return streamInstances;
	}
	
	public static InstanceStream baseElec()
	{
		AbstractOptionHandler stream = new ArffFileStream("src/main/resources/bases/elec.arff", -1);
		stream.prepareForUse();
		InstanceStream streamInstances = (InstanceStream)stream;
		return streamInstances;
	}
	
	public static InstanceStream baseSpam()
	{
		AbstractOptionHandler stream = new ArffFileStream("src/main/resources/bases/spam.arff", -1);
		stream.prepareForUse();
		InstanceStream streamInstances = (InstanceStream)stream;
		return streamInstances;
	}
	
	public static InstanceStream baseKDD()
	{
		AbstractOptionHandler stream = new ArffFileStream("src/main/resources/bases/KDDCup99_10.arff", -1);
		stream.prepareForUse();
		InstanceStream streamInstances = (InstanceStream)stream;
		return streamInstances;
	}
}
