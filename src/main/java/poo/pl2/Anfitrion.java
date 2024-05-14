package poo.pl2;
import java.time.LocalDate;

/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class Anfitrion extends Cliente {

    private boolean esSuperAnfitiron;
    private LocalDate fechaRegistro;

    public Anfitrion(String correo, String claveString, String dni, String nombre, String telefono){
        super(correo, claveString, dni, nombre, telefono);
        this.esSuperAnfitiron = false;
        this.fechaRegistro = LocalDate.now();
    }

    public void consultarReservas(){

    }

    public void crearInmueble(){

    }

    @Override
    public String toString() {
        return "Anfitrion: ["+super.toString()+", esSuperAnfitiron=" + esSuperAnfitiron + ", fechaRegistro=" + fechaRegistro + "]";
    }
    
}//end Anfitrion