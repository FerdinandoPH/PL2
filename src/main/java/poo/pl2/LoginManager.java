package poo.pl2;

import java.time.LocalDate;

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
    public String validarRegistro(String correo, String clave, String clave2,String dni, String nombre, String telefono){
        String errores="";
        if (correo.isEmpty() || clave.length()==0 || clave.length()==0 || nombre.isEmpty() || dni.isEmpty() || telefono.isEmpty())
            errores+="Es necesario rellenar todos los campos\n";
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
    public String validarRegistro(String nombreTarjeta, String numeroTarjeta, int mes, int año, String vip){
        String errores="";
        if (numeroTarjeta.isEmpty() || nombreTarjeta.isEmpty())
            errores+="Es necesario rellenar todos los campos que tengan un asterisco\n";
        if (!numeroTarjeta.matches("[0-9]{16}"))
            errores+="Número de tarjeta inválido\n";
        if (año<LocalDate.now().getYear() || (año==LocalDate.now().getYear() && mes<LocalDate.now().getMonthValue()))
            errores+="Fecha de caducidad inválida\n";
        if (!vip.isEmpty() && !vip.equals("SoyVIP"))
            errores+="Código VIP inválido. Si no eres VIP, no pongas nada\n";
        return errores;
    }
}
