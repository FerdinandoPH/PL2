package poo.pl2;

public class Administrador extends Usuario {

    public Administrador(String correo, String claveString) {
        super(correo, claveString);
    }

    public void gestionarInmuebles() {

    }

    public void gestionarReservas() {

    }

    public void gestionarUsuarios() {

    }

    @Override
    public String toString() {
        return "Administrador: [" + super.toString() + "]";
    }

}//end Administrador