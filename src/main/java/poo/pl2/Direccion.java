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
    public Direccion(String calle, String numero, String ciudad, String codigoPostal) {
        if (calle.isEmpty() || ciudad.isEmpty() || codigoPostal.isEmpty()|| numero.isEmpty())
            throw new IllegalArgumentException("Datos de dirección no válidos");
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.numero = numero;
    }
    @Override
    public String toString() {
        return "Calle=" + calle + ", numero=" + numero + ", ciudad=" + ciudad + ", codigoPostal="
                + codigoPostal;
    }
    
    
}
