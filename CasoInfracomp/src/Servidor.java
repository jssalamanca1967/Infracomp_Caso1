import java.util.ArrayList;


public class Servidor extends Thread{

	private Buffer buffer;

	public final static int INCREMENTO = 50;

	public Servidor(Buffer b){
		buffer = b;

	}

	public void run(){
		
		while(!buffer.acabe()){
			
			if(buffer.darMensajesEnCola().size() > 0){

				Mensaje aResponder = buffer.enviar();
				aResponder.cambiarRespondido();
				System.out.println("Se respondi� al mensaje: " + aResponder.darNumSerie());
				buffer.enviarRespuesta(aResponder);
			}
			
		}
		
	}

}
