import java.util.ArrayList;


public class Cliente extends Thread {
	
	private int numeroMensajes;
	
	private ArrayList<Mensaje> mensajes;
	
	private Buffer buffer;
	
	public Cliente(Buffer b)
	{
		numeroMensajes = (int) Math.random();
		mensajes = new ArrayList<Mensaje>();
		buffer = b;
		
	}
	
	public void run()
	{
		for(int i = 0; i < numeroMensajes; i++)
		{
			mensajes.add(new Mensaje((int) Math.random(), this));
			buffer.recibir((Mensaje) mensajes.get(i));
		}
		
		buffer.salidaCliente();
	}

}
