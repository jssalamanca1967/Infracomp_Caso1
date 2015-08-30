import java.util.ArrayList;


public class Cliente extends Thread {
	
	private int numeroMensajes;
	
	private ArrayList<Mensaje> mensajes;
	
	private Buffer buffer;
	
	public Cliente(Buffer b)
	{
		numeroMensajes = (int) (Math.random()*10);
		mensajes = new ArrayList<Mensaje>();
		buffer = b;
		
	}
	
	public void run()
	{
		
		System.out.println("EL NUMERO DE MENSAJES ES: " + numeroMensajes);
		for(int i = 0; i < numeroMensajes; i++)
		{
			
			
			Mensaje nuevo = new Mensaje((int) (Math.random()*10), this);
			System.out.println("Mensaje numero: " + i+1 + ", el valor es " + nuevo.darMensaje());
			mensajes.add(nuevo);
			Mensaje retorno = buffer.recibir(nuevo);
			
			System.out.println("Se recibio rta al mensaje: " + retorno.darNumSerie() + ", el nuevo valor es " + retorno.darMensaje());
		}
		
		buffer.salidaCliente();
	}

}
