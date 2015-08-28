import java.util.ArrayList;


public class Servidor {

	private Buffer buffer;

	public final static int INCREMENTO = 50;

	public Servidor(Buffer b){
		buffer = b;

	}

	public void run(){
		
		while(!buffer.acabe()){
			
			Mensaje aResponder = buffer.enviar();
			aResponder.cambiarRespondido();
			buffer.enviarRespuesta(aResponder);
			
		}
		
	}

}
