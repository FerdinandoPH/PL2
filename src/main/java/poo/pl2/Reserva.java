package poo.pl2;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author perez
 * @version 1.0
 * 
 */
public class Reserva implements java.io.Serializable{
    private LocalDate fechaReserva;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private double importe;
    private Inmueble inmueble;
    private Particular particular;
    private int id;
    private boolean yaResennado=false;
    //region gettersYsetters
        
        public LocalDate getFechaReserva() {
            return fechaReserva;
        }
        public void setFechaReserva(LocalDate fechaReserva) {
            this.fechaReserva = fechaReserva;
        }
        public LocalDate getFechaEntrada() {
            return fechaEntrada;
        }
        public void setFechaEntrada(LocalDate fechaEntrada) {
            this.fechaEntrada = fechaEntrada;
        }
        public LocalDate getFechaSalida() {
            return fechaSalida;
        }
        public void setFechaSalida(LocalDate fechaSalida) {
            this.fechaSalida = fechaSalida;
        }
        public double getImporte() {
            return importe;
        }
        public void setImporte(double importe) {
            this.importe = importe;
        }
        public Inmueble getInmueble() {
            return inmueble;
        }
        public void setInmueble(Inmueble inmueble) {
            this.inmueble = inmueble;
        }
        public Particular getParticular() {
            return particular;
        }
        public void setParticular(Particular particular) {
            this.particular = particular;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public boolean isYaResennado() {
            return yaResennado;
        }
        public void setYaResennado(boolean yaReservado) {
            this.yaResennado = yaReservado;
        }
    //endregion

    /**
     * Constructor para la clase Reserva.
     * Inicializa una nueva reserva con los detalles proporcionados.
     * El importe se calcula en base a la duracion de la estancia, el precio por noche del inmueble y si el particular es VIP.
     *
     * @param fechaReserva La fecha en que se hizo la reserva.
     * @param fechaEntrada La fecha de inicio de la reserva.
     * @param fechaSalida La fecha de salida de la reserva. Ese dia ya es reservable por otro cliente.
     * @param inmueble El inmueble reservado.
     * @param particular El particular que hace la reserva.
     * @param id El ID interno de la reserva.
     */
    public Reserva(LocalDate fechaReserva, LocalDate fechaEntrada, LocalDate fechaSalida,
            Inmueble inmueble, Particular particular, int id) {
        this.fechaReserva = fechaReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.importe = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida)*inmueble.getPrecioPorNoche()*(particular.isVip()?0.9:1);
        this.inmueble = inmueble;
        this.particular = particular;
        this.id = id;
    }
    /**
     * Obtiene una lista de todas las reservas, que se ordenan por la fecha de realizacion de la reserva.
     *
     * @return Una lista de todas las reservas.
     */
    public static ArrayList<Reserva> getReservas(){
        ArrayList<Reserva> reservas=ListManager.getReservas();
        Collections.sort(reservas, new Comparator<Reserva>(){
            public int compare(Reserva r1, Reserva r2){
                return r1.getFechaReserva().compareTo(r2.getFechaReserva());
            }
        });
        return reservas;
    }
    /**
     * Annade una nueva reserva al ListManager.
     * Valida los datos de la reserva antes de annadirla usando el metodo validarReserva.
     * Imprime una factura una vez que la reserva ha sido annadida.
     * Incrementa el contador de veces que el inmueble ha sido reservado.
     *
     * @param fechaReserva La fecha en que se hace la reserva.
     * @param fechaEntrada La fecha de entrada al inmueble.
     * @param fechaSalida La fecha de salida del inmueble.
     * @param inmueble El inmueble a reservar.
     * @param particular El particular que hace la reserva.
     * @throws IllegalArgumentException Si la reserva no es valida.
     */
    public static void annadirReserva(LocalDate fechaReserva, LocalDate fechaEntrada, LocalDate fechaSalida, Inmueble inmueble,
            Particular particular){
        if (!validarReserva(fechaReserva, fechaEntrada, fechaSalida, inmueble, particular).isEmpty())
                throw new IllegalArgumentException(validarReserva(fechaReserva, fechaEntrada, fechaSalida, inmueble, particular));
        Reserva nuevaReserva=new Reserva(fechaReserva, fechaEntrada, fechaSalida, inmueble, particular, ListManager.getReservas().size());
        ListManager.getReservas().add(nuevaReserva);
        nuevaReserva.imprimirFactura();
        inmueble.setVecesReservado(inmueble.getVecesReservado()+1);
    }
    /**
     * Cancela una reserva, borrandola del ListManager.
     * Solo se puede cancelar una reserva si no ha pasado la fecha de salida.
     * Decrementa el contador de veces que el inmueble ha sido reservado.
     *
     * @param id El ID de la reserva a borrar.
     * @throws IllegalArgumentException Si la reserva ya ha finalizado.
     */
    public static void borrarReserva (int id){
        for (Reserva r: ListManager.getReservas()){
            if (r.getId()==id){
                if (r.getFechaSalida().isBefore(LocalDate.now()))
                    throw new IllegalArgumentException("No se puede borrar una reserva pasada");
                r.getInmueble().setVecesReservado(r.getInmueble().getVecesReservado()-1);
                ListManager.getReservas().remove(r);
                return;
            }
        }
    }
    /**
     * Valida una reserva.
     * Comprueba varias condiciones para asegurarse de que la reserva es valida:
     * 1. La fecha de entrada no puede ser posterior a la fecha de salida.
     * 2. La fecha de reserva no puede ser posterior a la fecha de entrada o salida.
     * 3. No se puede hacer una reserva para una fecha anterior al dia siguiente.
     * 4. No se pueden hacer reservas a mas de un anno de antelacion.
     * 5. La duracion de la reserva no puede ser mayor a dos meses.
     * 6. El particular no puede tener otra reserva en las mismas fechas.
     * 7. El inmueble no puede estar reservado en las mismas fechas.
     *
     * @param fechaReserva La fecha en que se hace la reserva.
     * @param fechaEntrada La fecha de entrada al inmueble.
     * @param fechaSalida La fecha de salida del inmueble.
     * @param inmueble El inmueble a reservar.
     * @param particular El particular que hace la reserva.
     * @return Una cadena de texto con los errores de validacion. Si la cadena esta vacia, la reserva es valida.
     */
    public static String validarReserva(LocalDate fechaReserva, LocalDate fechaEntrada, LocalDate fechaSalida, Inmueble inmueble,
    Particular particular){
        String errores="";
        if(fechaEntrada.isAfter(fechaSalida)){
            errores+="La fecha de entrada no puede ser posterior a la fecha de salida\n";
            return errores;
        }
        if(fechaReserva.isAfter(fechaEntrada) || fechaReserva.isAfter(fechaSalida)){
            errores+="La fecha de reserva no puede ser posterior a la fecha de entrada o salida\n";
            return errores;
        }
        if (fechaEntrada.isBefore(LocalDate.now().plusDays(1)) || fechaSalida.isBefore(LocalDate.now().plusDays(1)))
            errores+="No se puede reservar antes de mannana\n";
        if (ChronoUnit.YEARS.between(fechaReserva, fechaEntrada)>0 || ChronoUnit.YEARS.between(fechaReserva, fechaSalida)>0)
            errores+="No se pueden reservar inmuebles a mas de un anno vista\n";
        if (ChronoUnit.MONTHS.between(fechaEntrada, fechaSalida)>2)
            errores+="No se pueden reservar inmuebles por mas de dos meses\n";
        for (Reserva r: Reserva.getReservas()){
            if (r.getParticular().equals(particular)){
                if (!((fechaSalida.isAfter(r.getFechaSalida())&& (fechaEntrada.isAfter(r.getFechaSalida())||fechaEntrada.equals(r.getFechaSalida())))||((fechaSalida.isBefore(r.getFechaEntrada())||fechaSalida.equals(r.getFechaEntrada()))&& fechaEntrada.isBefore(r.getFechaEntrada()))))
                    errores+="Ya tienes una reserva en esas fechas\n";
                    break;
            }
        }
        for (Reserva r:ListManager.getReservas()){
            if (r.getInmueble().equals(inmueble)&&!((fechaSalida.isAfter(r.getFechaSalida())&& (fechaEntrada.isAfter(r.getFechaSalida())||fechaEntrada.equals(r.getFechaSalida())))||((fechaSalida.isBefore(r.getFechaEntrada())||fechaSalida.equals(r.getFechaEntrada()))&& fechaEntrada.isBefore(r.getFechaEntrada()))))
                errores+="El inmueble ya esta reservado en esas fechas\n";
                break;
        }
        return errores;
    }

    /**
     * Imprime una factura para la reserva.
     * La factura se guarda en un archivo de texto.
     * El nombre del archivo sigue el siguiente formato: "Facturas/(Titulo del inmueble)_(correo del inmueble)_(fecha de entrada)_(fecha de salida).txt"
     */
    public void imprimirFactura(){
        try{
                String now = LocalDate.now().toString();
                File factura = new File("Facturas\\"+this.inmueble.getTitulo()+'_'+this.inmueble.getDuenno().getCorreo()+'_'+fechaEntrada.toString()+'_'+fechaSalida.toString()+".txt");
                factura.mkdirs();
                factura.createNewFile();
                PrintWriter writer = new PrintWriter("Facturas\\"+this.inmueble.getTitulo()+'_'+this.inmueble.getDuenno().getCorreo()+'_'+fechaEntrada.toString()+'_'+fechaSalida.toString()+".txt");
                writer.println("Datos de la Reserva");
                writer.println("Fecha de la reserva: "+now.toString());
                writer.println("Importe: "+String.valueOf(importe)+" €");
                writer.println("Fecha de entrada: "+fechaEntrada.toString());
                writer.println("Fecha de salida: "+fechaSalida.toString());
                writer.println("\nDatos del Inmueble");
                writer.println("Titulo: "+inmueble.getTitulo());
                writer.println("Duenno: "+inmueble.getDuenno());
                writer.println("Tipo de propiedad: "+inmueble.getTipo().toString());
                writer.println("Precio por noche: "+String.valueOf(inmueble.getPrecioPorNoche()));
                writer.println("Calificacion: "+String.valueOf(inmueble.getCalificacion()));
                writer.println("Direccion: "+inmueble.getDireccion().toString());
                writer.println("Bannos: "+inmueble.getBannos());
                writer.println("Huespedes maximos: "+String.valueOf(inmueble.getHuespedesMaximos()));
                writer.println("Habitaciones: "+String.valueOf(inmueble.getHabitaciones()));
                writer.println("Camas: "+String.valueOf(inmueble.getCamas()));
                writer.println("\nDatos del cliente");
                writer.println("Correo: "+particular.getCorreo());
                writer.println("Nombre: "+particular.getNombre());
                writer.println("Telefono: "+particular.getTelefono());
                writer.println("DNI: "+particular.getDni());
                writer.println("\n¡Gracias por reservar con JavaBnB!");
                writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }




}//end Reserva