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
				
				System.out.println("asdasd");

				Mensaje aResponder = buffer.enviar();
				aResponder.cambiarRespondido();
				System.out.println("Se respondió al mensaje: " + aResponder.darNumSerie());
				buffer.enviarRespuesta(aResponder);
			}
			else{
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
