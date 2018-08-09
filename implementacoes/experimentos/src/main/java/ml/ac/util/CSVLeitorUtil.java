package ml.ac.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVLeitorUtil {

	private BufferedReader br = null;
	String arquivoCSV = null;
	String csvDivisor = ",";

	public CSVLeitorUtil(String path, String fileName) {
		arquivoCSV = path + fileName;

		try {

			br = new BufferedReader(new FileReader(arquivoCSV));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

	public String[] readLine() {

		try {

			String linha = br.readLine();
			if (linha == null)
				return null;

			return linha.split(csvDivisor);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public void fechar()
	{
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
