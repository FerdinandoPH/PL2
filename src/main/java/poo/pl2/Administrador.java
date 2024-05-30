package poo.pl2;
public class Administrador extends Usuario {

    /**
     * Constructor para la clase Administrador.
     *
     * @param correo El correo del administrador.
     * @param claveString La contrasenna del administrador.
     */
    public Administrador(String correo, String claveString) {
        super(correo, claveString);
    }

    @Override
    public String toString() {
        return "Administrador: [" + super.toString() + "]";
    }

}//end Administrador
