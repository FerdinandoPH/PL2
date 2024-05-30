package poo.pl2;

public class Direccion implements java.io.Serializable{
    private String calle;
    private String numero;
    private String ciudad;
    private String codigoPostal;
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getCodigoPostal() {
        return codigoPostal;
    }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
        /**
     * Constructor para la clase Direccion.
     * Inicializa una nueva direccion con los detalles proporcionados.
     *
     * @param calle La calle de la direccion.
     * @param numero El numero de la direccion.
     * @param ciudad La ciudad de la direccion.
     * @param codigoPostal El codigo postal de la direccion.
     * @throws IllegalArgumentException si alguno de los parametros esta vacio.
     */
    public Direccion(String calle, String numero, String ciudad, String codigoPostal) {
        if (calle.isEmpty() || ciudad.isEmpty() || codigoPostal.isEmpty()|| numero.isEmpty())
            throw new IllegalArgumentException("Datos de direccion no validos");
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.numero = numero;
    }
    @Override
    public String toString() {
        return "C/ " + calle + ", nº " + numero + ", " + ciudad + ", C.P. " + codigoPostal;
    }
    /**
     * Dos direcciones son iguales si tienen la misma calle, numero, ciudad y codigo postal.
     *
     * @param obj El objeto a comparar con esta direccion.
     * @return true si este objeto es igual al objeto especificado; false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Direccion other = (Direccion) obj;
        if (calle == null) {
            if (other.calle != null)
                return false;
        } else if (!calle.equals(other.calle))
            return false;
        if (ciudad == null) {
            if (other.ciudad != null)
                return false;
        } else if (!ciudad.equals(other.ciudad))
            return false;
        if (codigoPostal == null) {
            if (other.codigoPostal != null)
                return false;
        } else if (!codigoPostal.equals(other.codigoPostal))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        return true;
    }
    
    
    
}
