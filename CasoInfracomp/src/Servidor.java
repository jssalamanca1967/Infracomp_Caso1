import java.util.ArrayList;


public class Servidor {

	private Buffer buffer;

	public final static int INCREMENTO = 50;

	public Servidor(Buffer b){
		buffer = b;

	}

	public void run(){
		
		while(true){
			while(!buffer.mensajeEnEspera)
			{

			}

			Mensaje mensaje = buffer.enviar();
			mensaje.modificarMensaje(INCREMENTO);
			buffer.enviarRespuesta(mensaje);
		}

	}

}
