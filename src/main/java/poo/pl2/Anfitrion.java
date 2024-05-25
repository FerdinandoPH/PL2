package poo.pl2;

import java.time.LocalDate;
import java.util.ArrayList;



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
    public ArrayList<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(ArrayList<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }
    public boolean isSuperAnfitrion() {
        return superAnfitrion;
    }
    public void actualizarSuperAnfitrion(){
        superAnfitrion= inmuebles.stream().mapToDouble(Inmueble::getCalificacion).average().orElse(0)>4?true:false;
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

    public void añadirInmueble(Direccion direccion, String titulo, int baños, int camas, String rutaImagenInmueble, int habitaciones, int huespedesMaximos, double precioPorNoche, String servicios, tipoPropiedad tipo){

        Inmueble inmueble = new Inmueble(direccion, titulo, this, baños, camas, rutaImagenInmueble, habitaciones, huespedesMaximos, precioPorNoche, servicios, tipo, inmuebles.size());
        inmuebles.add(inmueble);
    }
    public void añadirInmueble(Inmueble inmueble){
        inmuebles.add(inmueble);
    }

    public void borrarInmueble(int id){
        inmuebles.remove(id);
    }
    public void editarInmueble(int id, Direccion direccion, String titulo, int baños, int camas, String rutaImagenInmueble, int habitaciones, int huespedesMaximos,
            double precioPorNoche, String servicios, tipoPropiedad tipo){
        Inmueble inmuebleAEditar=inmuebles.get(id);
        Inmueble inmueble = new Inmueble(direccion, titulo, this, baños, camas, rutaImagenInmueble.isEmpty()?inmuebleAEditar.getFotografia().getRuta():rutaImagenInmueble, habitaciones, huespedesMaximos, precioPorNoche, servicios, tipo, id, true);
        inmuebles.set(id, inmueble);
    }
    @Override
    public String toString() {
        return "Anfitrion: ["+super.toString()+", esSuperAnfitiron=" + superAnfitrion + ", fechaRegistro=" + fechaRegistro + "]";
    }
    
}//end Anfitrion