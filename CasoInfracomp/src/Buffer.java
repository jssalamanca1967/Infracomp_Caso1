import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Buffer {
	
	private Cliente[] clientes;
	private Servidor[] servidores;

	private final static int CAPACIDAD_MAXIMA = 10;

	/**
	 * N�mero actual de mensajes
	 */
	private int capacidadActual;
	
	/**
	 * N�mero actual de clientes en el sistema, es decir, clientes que a�n tienen mensajes por mandar y recibir
	 */
	private int numClientes;
	
	private int numMensajesRecibidos;

	private ArrayList<Mensaje> mensajesEnCola;

	public Buffer(int numeroClientes, int numeroServidores) {
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
	public synchronized boolean acabe(){
		if(numClientes == 0)
			return true;
		else
			return false;
	}

	/**
	 * Recibe un mensaje de un cliente
	 * @param mensaje
	 */
	public synchronized Mensaje recibir(Mensaje mensaje) {
		
		
		while (capacidadActual == CAPACIDAD_MAXIMA) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Cuando la capacidadActual es menor a la capacidad maxima
		// Se aumenta los mensajes actuales y se encola el mensaje
				
		numMensajesRecibidos++;
		capacidadActual++;
		mensaje.asignarNumSerie(numMensajesRecibidos);
		mensajesEnCola.add(mensaje);

		while (!mensaje.fueRespondido()) {
			try {
				
				mensaje.esperarEnCola();
				
				
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		
		return mensaje;
		


	}

	public synchronized void enviarRespuesta(Mensaje mensaje) {
		
		mensaje.salirDeLaCola();
		
		notify();
		capacidadActual--;
	}

	public synchronized Mensaje enviar() {
		if(mensajesEnCola.size() > 0)
			return mensajesEnCola.remove(0);
		else
			return null;
	}
	
	public synchronized ArrayList<Mensaje> darMensajesEnCola(){
		System.out.println("retorna mensajes en cola");
		return mensajesEnCola;
	}
	
	public synchronized void salidaCliente(){
		numClientes--;	
	}

}