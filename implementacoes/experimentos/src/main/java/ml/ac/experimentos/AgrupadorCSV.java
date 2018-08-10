package ml.ac.experimentos;

import java.util.Arrays;
import java.util.List;

import ml.ac.util.CSVLeitorUtil;
import ml.ac.util.CSVUtil;

public class AgrupadorCSV {

	public static void main(String[] args) {
		
		String path = "/home/regis/Documents/git/sasi_2018/resultados_2/";
		
		int NUM_BASES=7;
		int NUM_CLASSES = 4;
		int NUM_RESULTADOS = 30;
		double[][][] acuraciaPrequencial = new double[NUM_BASES][NUM_CLASSES][NUM_RESULTADOS];
		
		List<String> listaClassificadores = Arrays.asList("Simples", "DDM", "EDDM", "ADWIN");
		List<String> listaBases = Arrays.asList("LINE", "CIRCLE", "GAUSS", "SINE1", "ELEC", "SPAM", "KDD");
		
		for (int b = 0; b < listaBases.size(); b++) {
			for (int c = 0; c < listaClassificadores.size(); c++) {
				
				CSVLeitorUtil csvLeitor = new CSVLeitorUtil(path,
															listaBases.get(b) +"-Experimento com HoeffdingTree ("+listaClassificadores.get(c)+").csv");
				String[] registro = null;

				int z = 0;
				csvLeitor.readLine(); //PULAR o CABEÇALHO
		        while ( (registro = csvLeitor.readLine() ) != null) {
		            acuraciaPrequencial[b][c][z++] = Double.parseDouble(registro[1]);

		        }
		        csvLeitor.fechar();
			}
		}
		
		
		//Gravar o CSV UNICO
		
		CSVUtil csvUtil = new CSVUtil(path, "resultado_unificado.csv");
		
		csvUtil.cabecalho("classificador,base,acc");
		
		for (int res = 0; res < NUM_RESULTADOS; res++)
		{
			for (int c = 0; c < NUM_CLASSES ; c++) {
				for (int b = 0; b < NUM_BASES; b++) {
					csvUtil.registro(c + "," + b + "," + acuraciaPrequencial[b][c][res]);
				}
			}
		}
		
		
		
		csvUtil.fechar();
		
		
		
		
	  }
}