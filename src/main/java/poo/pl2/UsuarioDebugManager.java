package poo.pl2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;


public class UsuarioDebugManager {
    private static ListManager listManager;
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
        String respuesta="";
        while (!respuesta.toLowerCase().equals("5")){
            System.out.println("--------------------------------------------");
            System.out.println("¿Qué quieres hacer?\n"+
                    "1. Añadir usuario\n"+
                    "2. Eliminar usuario\n"+
                    "3. Mostrar usuarios\n"+
                    "4. Modificar usuario\n"+
                    "5. Salir");
            respuesta = System.console().readLine();
            System.out.println("--------------------------------------------");
            switch (respuesta){
                case "1":
                    System.out.println("Introduce el email del usuario");
                    String email = System.console().readLine();
                    System.out.println("Introduce la clave del usuario");
                    String clave = System.console().readLine();
                    System.out.println("El usuario es administrador? (s/n)");
                    boolean admin = System.console().readLine().toLowerCase().equals("s");
                    if (admin){
                        ListManager.usuarios.add(new Administrador(email, clave));
                        System.out.println("Administrador añadido");
                        break;
                    }
                    System.out.println("Introduce el DNI del usuario");
                    String dni = System.console().readLine();
                    System.out.println("Introduce el nombre del usuario");
                    String nombre = System.console().readLine();
                    System.out.println("Introduce el teléfono del usuario");
                    String telefono = System.console().readLine();
                    System.out.println("El usuario es anfitirión? (s/n)");
                    boolean anfitrion = System.console().readLine().toLowerCase().equals("s");
                    if (anfitrion){
                        ListManager.usuarios.add(new Anfitrion(email, clave, dni, nombre, telefono));
                        System.out.println("Anfitrión añadido");
                        break;
                    }
                    System.out.println("El usuario es particular (s/n)");
                    boolean particular = System.console().readLine().toLowerCase().equals("s");
                    if (particular){
                        System.out.println("Introduce el nombre de la tarjeta de crédito");
                        String nombreTarjeta = System.console().readLine();
                        System.out.println("Introduce el número de la tarjeta de crédito");
                        String numeroTarjeta = System.console().readLine();
                        System.out.println("Introduce el año de caducidad de la tarjeta de crédito");
                        String añoCaducidad = System.console().readLine();
                        System.out.println("Introduce el mes de caducidad de la tarjeta de crédito");
                        String mesCaducidad = System.console().readLine();
                        System.out.println("El particular es VIP? (s/n)");
                        boolean esVip = System.console().readLine().toLowerCase().equals("s");
                        ListManager.usuarios.add(new Particular(email, clave, dni, nombre, telefono,esVip, new Tarjeta(LocalDate.of(Integer.parseInt(añoCaducidad),Integer.parseInt(mesCaducidad),1),nombreTarjeta,Long.parseLong(numeroTarjeta))));
                        System.out.println("Particular añadido");
                        break;
                    }
                    System.out.println("Cancelando...");
                    break;
                case "2":
                    System.out.println("Introduce el email del usuario a eliminar");
                    String emailEliminar = System.console().readLine();
                    boolean encontrado = false;
                    for (Usuario e: ListManager.usuarios){
                        if (e.getCorreo().equals(emailEliminar)){
                            encontrado = true;
                            System.out.println("Eliminando "+e.toString());
                            ListManager.usuarios.remove(e);
                            break;
                        }
                    }
                    if (!encontrado){
                        System.out.println("Usuario no encontrado");
                    }
                    break;
                case "3":
                    for (int i=0;i<ListManager.usuarios.size();i++){
                        System.out.println(Integer.toString(i+1)+". "+ListManager.usuarios.get(i).toString());
                    }
                    break;
                case "4":
                    System.out.println("Introduce el email del usuario a modificar");
                    String emailModificar = System.console().readLine();
                    boolean encontradoModificar = false;
                    Usuario usuarioModificar = null;
                    for (Usuario e: ListManager.usuarios){
                        if (e.getCorreo().equals(emailModificar)){
                            encontradoModificar = true;
                            usuarioModificar = e;
                            break;
                        }
                    }
                    if (!encontradoModificar){
                        System.out.println("Usuario no encontrado");
                        break;
                    }
                    String tipoUsuario=usuarioModificar.getClass().getSimpleName();
                    switch (tipoUsuario){
                        case "Administrador":
                            System.out.println("¿Qué quieres modificar?\n"+
                                    "1. Email\n"+
                                    "2. Clave\n"+
                                    "3. Cancelar");
                            String respuestaModificar = System.console().readLine();
                            switch (respuestaModificar){
                                case "1":
                                    System.out.println("Introduce el nuevo email");
                                    String nuevoEmail = System.console().readLine();
                                    usuarioModificar.setCorreo(nuevoEmail);
                                    System.out.println("Email modificado");
                                    break;
                                case "2":
                                    System.out.println("Introduce la nueva clave");
                                    String nuevaClave = System.console().readLine();
                                    usuarioModificar.setClave(nuevaClave);
                                    System.out.println("Clave modificada");
                                    break;
                                case "3":
                                    break;
                                default:
                                    System.out.println("Opción no válida");
                                    break;
                            }
                            break;
                        case "Anfitrion":
                            System.out.println("¿Qué quieres modificar?\n"+
                                    "1. Email\n"+
                                    "2. Clave\n"+
                                    "3. DNI\n"+
                                    "4. Nombre\n"+
                                    "5. Teléfono\n"+
                                    "6. Super anfitrión\n"+
                                    "7. Fecha de registro\n"+
                                    "8. Cancelar");
                            String respuestaModificarAnfitrion = System.console().readLine();
                            switch (respuestaModificarAnfitrion){
                                case "1":
                                    System.out.println("Introduce el nuevo email");
                                    String nuevoEmail = System.console().readLine();
                                    usuarioModificar.setCorreo(nuevoEmail);
                                    System.out.println("Email modificado");
                                    break;
                                case "2":
                                    System.out.println("Introduce la nueva clave");
                                    String nuevaClave = System.console().readLine();
                                    usuarioModificar.setClave(nuevaClave);
                                    System.out.println("Clave modificada");
                                    break;
                                case "3":
                                    System.out.println("Introduce el nuevo DNI");
                                    String nuevoDNI = System.console().readLine();
                                    ((Anfitrion)usuarioModificar).setDni(nuevoDNI);
                                    System.out.println("DNI modificado");
                                    break;
                                case "4":
                                    System.out.println("Introduce el nuevo nombre");
                                    String nuevoNombre = System.console().readLine();
                                    ((Anfitrion)usuarioModificar).setNombre(nuevoNombre);
                                    System.out.println("Nombre modificado");
                                    break;
                                case "5":
                                    System.out.println("Introduce el nuevo teléfono");
                                    String nuevoTelefono = System.console().readLine();
                                    ((Anfitrion)usuarioModificar).setTelefono(nuevoTelefono);
                                    System.out.println("Teléfono modificado");
                                    break;
                                case "6":
                                    System.out.println("¿Es super anfitrión? (s/n)");
                                    boolean superAnfitrion = System.console().readLine().toLowerCase().equals("s");
                                    ((Anfitrion)usuarioModificar).setSuperAnfitrion(superAnfitrion);
                                    System.out.println("Super anfitrión modificado");
                                    break;
                                case "7":
                                    System.out.println("Introduce el año de la fecha de registro");
                                    String año = System.console().readLine();
                                    System.out.println("Introduce el mes de la fecha de registro");
                                    String mes = System.console().readLine();
                                    System.out.println("Introduce el día de la fecha de registro");
                                    String dia = System.console().readLine();
                                    ((Anfitrion)usuarioModificar).setFechaRegistro(LocalDate.of(Integer.parseInt(año),Integer.parseInt(mes),Integer.parseInt(dia)));
                                    System.out.println("Fecha de registro modificada");
                                    break;
                                case "8":
                                    break;
                                default:
                                    System.out.println("Opción no válida");
                                    break;
                            }
                            break;
                        case "Particular":
                            System.out.println("¿Qué quieres modificar?\n"+
                                    "1. Email\n"+
                                    "2. Clave\n"+
                                    "3. DNI\n"+
                                    "4. Nombre\n"+
                                    "5. Teléfono\n"+
                                    "6. VIP\n"+
                                    "7. Tarjeta\n"+
                                    "8. Cancelar");
                            String respuestaModificarParticular = System.console().readLine();
                            switch (respuestaModificarParticular){
                                case "1":
                                    System.out.println("Introduce el nuevo email");
                                    String nuevoEmail = System.console().readLine();
                                    usuarioModificar.setCorreo(nuevoEmail);
                                    System.out.println("Email modificado");
                                    break;
                                case "2":
                                    System.out.println("Introduce la nueva clave");
                                    String nuevaClave = System.console().readLine();
                                    usuarioModificar.setClave(nuevaClave);
                                    System.out.println("Clave modificada");
                                    break;
                                case "3":
                                    System.out.println("Introduce el nuevo DNI");
                                    String nuevoDNI = System.console().readLine();
                                    ((Particular)usuarioModificar).setDni(nuevoDNI);
                                    System.out.println("DNI modificado");
                                    break;
                                case "4":
                                    System.out.println("Introduce el nuevo nombre");
                                    String nuevoNombre = System.console().readLine();
                                    ((Particular)usuarioModificar).setNombre(nuevoNombre);
                                    System.out.println("Nombre modificado");
                                    break;
                                case "5":
                                    System.out.println("Introduce el nuevo teléfono");
                                    String nuevoTelefono = System.console().readLine();
                                    ((Particular)usuarioModificar).setTelefono(nuevoTelefono);
                                    System.out.println("Teléfono modificado");
                                    break;
                                case "6":
                                    System.out.println("¿Es VIP? (s/n)");
                                    boolean vip = System.console().readLine().toLowerCase().equals("s");
                                    ((Particular)usuarioModificar).setVip(vip);
                                    System.out.println("VIP modificado");
                                    break;
                                case "7":
                                    System.out.println("Qué quieres modificar de la tarjeta?\n"+
                                            "1. Nombre\n"+
                                            "2. Número\n"+
                                            "3. Fecha de caducidad\n"+
                                            "4. Cancelar");
                                    String respuestaModificarTarjeta = System.console().readLine();
                                    switch (respuestaModificarTarjeta){
                                        case "1":
                                            System.out.println("Introduce el nuevo nombre de la tarjeta");
                                            String nuevoNombreTarjeta = System.console().readLine();
                                            ((Particular)usuarioModificar).getTarjeta().setNombreTitular(nuevoNombreTarjeta);
                                            System.out.println("Nombre de la tarjeta modificado");
                                            break;
                                        case "2":
                                            System.out.println("Introduce el nuevo número de la tarjeta");
                                            String nuevoNumeroTarjeta = System.console().readLine();
                                            ((Particular)usuarioModificar).getTarjeta().setNumero(Long.parseLong(nuevoNumeroTarjeta));
                                            System.out.println("Número de la tarjeta modificado");
                                            break;
                                        case "3":
                                            System.out.println("Introduce el nuevo año de caducidad de la tarjeta");
                                            String nuevoAñoCaducidad = System.console().readLine();
                                            System.out.println("Introduce el nuevo mes de caducidad de la tarjeta");
                                            String nuevoMesCaducidad = System.console().readLine();
                                            ((Particular)usuarioModificar).getTarjeta().setFechaCaducidad(LocalDate.of(Integer.parseInt(nuevoAñoCaducidad),Integer.parseInt(nuevoMesCaducidad),1));
                                            System.out.println("Fecha de caducidad de la tarjeta modificada");
                                            break;
                                        case "4":
                                            break;
                                        default:
                                            System.out.println("Opción no válida");
                                    }
                                    break;
                                case "8":
                                    break;
                                default:
                                    System.out.println("Opción no válida");
                                    break;
                        }
                    }
                case "5":
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
