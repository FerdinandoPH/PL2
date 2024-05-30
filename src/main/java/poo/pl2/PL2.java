/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package poo.pl2;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * La clase PL2 es la clase principal del programa.
 * Se encarga de la gestion de la persistencia del ListManager y de iniciar la GUI (comienza en el inicio de sesion).
 * Intenta cargar el ListManager de un archivo de guardado, si no puede, crea un nuevo ListManager con los administradores por defecto.
 * 
 * @author perez
 * @version 1.0
 * @since 07-may.-2024
 */
public class PL2 {

    private static ListManager listManager;
    /** 
     * El metodo principal del programa.
     * Intenta cargar los datos de una sesion anterior, crea un nuevo ListManager si no puede cargar los datos,
     * guarda los datos actuales al cerrar, e inicia la interfaz grafica de usuario.
     *
     * @param args 
     */
    public static void main(String[] args) {
        try{
            Path path = Paths.get(System.getProperty("user.dir"),"\\guardado.dat");
            
            if (Files.exists(path)) {
                FileInputStream fis = new FileInputStream(path.toString());
                ObjectInputStream ois = new ObjectInputStream(fis);
                listManager = (ListManager) ois.readObject();
                ois.close();
                fis.close();
            }
            else{
                throw new IOException("No se ha encontrado el archivo de guardado");
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println("El guardado es incompatible o no se ha encontrado, creando un nuevo ListManager");
            listManager = new ListManager();
            ListManager.getUsuarios().add(new Administrador("a@a.com","Admin1234"));
            ListManager.getUsuarios().add(new Administrador("admin@javabnb.com","admin"));
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    Path path = Paths.get(System.getProperty("user.dir"), "\\guardado.dat");
                    if (!Files.exists(path.getParent())){
                        Files.createDirectories(path.getParent());
                    }
                    if (!Files.exists(path)){
                        Files.createFile(path);
                    }
                    System.out.println("Guardando datos en "+path.toString());
                    FileOutputStream fos = new FileOutputStream(path.toString());
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(listManager);
                    oos.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        GUI_inicioSesion inicioSesion = new GUI_inicioSesion();
        inicioSesion.setVisible(true);
    }
}
