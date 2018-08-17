package ml.ac.experimentos;

import java.util.Arrays;
import java.util.List;

import ml.ac.util.CSVLeitorUtil;
import ml.ac.util.CSVUtil;

public class AgrupadorCSV2 {

	public static void main(String[] args) {
		
		String path = "/home/regis/Documents/git/regis/sasi_2018/resultados_4/";
		
		List<String> listaClassificadores = Arrays.asList("Simples", "DDM", "EDDM", "ADWIN");
		List<String> listaBases = Arrays.asList("LINE", "CIRCLE", "GAUSS", "SINE1", "ELEC", "SPAM", "KDD");
		
		int NUM_BASES = listaBases.size();
		int NUM_CLASSES = listaClassificadores.size();
		int NUM_RESULTADOS = 1;
		double[][][] acuraciaPrequencial = new double[NUM_BASES][NUM_CLASSES][NUM_RESULTADOS];
		
		
		
		for (int b = 0; b < listaBases.size(); b++) {
			for (int c = 0; c < listaClassificadores.size(); c++) {
				
				CSVLeitorUtil csvLeitor = new CSVLeitorUtil(path,
															listaBases.get(b) +"-Experimento com HoeffdingTree ("+listaClassificadores.get(c)+").csv");
				String[] registro = null;
				String[] lastRegistro = null;

				int z = 0;
				csvLeitor.readLine(); //PULAR o CABEÇALHO
		        while ( (registro = csvLeitor.readLine() ) != null) {
		        	lastRegistro = registro;
		        }
		        acuraciaPrequencial[b][c][z] = Double.parseDouble(lastRegistro[2]); //Acurácia
		        csvLeitor.fechar();
			}
		}
		
		
		//Gravar o CSV UNICO
		
		CSVUtil csvUtil = new CSVUtil(path, "resultado_unificado_acuracia_puro_ddm_eddm_adwin.csv");
		
		csvUtil.cabecalho("classificador,base,acc");
		
		for (int res = 0; res < NUM_RESULTADOS; res++)
		{
			for (int c = 0; c < NUM_CLASSES ; c++) {
				for (int b = 0; b < NUM_BASES; b++) {
					csvUtil.registro(listaClassificadores.get(c) + "," + listaBases.get(b) + "," + acuraciaPrequencial[b][c][res]);
				}
			}
		}
		
		
		
		csvUtil.fechar();
		
		
		
		
	  }
}