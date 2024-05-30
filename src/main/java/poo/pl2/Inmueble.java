package poo.pl2;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
/**
 * @author perez
 * @version 1.0
 * 
 */
public class Inmueble implements java.io.Serializable{
    private static int escala=200;
    public static int getEscala() {
        return escala;
    }
    private Direccion direccion;
    private String titulo;
    private Anfitrion duenno;
    private int bannos;
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
    private ArrayList<Resenna> resennas = new ArrayList<Resenna>();
    private int id;
    //region gettersYsetters
        public int getBannos() {
            return bannos;
        }
        public void setBannos(int bannos) {
            this.bannos = bannos;
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
        public Anfitrion getDuenno() {
            return duenno;
        }
        public void setDuenno(Anfitrion duenno) {
            this.duenno = duenno;
        }
        public ArrayList<Resenna> getResennas() {
            Collections.sort(resennas, new Comparator<Resenna>(){
                public int compare(Resenna r1, Resenna r2){
                    return r1.getFechaResenna().compareTo(r2.getFechaResenna()); //Devuelve las resennas por orden de fecha de creacion
                }
            });
            return resennas;
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

            calificacion = resennas.stream().mapToDouble(Resenna::getCalificacion).average().orElse(0);
            duenno.actualizarSuperAnfitrion();
        }
    //endregion
    /**
 * Constructor para la clase Inmueble.
 * Inicializa un nuevo inmueble con los detalles proporcionados.
 *
 * @param direccion La direccion del inmueble.
 * @param titulo El titulo del inmueble.
 * @param duenno El duenno del inmueble.
 * @param bannos El numero de bannos del inmueble.
 * @param camas El numero de camas del inmueble.
 * @param rutaImagenInmueble La ruta de la imagen del inmueble.
 * @param habitaciones El numero de habitaciones del inmueble.
 * @param huespedesMaximos El numero maximo de huespedes del inmueble.
 * @param precioPorNoche El precio por noche del inmueble.
 * @param servicios Los servicios del inmueble.
 * @param tipo El tipo de propiedad del inmueble.
 * @param id El ID del inmueble dentro de los inmuebles del anfitrion.
 * @throws IllegalArgumentException si los datos introducidos no son validos (valores negativos o vacios), si no se ha podido cargar la imagen o si ya existe un inmueble con la misma direccion.
 */
    public Inmueble(Direccion direccion, String titulo, Anfitrion duenno, int bannos, int camas, String rutaImagenInmueble, int habitaciones, int huespedesMaximos,
            double precioPorNoche, String servicios, tipoPropiedad tipo, int id) {
        if (bannos<1 || camas<1 || habitaciones<1 || huespedesMaximos<1 || precioPorNoche<1 || titulo.isEmpty()|| precioPorNoche<0 ||rutaImagenInmueble.isEmpty()){
            throw new IllegalArgumentException("Los datos introducidos no son validos");
        }
        this.bannos = bannos;
        this.camas = camas;
        try{
            
            BufferedImage imagenOriginal = ImageIO.read(Paths.get(rutaImagenInmueble).toFile());
            Image imagenEscalada = imagenOriginal.getScaledInstance(escala,escala, Image.SCALE_SMOOTH);
            this.fotografia = new ImageIcon(imagenEscalada);
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
        this.duenno = duenno;
        this.direccion = direccion;
        this.id = id;
    }
    /**
     * Obtiene una lista de todos los inmuebles en el sistema.
     * Este metodo recorre todos los usuarios, y si el usuario es un anfitrion, annade todos sus inmuebles a la lista.
     * La lista de inmuebles se ordena primero por el correo del duenno y luego por el ID del inmueble.
     *
     * @return Una lista de todos los inmuebles en el sistema.
     */
    public static ArrayList<Inmueble> getInmuebles(){
        ArrayList<Inmueble> inmuebles = new ArrayList<Inmueble>();
        for (Usuario u: ListManager.getUsuarios()){
            if (u instanceof Anfitrion){
                Anfitrion a = (Anfitrion) u;
                inmuebles.addAll(a.getInmuebles());
            }
        }
        Collections.sort(inmuebles, new Comparator<Inmueble>(){
            @Override
            public int compare(Inmueble i1, Inmueble i2){
                return !i1.getDuenno().equals(i2.getDuenno()) ? i1.getDuenno().getCorreo().compareTo(i2.getDuenno().getCorreo()) : Integer.valueOf(i1.getId()).compareTo(Integer.valueOf(i2.getId()));
            }
        }
        );
        return inmuebles;
    }
    /**
     * Annade una resenna a este inmueble y actualiza la calificacion del inmueble, que a su vez actualiza la califcacion media del anfitrion y determina si es superanfitrion o no.
     *
     * @param resenna La resenna a annadir.
     */
    public void annadirResenna(Resenna resenna){
        resennas.add(resenna);
        actualizarCalificacion();
    }
    @Override
    public String toString() {
        return "Inmueble [direccion=" + direccion.toString() + ", titulo=" + titulo + ", duenno=" + duenno.getCorreo() + ", bannos=" + bannos
                + ", calificacion=" + calificacion + ", camas=" + camas + ", habitaciones=" + habitaciones + ", huespedesMaximos=" + huespedesMaximos + ", precioPorNoche="
                + precioPorNoche + ", servicios=" + servicios + ", tipo=" + tipo + ", vecesReservado=" + vecesReservado
                + ", resennas=" + resennas.toString() + "]";
    }


    
}//end Inmueble