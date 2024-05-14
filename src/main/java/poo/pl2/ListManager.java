package poo.pl2;

import java.util.ArrayList;


/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class ListManager {

	private static ArrayList<Inmueble> inmuebles;
	private static ArrayList<Reserva> reservas;
	public static ArrayList<Usuario> usuarios;

	public ListManager(){

	}
        
        public static boolean correoRepetido(String correo){
            for (int i=0; i<usuarios.size(); i++)
                if (usuarios.get(i).getCorreo().equals(correo))
                    return true;
            return false;
        }
}//end ListManager