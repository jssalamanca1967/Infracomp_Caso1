
public class Mensaje {
	
	private int mensaje;
	private boolean respondido;
	private Cliente cliente;
	
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
	
	public void cambiarRespondido(){
		if(respondido)
			respondido = false;
		else
			respondido = true;
	}
	
	public Cliente darCliente(){
		return cliente;
	}
	
	public void modificarMensaje(int incremento)
	{
		mensaje += incremento;
	}

}
