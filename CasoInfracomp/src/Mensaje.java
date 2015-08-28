
public class Mensaje {
	
	private int mensaje;
	
	public Mensaje(int m)
	{
		mensaje = m;
	}
	
	public int darMensaje()
	{
		return mensaje;
	}
	
	public void modificarMensaje(int incremento)
	{
		mensaje += incremento;
	}

}
