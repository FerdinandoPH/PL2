package poo.pl2;

public class LoginManager {
    public Usuario iniciarSesion(String correo, int clave) throws IllegalArgumentException{
        for (int i=0; i<ListManager.usuarios.size(); i++)
            if (ListManager.usuarios.get(i).getCorreo().equals(correo) && ListManager.usuarios.get(i).getClave() == clave)
                return ListManager.usuarios.get(i);
        throw new IllegalArgumentException("Usuario no encontrado o contraseña incorrecta");
    }
    public void registrar(String correo, String clave){
        ListManager.usuarios.add(new Usuario(correo, clave));
    }
}
