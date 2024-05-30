package poo.pl2;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author perez
 * @version 1.0
 * 
 */
public class Usuario implements java.io.Serializable {
    private static String claveVip="SoyVIP";
    private int clave;
    private String correo;
    public static enum tipoUsuario {ADMIN, ANFITRION, CLIENTE};
    
    public static String getClaveVip() {
        return claveVip;
    }
    public int getClave() {
        return clave;
    }
    public void setClave(String claveString) {
        this.clave = claveString.hashCode();
    }
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    /**
     * Constructor para la clase Usuario.
     * Inicializa un nuevo usuario con el correo y la clave proporcionados.
     * La clave se almacena como un hash para mayor seguridad.
     *
     * @param correo El correo del usuario. Dos usuarios no pueden tener el mismo correo.
     * @param claveString La clave del usuario en formato de texto sin cifrar. Para el inicio de sesion, se utiliza la funcion hash
 */
    public Usuario(String correo, String claveString){
        this.correo = correo;
        this.clave = claveString.hashCode();
    }

    @Override
    public String toString() {
        return "correo=" + correo+", clave (hash)=" + clave;
    }
    
    //Funciones de registro, edicion e inicio de sesion
    /**
     * Borra un usuario de la lista de usuarios gestionada por ListManager.
     * Busca el usuario por correo y lo elimina de la lista.
     * Lanza una excepcion si se intenta borrar el unico administrador.
     *
     * @param correo El correo del usuario a borrar.
     * @throws IllegalArgumentException Si se intenta borrar el unico administrador.
     */
    public static void borrarUsuario(String correo){
        for (int i=0; i<ListManager.getUsuarios().size(); i++)
            if (ListManager.getUsuarios().get(i).getCorreo().equals(correo)){
                if (ListManager.getUsuarios().get(i) instanceof Administrador && ListManager.getUsuarios().stream().filter(u->u instanceof Administrador).count()==1)
                    throw new IllegalArgumentException("No se puede borrar el unico administrador\n Crea otro administrador antes de borrar este");{
                }
                ListManager.getUsuarios().remove(i);
                break;
            }
    }
    /**
     * Cambia la contrasenna de un usuario.
     * Como la contrasenna se almacena como un hash, no puede recuperarse, es necesario cambiarla.
     * Busca el usuario por correo y cambia su contrasenna.
     * Valida la nueva contrasenna antes de cambiarla.
     *
     * @param correo El correo del usuario.
     * @param nuevaContrasenna La nueva contrasenna del usuario.
     * @param nuevaContrasenna2 La confirmacion de la nueva contrasenna.
     * @throws IllegalArgumentException Si la nueva contrasenna no es valida, es decir si no tiene al menos 8 caracteres o si las contrasennas no coinciden.
     */
    public static void cambiarContrasenna(String correo, String nuevaContrasenna, String nuevaContrasenna2){
        String errores=validarInformacion(nuevaContrasenna,nuevaContrasenna2);
        if (errores.length()>0)
            throw new IllegalArgumentException(errores);
        for (int i=0; i<ListManager.getUsuarios().size(); i++)
            if (ListManager.getUsuarios().get(i).getCorreo().equals(correo)){
                ListManager.getUsuarios().get(i).setClave(nuevaContrasenna);
                break;
            }
    }
    /**
     * Comprueba si un correo ya esta en uso, ya que los usuarios se identifican de forma unica por su correo.
     * Busca el correo en la lista de usuarios.
     *
     * @param correo El correo a comprobar.
     * @return Verdadero si el correo ya esta en uso, falso en caso contrario.
     */
    public static boolean correoRepetido(String correo){
        for (int i=0; i<ListManager.getUsuarios().size(); i++)
            if (ListManager.getUsuarios().get(i).getCorreo().equals(correo))
                return true;
        return false;
    }
    /**
     * Edita la informacion de un usuario particular.
     * Valida la nueva informacion antes de hacer los cambios.
     * Lanza una excepcion si la nueva informacion no es valida.
     *
     * @param correoOriginal El correo original del particular.
     * @param correo El nuevo correo del particular.
     * @param dni El nuevo DNI del particular.
     * @param nombre El nuevo nombre del particular.
     * @param telefono El nuevo telefono del particular.
     * @param esVip Si el particular es VIP.
     * @param tarjeta La nueva tarjeta del particular.
     * @throws IllegalArgumentException Si la nueva informacion no es valida (ver {@link #validarInformacion(String, String, String, String, String, String, String)}).
     */
    public static void editarUsuario(String correoOriginal,String correo, String dni, String nombre, String telefono, boolean esVip, Tarjeta tarjeta){ //Para particulares
        String errores=validarInformacion(correoOriginal,correo, "AAAA1234", "AAAA1234", dni, nombre, telefono)+validarInformacion(tarjeta.getNombreTitular(), Long.toString(tarjeta.getNumero()),tarjeta.getFechaCaducidad().getMonthValue(), tarjeta.getFechaCaducidad().getYear(), new String());
        if (errores.length()>0)
            throw new IllegalArgumentException(errores);
        for (int i=0; i<ListManager.getUsuarios().size(); i++)
            if (ListManager.getUsuarios().get(i).getCorreo().equals(correoOriginal)){
                ((Particular)ListManager.getUsuarios().get(i)).setCorreo(correo);
                ((Particular)ListManager.getUsuarios().get(i)).setDni(dni);
                ((Particular)ListManager.getUsuarios().get(i)).setNombre(nombre);
                ((Particular)ListManager.getUsuarios().get(i)).setTelefono(telefono);
                ((Particular)ListManager.getUsuarios().get(i)).setVip(esVip);
                ((Particular)ListManager.getUsuarios().get(i)).setTarjeta(tarjeta);
                break;
            }
    }
    /**
     * Edita la informacion de un usuario anfitrion.
     * Valida la nueva informacion antes de hacer los cambios.
     * Lanza una excepcion si la nueva informacion no es valida.
     *
     * @param correoOriginal El correo original del anfitrion.
     * @param correo El nuevo correo del anfitrion.
     * @param dni El nuevo DNI del anfitrion.
     * @param nombre El nuevo nombre del anfitrion.
     * @param telefono El nuevo telefono del anfitrion.
     * @throws IllegalArgumentException Si la nueva informacion no es valida (ver {@link #validarInformacion(String, String, String, String, String, String, String)}).
     */
    public static void editarUsuario(String correoOriginal,String correo, String dni, String nombre, String telefono){ //Para anfitriones
        String errores=validarInformacion(correoOriginal,correo, "AAAA1234", "AAAA1234", dni, nombre, telefono);
        if (errores.length()>0)
            throw new IllegalArgumentException(errores);
        for (int i=0; i<ListManager.getUsuarios().size(); i++)
            if (ListManager.getUsuarios().get(i).getCorreo().equals(correoOriginal)){
                ((Anfitrion)ListManager.getUsuarios().get(i)).setCorreo(correo);
                ((Anfitrion)ListManager.getUsuarios().get(i)).setDni(dni);
                ((Anfitrion)ListManager.getUsuarios().get(i)).setNombre(nombre);
                ((Anfitrion)ListManager.getUsuarios().get(i)).setTelefono(telefono);
                break;
            }
    }
    /**
     * Edita el correo de un usuario administrador.
     * Valida el nuevo correo antes de hacer los cambios.
     * Lanza una excepcion si el nuevo correo no es valido.
     *
     * @param correoOriginal El correo original del administrador.
     * @param correo El nuevo correo del administrador.
     * @throws IllegalArgumentException Si el nuevo correo no es valido (ver {@link #validarInformacion(String)}).
     */
    public static void editarUsuario (String correoOriginal,String correo){
        String errores=validarInformacion(correo);
        if (errores.length()>0)
            throw new IllegalArgumentException(errores);
        for (int i=0; i<ListManager.getUsuarios().size(); i++)
            if (ListManager.getUsuarios().get(i).getCorreo().equals(correoOriginal)){
                ((Administrador)ListManager.getUsuarios().get(i)).setCorreo(correo);
                break;
            }
    }
    /**
     * Inicia sesion para un usuario.
     * Busca el usuario por correo y clave en la lista de usuarios.
     * Lanza una excepcion si el usuario no se encuentra o si la clave es incorrecta.
     *
     * @param correo El correo del usuario.
     * @param clave El hash de la clave del usuario.
     * @return El usuario que ha iniciado sesion.
     * @throws IllegalArgumentException Si el usuario no se encuentra o si la clave es incorrecta.
     */
    public static Usuario iniciarSesion(String correo, int clave) throws IllegalArgumentException{
        for (int i=0; i<ListManager.getUsuarios().size(); i++){
            if (ListManager.getUsuarios().get(i).getCorreo().equals(correo) && ListManager.getUsuarios().get(i).getClave() == clave){
                return ListManager.getUsuarios().get(i);
            }
                
        }
        throw new IllegalArgumentException("Usuario no encontrado o contrasenna incorrecta");
    }
    /**
     * Registra un nuevo usuario anfitrion.
     * Valida la informacion del usuario antes de hacer el registro.
     * Lanza una excepcion si la informacion del usuario no es valida.
     *
     * @param correo El correo del anfitrion.
     * @param clave La clave del anfitrion.
     * @param clave2 La confirmacion de la clave del anfitrion.
     * @param dni El DNI del anfitrion.
     * @param nombre El nombre del anfitrion.
     * @param telefono El telefono del anfitrion.
     * @throws IllegalArgumentException Si la informacion del anfitrion no es valida (ver {@link #validarInformacion(String, String, String, String, String, String, String)}
     */
    public static void registrar(String correo, String clave, String clave2, String dni, String nombre, String telefono) throws IllegalArgumentException{
        if (Usuario.validarInformacion(null,correo, clave, clave2, dni, nombre, telefono).length()>0)
            throw new IllegalArgumentException(Usuario.validarInformacion(null,correo, clave, clave, dni, nombre, telefono));
        ListManager.getUsuarios().add(new Anfitrion(correo, clave, dni, nombre, telefono));
        reordenarUsuarios();
    }
    /**
     * Registra un nuevo usuario particular.
     * Valida la informacion del usuario y de la tarjeta antes de hacer el registro.
     * Lanza una excepcion si la informacion del usuario o de la tarjeta no es valida.
     *
     * @param correo El correo del particular.
     * @param clave La clave del particular.
     * @param clave2 La confirmacion de la clave del particular.
     * @param dni El DNI del particular.
     * @param nombre El nombre del particular.
     * @param telefono El telefono del particular.
     * @param esVip Si el particular es VIP.
     * @param tarjeta La tarjeta del particular.
     * @throws IllegalArgumentException Si la informacion del particular o de la tarjeta no es valida (ver {@link #validarInformacion(String, String, String, String, String, String, String)} y {@link Tarjeta#validarInformacion(String, String, int, int)}
     */
    public static void registrar(String correo, String clave, String clave2, String dni, String nombre, String telefono, boolean esVip, Tarjeta tarjeta) throws IllegalArgumentException{
        String errores=validarInformacion(null,correo, clave, clave2, dni, nombre, telefono)+ validarInformacion(tarjeta.getNombreTitular(), Long.toString(tarjeta.getNumero()),tarjeta.getFechaCaducidad().getMonthValue(), tarjeta.getFechaCaducidad().getYear(), new String());
        if (errores.length()>0)
            throw new IllegalArgumentException(errores);
        ListManager.getUsuarios().add(new Particular(correo, clave, dni, nombre, telefono, esVip, tarjeta));
        reordenarUsuarios();
    }
    /**
     * Registra un nuevo usuario administrador.
     * Valida la informacion del usuario antes de hacer el registro.
     * Lanza una excepcion si la informacion del usuario no es valida.
     *
     * @param correo El correo del administrador.
     * @param clave La clave del administrador.
     * @param clave2 La confirmacion de la clave del administrador.
     * @throws IllegalArgumentException Si la informacion del administrador no es valida.
     */
    public static void registrar (String correo, String clave, String clave2){
        String errores=validarInformacion(clave, clave2)+validarInformacion(correo);
        if (errores.length()>0)
            throw new IllegalArgumentException(errores);
        ListManager.getUsuarios().add(new Administrador(correo, clave));
        reordenarUsuarios();
    }
    /**
     * Reordena la lista de usuarios de ListManager.
     * Los administradores se colocan primero, seguidos por los anfitriones y luego los usuarios particulares.
     * Dentro de cada grupo, los usuarios se ordenan alfabeticamente por correo.
     */
    public static void reordenarUsuarios(){
        Collections.sort(ListManager.getUsuarios(), new Comparator<Usuario>() {
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
    /**
     * Valida la informacion de un usuario.
     * Comprueba si los campos no estan vacios, si el correo no esta repetido y cumple con el formato, si las claves coinciden y tienen al menos 8 caracteres,
     * si el telefono solo contiene numeros o un + al principio, y si el DNI es valido.
     *
     * @param correoOriginal El correo original del usuario.
     * @param correo El nuevo correo del usuario.
     * @param clave La clave del usuario.
     * @param clave2 La confirmacion de la clave del usuario.
     * @param dni El DNI del usuario.
     * @param nombre El nombre del usuario.
     * @param telefono El telefono del usuario.
     * @return Una cadena con los errores si la informacion no es valida, o una cadena vacia si lo es.
     */
    public static String validarInformacion(String correoOriginal,String correo, String clave, String clave2,String dni, String nombre, String telefono){
        String errores="";
        if (correo.isEmpty() || clave.length()==0 || clave.length()==0 || nombre.isEmpty() || dni.isEmpty() || telefono.isEmpty())
            errores+="Es necesario rellenar todos los campos\n";
        if (correoRepetido(correo) && !correo.equals(correoOriginal))
            errores+="Correo ya registrado\n";
        if (!clave.equals(clave2))
            errores+="Las contrasennas no coinciden\n";
        else if (clave.length()<8)
            errores+="La contrasenna debe tener al menos 8 caracteres\n";
        if (!telefono.matches("\\+?[0-9]*"))
            errores+="El telefono solo puede contener numeros o un + al principio\n";
        if (!dni.matches("[0-9]{8}[A-Za-z]"))//|| "TRWAGMYFPDXBNJZSQVHLCKE".charAt(Integer.parseInt(dniField.getText().substring(0, 8))%23)!=dniField.getText().charAt(8))
            errores+="DNI invalido\n";
        if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
            errores+="Correo invalido\n";
        return errores;
    }
    /**
     * Valida la informacion de los datos exclusivos de un usuario particular (tarjeta y codigo VIP).
     * Comprueba si la informacion de la tarjeta es valida y si el codigo VIP es correcto.
     *
     * @param nombreTarjeta El nombre en la tarjeta.
     * @param numeroTarjeta El numero de la tarjeta.
     * @param mes El mes de expiracion de la tarjeta.
     * @param anno El anno de expiracion de la tarjeta.
     * @param vip El codigo VIP.
     * @return Una cadena con los errores si la informacion no es valida, o una cadena vacia si lo es.
     */
    public static String validarInformacion(String nombreTarjeta, String numeroTarjeta, int mes, int anno, String vip){
        String errores="";
        errores+=Tarjeta.validarInformacion(nombreTarjeta, numeroTarjeta, mes, anno);
        if (!vip.isEmpty() && !vip.equals(claveVip))
            errores+="Codigo VIP invalido. Si no eres VIP, no pongas nada\n";
        return errores;
    }
    /**
     * Comprueba si el correo es valido, es decir, si no esta repetido y si cumple con el formato de correo electronico.
     *
     * @param correo El correo del usuario.
     * @return Una cadena vacia si la informacion es valida, o una cadena con los errores si no lo es.
     */
    public static String validarInformacion (String correo){
        String errores="";
        if (correoRepetido(correo))
            errores+="Correo ya registrado\n";
        if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
            errores+="Correo invalido\n";
        return errores;
    }
    /**
     * Comprueba si la clave y su confirmacion son iguales, y si la clave tiene al menos 8 caracteres.
     *
     * @param clave La clave del usuario.
     * @param clave2 La confirmacion de la clave del usuario.
     * @return Una cadena vacia si la informacion es valida, o una cadena con los errores si no lo es.
     */
    public static String validarInformacion (String clave, String clave2){
        String errores="";
        if (!clave.equals(clave2))
            errores+="Las contrasennas no coinciden\n";
        else if (clave.length()<8)
            errores+="La contrasenna debe tener al menos 8 caracteres\n";
        return errores;
    }





        
    
}//end Usuario