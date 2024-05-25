package poo.pl2;
import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class Inmueble implements java.io.Serializable{
    private static int escala=200;
    public static int getEscala() {
        return escala;
    }
    private Direccion direccion;
    private String titulo;
    private Anfitrion dueño;
    private int baños;
    private double calificacion=0;
    private int camas;
    private ImageIconConRuta fotografia;
    private int habitaciones;
    private int huespedesMaximos;
    private double precioPorNoche;
    private String servicios;
    public static enum tipoPropiedad {APARTAMENTO, CASA}
    private tipoPropiedad tipo;
    private int vecesReservado=0;
    private ArrayList<Reseña> reseñas = new ArrayList<Reseña>();
    private int id;
    //region gettersYsetters
        public int getBaños() {
            return baños;
        }
        public void setBaños(int baños) {
            this.baños = baños;
        }
        public double getCalificacion() {
            return calificacion;
        }
        public void setCalificacion(double calificacion) {
            this.calificacion = calificacion;
        }
        public int getCamas() {
            return camas;
        }
        public void setCamas(int camas) {
            this.camas = camas;
        }
        public ImageIconConRuta getFotografia() {
            return fotografia;
        }
        public void setFotografia(ImageIconConRuta fotografia) {
            this.fotografia = fotografia;
        }
        public int getHabitaciones() {
            return habitaciones;
        }
        public void setHabitaciones(int habitaciones) {
            this.habitaciones = habitaciones;
        }
        public int getHuespedesMaximos() {
            return huespedesMaximos;
        }
        public void setHuespedesMaximos(int huespedesMaximos) {
            this.huespedesMaximos = huespedesMaximos;
        }
        public double getPrecioPorNoche() {
            return precioPorNoche;
        }
        public void setPrecioPorNoche(double precioPorNoche) {
            this.precioPorNoche = precioPorNoche;
        }
        public String getServicios() {
            return servicios;
        }
        public void setServicios(String servicios) {
            this.servicios = servicios;
        }
        public tipoPropiedad getTipo() {
            return tipo;
        }
        public void setTipo(tipoPropiedad tipo) {
            this.tipo = tipo;
        }
        public String getTitulo() {
            return titulo;
        }
        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }
        public int getVecesReservado() {
            return vecesReservado;
        }
        public void setVecesReservado(int vecesReservado) {
            this.vecesReservado = vecesReservado;
        }
        public Anfitrion getDueño() {
            return dueño;
        }
        public void setDueño(Anfitrion dueño) {
            this.dueño = dueño;
        }
        public ArrayList<Reseña> getReseñas() {
            return reseñas;
        }
        public Direccion getDireccion() {
            return direccion;
        }
        public void setDireccion(Direccion direccion) {
            this.direccion = direccion;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public void actualizarCalificacion(){

            calificacion = reseñas.stream().mapToDouble(Reseña::getCalificacion).average().orElse(0);
            dueño.actualizarSuperAnfitrion();
        }
    //endregion
    public Inmueble(Direccion direccion, String titulo, Anfitrion dueño, int baños, int camas, String rutaImagenInmueble, int habitaciones, int huespedesMaximos,
            double precioPorNoche, String servicios, tipoPropiedad tipo, int id, boolean ignorarDireccionRepetida) {
        if (baños<1 || camas<1 || habitaciones<1 || huespedesMaximos<1 || precioPorNoche<1 || titulo.isEmpty()|| precioPorNoche<0 ||rutaImagenInmueble.isEmpty()){
            throw new IllegalArgumentException("Los datos introducidos no son válidos");
        }
        this.baños = baños;
        this.camas = camas;
        try{
            Path rutaDestino=Paths.get(System.getProperty("user.dir"), "Recursos","fotos", new File(rutaImagenInmueble).getName());
            int sufijo = 1;
            while (Files.exists(rutaDestino)) {
                String stringRuta = new File(rutaImagenInmueble).getName();
                String nombreSinExt = stringRuta.substring(0, stringRuta.lastIndexOf('.'));
                String extension = stringRuta.substring(stringRuta.lastIndexOf('.'));
                rutaDestino = Paths.get(System.getProperty("user.dir"), "Recursos","fotos", nombreSinExt + "(" + sufijo + ")" + extension);
                sufijo++;
            }
            Files.copy(Paths.get(rutaImagenInmueble), rutaDestino);
            BufferedImage imagenOriginal = ImageIO.read(rutaDestino.toFile());
            Image imagenEscalada = imagenOriginal.getScaledInstance(escala,escala, Image.SCALE_SMOOTH);
            this.fotografia = new ImageIconConRuta(imagenEscalada, rutaDestino.toString());
        }catch(IOException e){
            e.printStackTrace();
            throw new IllegalArgumentException("No se ha podido cargar la imagen");
        }
        this.habitaciones = habitaciones;
        this.huespedesMaximos = huespedesMaximos;
        this.precioPorNoche = precioPorNoche;
        this.servicios = servicios;
        this.tipo = tipo;
        this.titulo = titulo;
        this.dueño = dueño;
        if (!ignorarDireccionRepetida){
            for (Inmueble i: Inmueble.getInmuebles()){
                if (i.getDireccion().equals(direccion)){
                    throw new IllegalArgumentException("Ya existe un inmueble con esa dirección");
                }
            }
        }
        this.direccion = direccion;
        this.id = id;
    }
    public Inmueble(Direccion direccion, String titulo, Anfitrion dueño, int baños, int camas, String rutaImagenInmueble, int habitaciones, int huespedesMaximos,
            double precioPorNoche, String servicios, tipoPropiedad tipo, int id) {
        this(direccion, titulo, dueño, baños, camas, rutaImagenInmueble, habitaciones, huespedesMaximos, precioPorNoche, servicios, tipo, id, false);
    }
    @Override
    public String toString() {
        return "Inmueble [direccion=" + direccion.toString() + ", titulo=" + titulo + ", dueño=" + dueño.getCorreo() + ", baños=" + baños
                + ", calificacion=" + calificacion + ", camas=" + camas + ", habitaciones=" + habitaciones + ", huespedesMaximos=" + huespedesMaximos + ", precioPorNoche="
                + precioPorNoche + ", servicios=" + servicios + ", tipo=" + tipo + ", vecesReservado=" + vecesReservado
                + ", reseñas=" + reseñas.toString() + "]";
    }
    public static ArrayList<Inmueble> getInmuebles(){
        ArrayList<Inmueble> inmuebles = new ArrayList<Inmueble>();
        for (Usuario u: ListManager.usuarios){
            if (u instanceof Anfitrion){
                Anfitrion a = (Anfitrion) u;
                inmuebles.addAll(a.getInmuebles());
            }
        }
        Collections.sort(inmuebles, new Comparator<Inmueble>(){
            @Override
            public int compare(Inmueble i1, Inmueble i2){
                return !i1.getDueño().equals(i2.getDueño()) ? i1.getDueño().getCorreo().compareTo(i2.getDueño().getCorreo()) : Integer.valueOf(i1.getId()).compareTo(Integer.valueOf(i2.getId()));
            }
        }
        );
        return inmuebles;
    }
    public void añadirReseña(Reseña reseña){
        reseñas.add(reseña);
        actualizarCalificacion();
    }

    
}//end Inmueble