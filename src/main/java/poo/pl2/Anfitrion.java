package poo.pl2;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import poo.pl2.Inmueble.tipoPropiedad;
/**
 * @author perez
 * @version 1.0
 * 
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

    public void setSuperAnfitrion(boolean esSuperAnfitiron) {
        this.superAnfitrion = esSuperAnfitiron;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    /**
     * Actualiza el estado de super anfitrión del anfitrión.
     * Un anfitrión se convierte en super anfitrión si la calificación promedio de sus inmuebles es mayor que 4.
     */
    public void actualizarSuperAnfitrion(){
        superAnfitrion= inmuebles.stream().mapToDouble(Inmueble::getCalificacion).average().orElse(0)>4?true:false;
    }
    
    /**
     * Constructor para la clase Anfitrion.
     * Inicializa un nuevo anfitrión con los detalles proporcionados. Aparte de los parámetros, este empieza sin ser super anfitrión, y con la fecha de registro actual
     *
     * @param correo El correo del anfitrión.
     * @param claveString La clave del anfitrión.
     * @param dni El DNI del anfitrión.
     * @param nombre El nombre del anfitrión.
     * @param telefono El teléfono del anfitrión.
     */
    public Anfitrion(String correo, String claveString, String dni, String nombre, String telefono){
        super(correo, claveString, dni, nombre, telefono);
        this.superAnfitrion = false;
        this.fechaRegistro = LocalDate.now();
    }

    /**
     * Método para añadir un nuevo inmueble a nombre del anfitrión.
     * 
     * @param direccion La dirección del inmueble.
     * @param titulo El título del inmueble.
     * @param baños El número de baños del inmueble.
     * @param camas El número de camas del inmueble.
     * @param rutaImagenInmueble La ruta de la imagen del inmueble, para crear el ImageIcon.
     * @param habitaciones El número de habitaciones del inmueble.
     * @param huespedesMaximos El número máximo de huéspedes del inmueble.
     * @param precioPorNoche El precio por noche del inmueble.
     * @param servicios Los servicios del inmueble.
     * @param tipo El tipo de propiedad del inmueble.
     */
    public void añadirInmueble(Direccion direccion, String titulo, int baños, int camas, String rutaImagenInmueble, int habitaciones, int huespedesMaximos, double precioPorNoche, String servicios, tipoPropiedad tipo){
        for (Inmueble i: inmuebles){
            if (i.getTitulo().equals(titulo))
                throw new IllegalArgumentException("Ya tienes un inmueble con ese título");
        }
        Inmueble inmueble = new Inmueble(direccion, titulo, this, baños, camas, rutaImagenInmueble, habitaciones, huespedesMaximos, precioPorNoche, servicios, tipo, inmuebles.size());
        inmuebles.add(inmueble);
    }
    /**
     * Método para borrar un inmueble del anfitrión.
     * 
     * @param id El ID del inmueble a borrar.
     */
    public void borrarInmueble(int id){
        inmuebles.remove(id);
    }
    /**
     * Método para editar un inmueble del anfitrión.
     * 
     * @param id El ID del inmueble a editar.
     * @param direccion La nueva dirección del inmueble.
     * @param titulo El nuevo título del inmueble.
     * @param baños El nuevo número de baños del inmueble.
     * @param camas El nuevo número de camas del inmueble.
     * @param rutaImagenInmueble La nueva ruta de la imagen del inmueble.
     * @param habitaciones El nuevo número de habitaciones del inmueble.
     * @param huespedesMaximos El nuevo número máximo de huéspedes del inmueble.
     * @param precioPorNoche El nuevo precio por noche del inmueble.
     * @param servicios Los nuevos servicios del inmueble.
     * @param tipo El nuevo tipo de propiedad del inmueble.
     */
    public void editarInmueble(int id, Direccion direccion, String titulo, int baños, int camas, String rutaImagenInmueble, int habitaciones, int huespedesMaximos,
            double precioPorNoche, String servicios, tipoPropiedad tipo){
        Inmueble inmuebleAEditar=inmuebles.stream().filter(i->i.getId()==id).findFirst().orElse(null);
        if (inmuebleAEditar==null)
            throw new IllegalArgumentException("No existe un inmueble con ese id");
        for (Inmueble i: Inmueble.getInmuebles()){
            if (i.getDireccion().equals(direccion)&&i.getId()!=id)
                throw new IllegalArgumentException("Ya existe un inmueble con esa dirección");
        }
        inmuebleAEditar.setDireccion(direccion);
        for (Inmueble i: inmuebles){
            if (i.getTitulo().equals(titulo)&&i.getId()!=id)
                throw new IllegalArgumentException("Ya tienes un inmueble con ese título");
        }
        inmuebleAEditar.setTitulo(titulo);
        inmuebleAEditar.setBaños(baños);
        inmuebleAEditar.setCamas(camas);
        if (!rutaImagenInmueble.equals("")) {
            try{
                BufferedImage imagenOriginal = ImageIO.read(Paths.get(rutaImagenInmueble).toFile());
                Image imagenEscalada = imagenOriginal.getScaledInstance(Inmueble.getEscala(),Inmueble.getEscala(), Image.SCALE_SMOOTH);
                inmuebleAEditar.setFotografia(new ImageIcon(imagenEscalada));
            }
            catch (IOException e){
                throw new IllegalArgumentException("No se ha podido cargar la imagen");
            }
        }
        inmuebleAEditar.setHabitaciones(habitaciones);
        inmuebleAEditar.setHuespedesMaximos(huespedesMaximos);
        inmuebleAEditar.setPrecioPorNoche(precioPorNoche);
        inmuebleAEditar.setServicios(servicios);
        inmuebleAEditar.setTipo(tipo);
    }
    @Override
    public String toString() {
        return "Anfitrion: ["+super.toString()+", esSuperAnfitiron=" + superAnfitrion + ", fechaRegistro=" + fechaRegistro + "]";
    }
    
}//end Anfitrion