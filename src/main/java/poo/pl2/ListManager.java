package poo.pl2;

import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * La clase ListManager gestiona las listas de usuarios y reservas del sistema.
 * Se encarga de mantener la persistencia de los datos de los usuarios y reservas entre sesiones.
 * Tambien permite el acceso a las listas de usuarios y reservas desde cualquier parte del sistema.
 * 
 * @author perez
 * @version 1.0
 * @since 07-may.-2024
 */
public class ListManager implements java.io.Serializable{

    private static ArrayList<Reserva> reservas;
    private static ArrayList<Usuario> usuarios;
    /**
     * Obtiene la lista de los usuarios del sistema.
     *
     * @return Una ArrayList con los usuarios del sistema.
     */
    public static ArrayList<Usuario> getUsuarios(){
        return usuarios;
    }
    /**
     * Obtiene la lista de las reservas del sistema.
     *
     * @return Una ArrayList con las reservas del sistema.
     */
    public static ArrayList<Reserva> getReservas(){
        return reservas;
    }
    public ListManager(){
        reservas = new ArrayList<Reserva>();
        usuarios = new ArrayList<Usuario>();

    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(reservas);
        oos.writeObject(usuarios);
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        reservas = (ArrayList<Reserva>) ois.readObject();
        usuarios = (ArrayList<Usuario>) ois.readObject();
    }

}//end ListManager