package poo.pl2;


/**
 * @author perez
 * @version 1.0
 * 
 */
public class Cliente extends Usuario {

    private String dni;
    private String nombre;
    private String telefono;
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /**
     * Constructor para la clase Cliente.
     * Inicializa un nuevo cliente con los detalles proporcionados.
     *
     * @param correo El correo del cliente.
     * @param claveString La clave del cliente.
     * @param dni El DNI del cliente.
     * @param nombre El nombre del cliente.
     * @param telefono El telefono del cliente.
     */
    public Cliente(String correo, String claveString, String dni, String nombre, String telefono){
        super(correo, claveString);
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;

    }

    @Override
    public String toString() {
        return super.toString()+", dni=" + dni + ", nombre=" + nombre + ", telefono=" + telefono;
    }
    
}//end Cliente