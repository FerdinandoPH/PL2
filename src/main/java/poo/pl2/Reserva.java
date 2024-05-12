package poo.pl2;
import java.time.LocalDate;

/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class Reserva {

	private LocalDate fechaEntrada;
	private LocalDate fechaReserva;
	private LocalDate fechaSalida;
	private double importe;
	public Inmueble m_Inmueble;
	public Particular m_Particular;

	public Reserva(){

	}

	public void finalize() throws Throwable {

	}
	public void imprimirFactura(){

	}
}//end Reserva