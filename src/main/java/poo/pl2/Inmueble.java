package poo.pl2;
import javax.swing.ImageIcon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class Inmueble implements java.io.Serializable{
    private Direccion direccion;
    private String titulo;
    private Anfitrion dueño;
    private int baños;
    private double calificacion=0;
    private int camas;
    private ImageIcon fotografia;
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
        public ImageIcon getFotografia() {
            return fotografia;
        }
        public void setFotografia(ImageIcon fotografia) {
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
    public Inmueble(Direccion direccion, String titulo, Anfitrion dueño, int baños, int camas, ImageIcon fotografia, int habitaciones, int huespedesMaximos,
            double precioPorNoche, String servicios, tipoPropiedad tipo, int id) {
        if (baños<1 || camas<1 || habitaciones<1 || huespedesMaximos<1 || precioPorNoche<1 || titulo.isEmpty()|| precioPorNoche<0){
            throw new IllegalArgumentException("Los datos introducidos no son válidos");
        }
        this.baños = baños;
        this.camas = camas;
        this.fotografia = fotografia;
        this.habitaciones = habitaciones;
        this.huespedesMaximos = huespedesMaximos;
        this.precioPorNoche = precioPorNoche;
        this.servicios = servicios;
        this.tipo = tipo;
        this.titulo = titulo;
        this.dueño = dueño;
        this.direccion = direccion;
        this.id = id;
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