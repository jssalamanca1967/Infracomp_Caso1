import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Buffer {
	
	private static int numeroClientes;
	
	private static int numeroServidores; 
	
	private final static int capacidad = 10;
	
	private int capacidadActual;
	
	private boolean servidorEnEspera;
	
	public boolean mensajeEnEspera;
	
	private ArrayList mensajesEnCola;
	
	
	public Buffer()
	{
		capacidadActual = 0;
		servidorEnEspera = false;
		servidorEnEspera = false;
		mensajeEnEspera = false;
		mensajesEnCola = new ArrayList();
		
		
	}
	
	public synchronized void recibir(Mensaje mensaje)
	{
		while(capacidadActual == capacidad)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		capacidadActual++;
		
		
		while(!servidorEnEspera)
		{
			try
			{
				mensaje.wait();
			}	catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		mensajeEnEspera = true;
		mensajesEnCola.add(mensaje);		
			
	}
	
	public synchronized void enviarRespuesta(Mensaje mensaje)
	{
		mensaje.notify();
		notify();
		numeroClientes--;
		capacidadActual--;
	}

	public synchronized Mensaje enviar()
	{
		return (Mensaje) mensajesEnCola.remove(0);		
	}

	public static void main(String[] args) {
		//TODO procesar este numero de elementos por archivo
		try {
			BufferedReader lector = new BufferedReader(new FileReader(new File("./data/data.txt")));

			
			String laInfo = lector.readLine();
			String[] info = laInfo.split(":");
			numeroClientes = Integer.parseInt(info[1]);
			
			laInfo = lector.readLine();
			info = laInfo.split(":");
			numeroServidores = Integer.parseInt(info[1]);
			
			Buffer buffer = new Buffer();
			
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo acceder al archivo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
