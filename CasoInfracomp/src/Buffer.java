import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Buffer {

	private static int numeroClientes;

	private static int numeroServidores;
	
	private Cliente[] clientes;
	private Servidor[] servidores;

	private final static int CAPACIDAD_MAXIMA = 10;

	/**
	 * Número actual de mensajes
	 */
	private int capacidadActual;
	
	/**
	 * Número actual de clientes en el sistema, es decir, clientes que aún tienen mensajes por mandar y recibir
	 */
	private int numClientes;
	
	private int numMensajesRecibidos;

	private ArrayList<Mensaje> mensajesEnCola;

	public Buffer() {
		capacidadActual = 0;
		mensajesEnCola = new ArrayList<Mensaje>();
		numClientes = numeroClientes;
		numMensajesRecibidos = 0;
		
		clientes = new Cliente[numeroClientes];
		servidores = new Servidor[numeroServidores];
		
		for(int i = 0; i < numeroServidores; i++){
			servidores[i] = new Servidor(this);
			servidores[i].start();
		}
		for(int i = 0; i < numeroClientes; i++){
			clientes[i] = new Cliente(this);
			clientes[i].start();
		}

	}
	
	/**
	 * Avisa si ya no hay clientes restantes
	 */
	public boolean acabe(){
		if(numClientes == 0)
			return true;
		else
			return false;
	}

	/**
	 * Recibe un mensaje de un cliente
	 * @param mensaje
	 */
	public synchronized void recibir(Mensaje mensaje) {
		
		System.out.println("Entró");
		
		while (capacidadActual == CAPACIDAD_MAXIMA) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Cuando la capacidadActual es menor a la capacidad maxima
		// Se aumenta los mensajes actuales y se encola el mensaje
		
		System.out.println("Antes de meter el mensaje");
		
		numMensajesRecibidos++;
		capacidadActual++;
		mensaje.asignarNumSerie(numMensajesRecibidos);
		mensajesEnCola.add(mensaje);

		System.out.println("Metio el mensaje");
		while (!mensaje.fueRespondido()) {
			try {
				synchronized (mensaje) {
					System.out.println("Dormido");
					System.out.println(mensajesEnCola.size());
					mensaje.wait();
				}
				
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		
		System.out.println("Sale");

//		mensajesEnCola.add(mensaje);

	}

	public synchronized void enviarRespuesta(Mensaje mensaje) {
		synchronized (mensaje) {
			mensaje.notify();
		}
		notify();
//		numeroClientes--;
		capacidadActual--;
	}

	public synchronized Mensaje enviar() {
		if(mensajesEnCola.size() > 0)
			return mensajesEnCola.remove(0);
		else
			return null;
	}
	
	public ArrayList<Mensaje> darMensajesEnCola(){
		return mensajesEnCola;
	}
	
	public synchronized void salidaCliente(){
		numClientes--;	
	}

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

			Buffer buffer = new Buffer();
			
			

		} catch (FileNotFoundException e) {
			System.out.println("No se pudo acceder al archivo");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
