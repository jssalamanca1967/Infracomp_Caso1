
public class Mensaje {
	
	private int mensaje;
	private boolean respondido;
	private Cliente cliente;
	
	private Object cola;
	

	public final static int INCREMENTO = 50;
	
	/**
	 * Numero consecutivo del mensaje - Para probar si sirve o no
	 */
	private int numSerie;
	
	/**
	 * 
	 * @param m. Mensaje
	 * @param cliente. Cliente que hizo el mensaje
	 */
	public Mensaje(int m, Cliente cliente)
	{
		mensaje = m;
		respondido = false;
		this.cliente = cliente;
		cola = new Object();
	}
	
	public int darNumSerie(){
		return numSerie;
	}
	
	public void asignarNumSerie(int numSerie){
		this.numSerie = numSerie;
	}
	
	public int darMensaje()
	{
		return mensaje;
	}
	
	public boolean fueRespondido(){
		return respondido;
	}
	
	public  void cambiarRespondido(){
			
		respondido = true;
		
		modificarMensaje(INCREMENTO);
	}
	
	public Cliente darCliente(){
		return cliente;
	}
	
	public void modificarMensaje(int incremento)
	{
		mensaje += incremento;
	}

	public synchronized void esperarEnCola() throws InterruptedException {
//		synchronized (cola) {
//			try {
//				cola.wait();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//		}	
		
		wait();
	}
	
	public void salirDeLaCola() {
		synchronized (cola) {
			cola.notify();	
		}
	}

}
