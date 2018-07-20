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
}
