package poo.pl2;

public class LoginManager {
    public int iniciarSesion(String correo, int clave){
        for (int i=0; i<ListManager.usuarios.size(); i++)
            if (ListManager.usuarios.get(i).getCorreo().equals(correo) && ListManager.usuarios.get(i).getClave() == clave)
                return ListManager.usuarios.get(i).getClass().getSimpleName().equals("Administrador") ? 0 : ListManager.usuarios.get(i).getClass().getSimpleName().equals("Anfitrion")? 1 : 2;
        return -1;
    }
    public void registrar(String correo, int clave){
        ListManager.usuarios.add(new Usuario(correo, clave));
    }
}
