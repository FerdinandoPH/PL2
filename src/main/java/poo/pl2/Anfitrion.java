package poo.pl2;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import poo.pl2.Inmueble.tipoPropiedad;
/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class Anfitrion extends Cliente {

    private boolean superAnfitrion;
    private LocalDate fechaRegistro;
    private ArrayList<Inmueble> inmuebles = new ArrayList<Inmueble>();
    public boolean isSuperAnfitrion() {
        return superAnfitrion;
    }

    public void setSuperAnfitrion(boolean esSuperAnfitiron) {
        this.superAnfitrion = esSuperAnfitiron;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    

    public Anfitrion(String correo, String claveString, String dni, String nombre, String telefono){
        super(correo, claveString, dni, nombre, telefono);
        this.superAnfitrion = false;
        this.fechaRegistro = LocalDate.now();
    }

    public void consultarReservas(){

    }

    public void añadirInmueble(String calle, String numero, String ciudad, String codigoPostal, String titulo, int baños, int camas, ImageIcon fotografia, int habitaciones, int huespedesMaximos,
            double precioPorNoche, String servicios, tipoPropiedad tipo){

        Inmueble inmueble = new Inmueble(new Direccion(calle, numero, ciudad, codigoPostal),titulo, this, baños, camas, fotografia, habitaciones, huespedesMaximos, precioPorNoche, servicios, tipo);
        inmuebles.add(inmueble);
    }

    @Override
    public String toString() {
        return "Anfitrion: ["+super.toString()+", esSuperAnfitiron=" + superAnfitrion + ", fechaRegistro=" + fechaRegistro + "]";
    }

    public ArrayList<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(ArrayList<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }
    
}//end Anfitrion