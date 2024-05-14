package poo.pl2;

public class LoginManager {
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
        if (correoRepetido(correo))
            throw new IllegalArgumentException("Correo ya registrado");
        ListManager.usuarios.add(new Anfitrion(correo, clave, dni, nombre, telefono));
    }
    public void registrar(String correo, String clave, String dni, String nombre, String telefono, boolean esVip, Tarjeta tarjeta) throws IllegalArgumentException{
        if (correoRepetido(correo))
            throw new IllegalArgumentException("Correo ya registrado");
        ListManager.usuarios.add(new Particular(correo, clave, dni, nombre, telefono, esVip, tarjeta));
    }
    public boolean correoRepetido(String correo){
        for (int i=0; i<ListManager.usuarios.size(); i++)
            if (ListManager.usuarios.get(i).getCorreo().equals(correo))
                return true;
        return false;
    }
}
