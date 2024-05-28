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
     * @param claveString La clave del usuario en formato de texto sin cifrar. Para el inicio de sesión, se utiliza la función hash
 */
    public Usuario(String correo, String claveString){
        this.correo = correo;
        this.clave = claveString.hashCode();
    }

    @Override
    public String toString() {
        return "correo=" + correo+", clave (hash)=" + clave;
    }
    
    //Funciones de registro, edición e inicio de sesión
    /**
     * Borra un usuario de la lista de usuarios gestionada por ListManager.
     * Busca el usuario por correo y lo elimina de la lista.
     * Lanza una excepción si se intenta borrar el único administrador.
     *
     * @param correo El correo del usuario a borrar.
     * @throws IllegalArgumentException Si se intenta borrar el único administrador.
     */
    public static void borrarUsuario(String correo){
        for (int i=0; i<ListManager.getUsuarios().size(); i++)
            if (ListManager.getUsuarios().get(i).getCorreo().equals(correo)){
                if (ListManager.getUsuarios().get(i) instanceof Administrador && ListManager.getUsuarios().stream().filter(u->u instanceof Administrador).count()==1)
                    throw new IllegalArgumentException("No se puede borrar el único administrador\n Crea otro administrador antes de borrar este");{
                }
                ListManager.getUsuarios().remove(i);
                break;
            }
    }
    /**
     * Cambia la contraseña de un usuario.
     * Como la contraseña se almacena como un hash, no puede recuperarse, es necesario cambiarla.
     * Busca el usuario por correo y cambia su contraseña.
     * Valida la nueva contraseña antes de cambiarla.
     *
     * @param correo El correo del usuario.
     * @param nuevaContraseña La nueva contraseña del usuario.
     * @param nuevaContraseña2 La confirmación de la nueva contraseña.
     * @throws IllegalArgumentException Si la nueva contraseña no es válida, es decir si no tiene al menos 8 caracteres o si las contraseñas no coinciden.
     */
    public static void cambiarContraseña(String correo, String nuevaContraseña, String nuevaContraseña2){
        String errores=validarInformacion(nuevaContraseña,nuevaContraseña2);
        if (errores.length()>0)
            throw new IllegalArgumentException(errores);
        for (int i=0; i<ListManager.getUsuarios().size(); i++)
            if (ListManager.getUsuarios().get(i).getCorreo().equals(correo)){
                ListManager.getUsuarios().get(i).setClave(nuevaContraseña);
                break;
            }
    }
    /**
     * Comprueba si un correo ya está en uso, ya que los usuarios se identifican de forma única por su correo.
     * Busca el correo en la lista de usuarios.
     *
     * @param correo El correo a comprobar.
     * @return Verdadero si el correo ya está en uso, falso en caso contrario.
     */
    public static boolean correoRepetido(String correo){
        for (int i=0; i<ListManager.getUsuarios().size(); i++)
            if (ListManager.getUsuarios().get(i).getCorreo().equals(correo))
                return true;
        return false;
    }
    /**
     * Edita la información de un usuario particular.
     * Valida la nueva información antes de hacer los cambios.
     * Lanza una excepción si la nueva información no es válida.
     *
     * @param correoOriginal El correo original del particular.
     * @param correo El nuevo correo del particular.
     * @param dni El nuevo DNI del particular.
     * @param nombre El nuevo nombre del particular.
     * @param telefono El nuevo teléfono del particular.
     * @param esVip Si el particular es VIP.
     * @param tarjeta La nueva tarjeta del particular.
     * @throws IllegalArgumentException Si la nueva información no es válida (ver {@link #validarInformacion(String, String, String, String, String, String, String)}).
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
     * Edita la información de un usuario anfitrión.
     * Valida la nueva información antes de hacer los cambios.
     * Lanza una excepción si la nueva información no es válida.
     *
     * @param correoOriginal El correo original del anfitrión.
     * @param correo El nuevo correo del anfitrión.
     * @param dni El nuevo DNI del anfitrión.
     * @param nombre El nuevo nombre del anfitrión.
     * @param telefono El nuevo teléfono del anfitrión.
     * @throws IllegalArgumentException Si la nueva información no es válida (ver {@link #validarInformacion(String, String, String, String, String, String, String)}).
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
     * Lanza una excepción si el nuevo correo no es válido.
     *
     * @param correoOriginal El correo original del administrador.
     * @param correo El nuevo correo del administrador.
     * @throws IllegalArgumentException Si el nuevo correo no es válido (ver {@link #validarInformacion(String)}).
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
     * Inicia sesión para un usuario.
     * Busca el usuario por correo y clave en la lista de usuarios.
     * Lanza una excepción si el usuario no se encuentra o si la clave es incorrecta.
     *
     * @param correo El correo del usuario.
     * @param clave El hash de la clave del usuario.
     * @return El usuario que ha iniciado sesión.
     * @throws IllegalArgumentException Si el usuario no se encuentra o si la clave es incorrecta.
     */
    public static Usuario iniciarSesion(String correo, int clave) throws IllegalArgumentException{
        for (int i=0; i<ListManager.getUsuarios().size(); i++){
            if (ListManager.getUsuarios().get(i).getCorreo().equals(correo) && ListManager.getUsuarios().get(i).getClave() == clave){
                return ListManager.getUsuarios().get(i);
            }
                
        }
        throw new IllegalArgumentException("Usuario no encontrado o contraseña incorrecta");
    }
    /**
     * Registra un nuevo usuario anfitrión.
     * Valida la información del usuario antes de hacer el registro.
     * Lanza una excepción si la información del usuario no es válida.
     *
     * @param correo El correo del anfitrión.
     * @param clave La clave del anfitrión.
     * @param clave2 La confirmación de la clave del anfitrión.
     * @param dni El DNI del anfitrión.
     * @param nombre El nombre del anfitrión.
     * @param telefono El teléfono del anfitrión.
     * @throws IllegalArgumentException Si la información del anfitrión no es válida (ver {@link #validarInformacion(String, String, String, String, String, String, String)}
     */
    public static void registrar(String correo, String clave, String clave2, String dni, String nombre, String telefono) throws IllegalArgumentException{
        if (Usuario.validarInformacion(null,correo, clave, clave2, dni, nombre, telefono).length()>0)
            throw new IllegalArgumentException(Usuario.validarInformacion(null,correo, clave, clave, dni, nombre, telefono));
        ListManager.getUsuarios().add(new Anfitrion(correo, clave, dni, nombre, telefono));
        reordenarUsuarios();
    }
    /**
     * Registra un nuevo usuario particular.
     * Valida la información del usuario y de la tarjeta antes de hacer el registro.
     * Lanza una excepción si la información del usuario o de la tarjeta no es válida.
     *
     * @param correo El correo del particular.
     * @param clave La clave del particular.
     * @param clave2 La confirmación de la clave del particular.
     * @param dni El DNI del particular.
     * @param nombre El nombre del particular.
     * @param telefono El teléfono del particular.
     * @param esVip Si el particular es VIP.
     * @param tarjeta La tarjeta del particular.
     * @throws IllegalArgumentException Si la información del particular o de la tarjeta no es válida (ver {@link #validarInformacion(String, String, String, String, String, String, String)} y {@link Tarjeta#validarInformacion(String, String, int, int)}
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
     * Valida la información del usuario antes de hacer el registro.
     * Lanza una excepción si la información del usuario no es válida.
     *
     * @param correo El correo del administrador.
     * @param clave La clave del administrador.
     * @param clave2 La confirmación de la clave del administrador.
     * @throws IllegalArgumentException Si la información del administrador no es válida.
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
     * Dentro de cada grupo, los usuarios se ordenan alfabéticamente por correo.
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
     * Valida la información de un usuario.
     * Comprueba si los campos no están vacíos, si el correo no está repetido y cumple con el formato, si las claves coinciden y tienen al menos 8 caracteres,
     * si el teléfono solo contiene números o un + al principio, y si el DNI es válido.
     *
     * @param correoOriginal El correo original del usuario.
     * @param correo El nuevo correo del usuario.
     * @param clave La clave del usuario.
     * @param clave2 La confirmación de la clave del usuario.
     * @param dni El DNI del usuario.
     * @param nombre El nombre del usuario.
     * @param telefono El teléfono del usuario.
     * @return Una cadena con los errores si la información no es válida, o una cadena vacía si lo es.
     */
    public static String validarInformacion(String correoOriginal,String correo, String clave, String clave2,String dni, String nombre, String telefono){
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
    /**
     * Valida la información de los datos exclusivos de un usuario particular (tarjeta y código VIP).
     * Comprueba si la información de la tarjeta es válida y si el código VIP es correcto.
     *
     * @param nombreTarjeta El nombre en la tarjeta.
     * @param numeroTarjeta El número de la tarjeta.
     * @param mes El mes de expiración de la tarjeta.
     * @param año El año de expiración de la tarjeta.
     * @param vip El código VIP.
     * @return Una cadena con los errores si la información no es válida, o una cadena vacía si lo es.
     */
    public static String validarInformacion(String nombreTarjeta, String numeroTarjeta, int mes, int año, String vip){
        String errores="";
        errores+=Tarjeta.validarInformacion(nombreTarjeta, numeroTarjeta, mes, año);
        if (!vip.isEmpty() && !vip.equals(claveVip))
            errores+="Código VIP inválido. Si no eres VIP, no pongas nada\n";
        return errores;
    }
    /**
     * Comprueba si el correo es válido, es decir, si no está repetido y si cumple con el formato de correo electrónico.
     *
     * @param correo El correo del usuario.
     * @return Una cadena vacía si la información es válida, o una cadena con los errores si no lo es.
     */
    public static String validarInformacion (String correo){
        String errores="";
        if (correoRepetido(correo))
            errores+="Correo ya registrado\n";
        if (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))
            errores+="Correo inválido\n";
        return errores;
    }
    /**
     * Comprueba si la clave y su confirmación son iguales, y si la clave tiene al menos 8 caracteres.
     *
     * @param clave La clave del usuario.
     * @param clave2 La confirmación de la clave del usuario.
     * @return Una cadena vacía si la información es válida, o una cadena con los errores si no lo es.
     */
    public static String validarInformacion (String clave, String clave2){
        String errores="";
        if (!clave.equals(clave2))
            errores+="Las contraseñas no coinciden\n";
        else if (clave.length()<8)
            errores+="La contraseña debe tener al menos 8 caracteres\n";
        return errores;
    }





        
    
}//end Usuario