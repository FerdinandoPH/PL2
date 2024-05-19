package poo.pl2;
import java.time.LocalDate;

/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class Reserva implements java.io.Serializable{
    private LocalDate fechaReserva;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private double importe;
    private Inmueble inmueble;
    private Particular particular;

    public Reserva(){
        
    }

    public void imprimirFactura(){

    }
}//end Reserva