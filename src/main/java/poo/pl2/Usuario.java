package poo.pl2;


/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:07
 */
public class Usuario implements java.io.Serializable {

    private int clave;
    private String correo;
    public static enum tipoUsuario {ADMIN, ANFITRION, CLIENTE};
    
    public void setClave(String claveString) {
        this.clave = claveString.hashCode();
    }



    public void setCorreo(String correo) {
        this.correo = correo;
    }



    public Usuario(String correo, String claveString){
        this.correo = correo;
        this.clave = claveString.hashCode();

    }
        

        
        public String getCorreo(){
            return correo;
        }

        public int getClave() {
            return clave;
        }



        @Override
        public String toString() {
            return "correo=" + correo+", clave (hash)=" + clave;
        }
        
    
}//end Usuario