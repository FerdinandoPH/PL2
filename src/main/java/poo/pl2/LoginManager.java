package poo.pl2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;

public class LoginManager {
    static String claveVip="SoyVIP";
    public Usuario iniciarSesion(String correo, int clave) throws IllegalArgumentException{
        for (int i=0; i<ListManager.usuarios.size(); i++){
            System.out.println("Comparando " + ListManager.usuarios.get(i).getCorreo() + " con " + correo + " y " + ListManager.usuarios.get(i).getClave() + " con " + clave);
            if (ListManager.usuarios.get(i).getCorreo().equals(correo) && ListManager.usuarios.get(i).getClave() == clave){
                System.out.println("Coincide con "+ListManager.usuarios.get(i).toString());
                return ListManager.usuarios.get(i);
            }
                
        }
        throw new IllegalArgumentException("Usuario no encontrado o contraseña incorrecta");
    }
    public void registrar(String correo, String clave, String dni, String nombre, String telefono) throws IllegalArgumentException{
        if (validarInformacion(null,correo, clave, clave, dni, nombre, telefono).length()>0)
            throw new IllegalArgumentException(validarInformacion(null,correo, clave, clave, dni, nombre, telefono));
        ListManager.usuarios.add(new Anfitrion(correo, clave, dni, nombre, telefono));
        Collections.sort(ListManager.usuarios, new Comparator<Usuario>() {
            @Override
            public int compare(Usuario u1, Usuario u2) {
                if (u1 instanceof Administrador && u2 instanceof Administrador) {
                    return u1.getCorreo().compareTo(u2.getCorreo());
                } else if (u1 instanceof Administrador) {
                    return -1;
                } else if (u2 instanceof Administrador) {
                    return 1;
                } else if (u1 instanceof Anfitrion && u2 instanceof Anfitrion) {
                    return u1.getCorreo().compareTo(u2.getCorreo());
                } else if (u1 instanceof Anfitrion) {
                    return -1;
                } else if (u2 instanceof Anfitrion) {
                    return 1;
                } else {
                    return u1.getCorreo().compareTo(u2.getCorreo());
                }
            }
        });
    }
    public void registrar(String correo, String clave, String dni, String nombre, String telefono, boolean esVip, Tarjeta tarjeta) throws IllegalArgumentException{
        String errores=validarInformacion(null,correo, clave, clave, dni, nombre, telefono)+validarInformacion(tarjeta.getNombreTitular(), Long.toString(tarjeta.getNumero()),tarjeta.getFechaCaducidad().getMonthValue(), tarjeta.getFechaCaducidad().getYear(), new String());
        if (errores.length()>0)
            throw new IllegalArgumentException(errores);
        ListManager.usuarios.add(new Particular(correo, clave, dni, nombre, telefono, esVip, tarjeta));
        Collections.sort(ListManager.usuarios, new Comparator<Usuario>() {
            @Override
            public int compare(Usuario u1, Usuario u2) {
                if (u1 instanceof Administrador && u2 instanceof Administrador) {
                    return u1.getCorreo().compareTo(u2.getCorreo());
                } else if (u1 instanceof Administrador) {
                    return -1;
                } else if (u2 instanceof Administrador) {
                    return 1;
                } else if (u1 instanceof Anfitrion && u2 instanceof Anfitrion) {
                    return u1.getCorreo().compareTo(u2.getCorreo());
                } else if (u1 instanceof Anfitrion) {
                    return -1;
                } else if (u2 instanceof Anfitrion) {
                    return 1;
                } else {
                    return u1.getCorreo().compareTo(u2.getCorreo());
                }
            }
        });
    }
    public boolean correoRepetido(String correo){
        for (int i=0; i<ListManager.usuarios.size(); i++)
            if (ListManager.usuarios.get(i).getCorreo().equals(correo))
                return true;
        return false;
    }
    public String validarInformacion(String correoOriginal,String correo, String clave, String clave2,String dni, String nombre, String telefono){
        String errores="";
        if (correo.isEmpty() || clave.length()==0 || clave.length()==0 || nombre.isEmpty() || dni.isEmpty() || telefono.isEmpty())
            errores+="Es necesario rellenar todos los campos\n";
        if (correoRepetido(correo) && !correo.equals(correoOriginal))
            errores+="Correo ya registrado\n";
        if (!clave.equals(clave2))
            errores+="Las contraseñas no coinciden\n";
        else if (clave.length()<8)
            errores+="La contraseña debe tener al menos 8 caracteres\n";
        if (!telefono.matches("\\+?[0-9]*"))
            errores+="El teléfono solo puede contener números o un + al principio\n";
        if (!dni.matches("[0-9]{8}[A-Za-z]"))//|| "TRWAGMYFPDXBNJZSQVHLCKE".charAt(Integer.parseInt(dniField.getText().substring(0, 8))%23)!=dniField.getText().charAt(8))
            errores+="DNI inválido\n";
        if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
            errores+="Correo inválido\n";
        return errores;
    }
    public String validarInformacion(String nombreTarjeta, String numeroTarjeta, int mes, int año, String vip){
        String errores="";
        if (numeroTarjeta.isEmpty() || nombreTarjeta.isEmpty())
            errores+="Es necesario rellenar todos los campos que tengan un asterisco\n";
        if (!numeroTarjeta.matches("[0-9]{16}"))
            errores+="Número de tarjeta inválido\n";
        if (año<LocalDate.now().getYear() || (año==LocalDate.now().getYear() && mes<LocalDate.now().getMonthValue() || mes>12 || mes<1))
            errores+="Fecha de caducidad inválida\n";
        if (!vip.isEmpty() && !vip.equals(claveVip))
            errores+="Código VIP inválido. Si no eres VIP, no pongas nada\n";
        return errores;
    }
    public String validarInformacion (String correo){
        String errores="";
        if (correoRepetido(correo))
            errores+="Correo ya registrado\n";
        if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
            errores+="Correo inválido\n";
        return errores;
    }
    public String validarInformacion (String clave, String clave2){
        String errores="";
        if (!clave.equals(clave2))
            errores+="Las contraseñas no coinciden\n";
        else if (clave.length()<8)
            errores+="La contraseña debe tener al menos 8 caracteres\n";
        return errores;
    }
    public void editarUsuario(String correoOriginal,String correo, String dni, String nombre, String telefono, boolean esVip, Tarjeta tarjeta){ //Para particulares
        String errores=validarInformacion(correoOriginal,correo, "AAAA1234", "AAAA1234", dni, nombre, telefono)+validarInformacion(tarjeta.getNombreTitular(), Long.toString(tarjeta.getNumero()),tarjeta.getFechaCaducidad().getMonthValue(), tarjeta.getFechaCaducidad().getYear(), new String());
        if (errores.length()>0)
            throw new IllegalArgumentException(errores);
        for (int i=0; i<ListManager.usuarios.size(); i++)
            if (ListManager.usuarios.get(i).getCorreo().equals(correoOriginal)){
                ((Particular)ListManager.usuarios.get(i)).setCorreo(correo);
                ((Particular)ListManager.usuarios.get(i)).setDni(dni);
                ((Particular)ListManager.usuarios.get(i)).setNombre(nombre);
                ((Particular)ListManager.usuarios.get(i)).setTelefono(telefono);
                ((Particular)ListManager.usuarios.get(i)).setVip(esVip);
                ((Particular)ListManager.usuarios.get(i)).setTarjeta(tarjeta);
                break;
            }
    }
    public void editarUsuario(String correoOriginal,String correo, String dni, String nombre, String telefono, boolean esSuperAnfitrion){ //Para anfitriones
        String errores=validarInformacion(correoOriginal,correo, "AAAA1234", "AAAA1234", dni, nombre, telefono);
        if (errores.length()>0)
            throw new IllegalArgumentException(errores);
        for (int i=0; i<ListManager.usuarios.size(); i++)
            if (ListManager.usuarios.get(i).getCorreo().equals(correoOriginal)){
                ((Anfitrion)ListManager.usuarios.get(i)).setCorreo(correo);
                ((Anfitrion)ListManager.usuarios.get(i)).setDni(dni);
                ((Anfitrion)ListManager.usuarios.get(i)).setNombre(nombre);
                ((Anfitrion)ListManager.usuarios.get(i)).setTelefono(telefono);
                ((Anfitrion)ListManager.usuarios.get(i)).setSuperAnfitrion(esSuperAnfitrion);
                break;
            }
    }
    public void editarUsuario (String correoOriginal,String correo){
        String errores=validarInformacion(correo);
        if (errores.length()>0)
            throw new IllegalArgumentException(errores);
        for (int i=0; i<ListManager.usuarios.size(); i++)
            if (ListManager.usuarios.get(i).getCorreo().equals(correoOriginal)){
                ((Administrador)ListManager.usuarios.get(i)).setCorreo(correo);
                break;
            }
    }
    public void cambiarContraseña(String correo, String nuevaContraseña, String nuevaContraseña2){
        String errores=validarInformacion(nuevaContraseña,nuevaContraseña2);
        if (errores.length()>0)
            throw new IllegalArgumentException(errores);
        for (int i=0; i<ListManager.usuarios.size(); i++)
            if (ListManager.usuarios.get(i).getCorreo().equals(correo)){
                ListManager.usuarios.get(i).setClave(nuevaContraseña);
                break;
            }
    }

}
