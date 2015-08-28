import java.util.ArrayList;


public class Cliente extends Thread {
	
	private int numeroMensajes;
	
	private ArrayList<Mensaje> mensajes;
	
	private Buffer buffer;
	
	public Cliente(Buffer b)
	{
		numeroMensajes = (int) (Math.random()*100);
		mensajes = new ArrayList<Mensaje>();
		buffer = b;
		
	}
	
	public void run()
	{
		for(int i = 0; i < numeroMensajes; i++)
		{
			System.out.println("Mensaje número: " + i);
			
			Mensaje nuevo = new Mensaje((int) (Math.random()*10), this);
			mensajes.add(nuevo);
			buffer.recibir(nuevo);
			
			System.out.println("Se recibió rta al mensaje: " + nuevo.darNumSerie());
		}
		
		buffer.salidaCliente();
	}

}
