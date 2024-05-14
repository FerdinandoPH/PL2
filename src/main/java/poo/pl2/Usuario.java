package poo.pl2;


/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:07
 */
public class Usuario {

	private int clave;
	private String correo;

	public Usuario(String correo, String claveString){
		this.correo = correo;
		this.clave = claveString.hashCode();

	}
        
	public void iniciarSesion(){

	}

	public void registrar(){

	}
        
        public String getCorreo(){
            return correo;
        }

		public int getClave() {
			return clave;
		}
		
	
}//end Usuario