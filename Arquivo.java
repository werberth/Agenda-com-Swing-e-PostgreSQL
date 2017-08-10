// imports para manipulação de arquivos
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Arquivo {
	public String lerArquivo(){
		String line = " ";
		try {

			String path = new File(".").getCanonicalPath();
			FileInputStream file = new FileInputStream(path + "/arquivo.txt");
			InputStreamReader input = new InputStreamReader(file);
			BufferedReader br = new BufferedReader(input);
			line = br.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return line;
	}

	public void escreverArquivo(String nome){
		try {
			FileOutputStream arquivo = new FileOutputStream("arquivo.txt");
			PrintWriter pr = new PrintWriter(arquivo);
			pr.println(nome);
			pr.close();
			arquivo.close();
		} catch(Exception e){
			System.out.println("Erro ao escrever");
		}
	}
}