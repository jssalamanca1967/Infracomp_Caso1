import java.util.ArrayList;


public class Servidor extends Thread{

	private Buffer buffer;

	public Servidor(Buffer b){
		buffer = b;

	}

	public void run(){

		while(!buffer.acabe()){

			if(buffer.darMensajesEnCola().size() > 0){


				if(buffer.darMensajesEnCola().size() > 0)
				{
					Mensaje aResponder = buffer.enviar();
					aResponder.cambiarRespondido();
					buffer.enviarRespuesta(aResponder);
				}
			}
			else{
				System.out.println("sede procesador");
				yield();

			}

		}

	}

}
