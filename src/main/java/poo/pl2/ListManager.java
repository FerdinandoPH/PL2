package poo.pl2;

import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.*;

/**
 * @author perez
 * @version 1.0
 * @created 07-may.-2024 17:29:06
 */
public class ListManager implements java.io.Serializable{

    public static ArrayList<Inmueble> inmuebles;
    public static ArrayList<Reserva> reservas;
    public static ArrayList<Usuario> usuarios;

    public ListManager(){
        inmuebles = new ArrayList<Inmueble>();
        reservas = new ArrayList<Reserva>();
        usuarios = new ArrayList<Usuario>();

    }
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(inmuebles);
        oos.writeObject(reservas);
        oos.writeObject(usuarios);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        inmuebles = (ArrayList<Inmueble>) ois.readObject();
        reservas = (ArrayList<Reserva>) ois.readObject();
        usuarios = (ArrayList<Usuario>) ois.readObject();
    }

}//end ListManager