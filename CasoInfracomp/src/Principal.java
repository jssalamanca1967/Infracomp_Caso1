import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Principal {
	
	private static int numeroClientes;

	private static int numeroServidores;
	
	
	public static void main(String[] args) {
		try {
			BufferedReader lector = new BufferedReader(new FileReader(new File(
					"./data/data.txt")));

			String laInfo = lector.readLine();
			String[] info = laInfo.split(":");
			numeroClientes = Integer.parseInt(info[1]);

			laInfo = lector.readLine();
			info = laInfo.split(":");
			numeroServidores = Integer.parseInt(info[1]);
			
			laInfo = lector.readLine();
			info = laInfo.split(":");
			int numeroMensajes = Integer.parseInt(info[1]);

			Buffer buffer = new Buffer(numeroClientes, numeroServidores);
			
			

		} catch (FileNotFoundException e) {
			System.out.println("No se pudo acceder al archivo");
		} catch (IOException e) {

			e.printStackTrace();
		}


	}
}
