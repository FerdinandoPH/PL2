/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package poo.pl2;

import java.awt.List;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PL2 {

    private static ListManager listManager;
    /** 
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
            System.out.println(e.getMessage());
            listManager = new ListManager();
            ListManager.usuarios.add(new Administrador("a@a.com","Admin1234"));
            ListManager.usuarios.add(new Administrador("admin@javabnb.com","admin"));
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    Path path = Paths.get(System.getProperty("user.dir"), "\\guardado.dat");
                    if (!Files.exists(path.getParent())){
                        System.out.println("Creando el directorio "+path.getParent().toString());
                        Files.createDirectories(path.getParent());
                    }
                    if (!Files.exists(path)){
                        System.out.println("Creando el archivo "+path.toString());
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
