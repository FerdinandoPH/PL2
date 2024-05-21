package poo.pl2;

import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class ListManager implements java.io.Serializable{

    private static ArrayList<Reserva> reservas;
    public static ArrayList<Usuario> usuarios;

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