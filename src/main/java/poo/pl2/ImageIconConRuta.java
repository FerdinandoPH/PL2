package poo.pl2;

public class ImageIconConRuta extends javax.swing.ImageIcon {
    private String ruta;
    public String getRuta(){
        return ruta;
    }
    public void setRuta(String ruta){
        this.ruta=ruta;
    }
    public ImageIconConRuta(String ruta){
        super(ruta);
        this.ruta=ruta;
    }
    public ImageIconConRuta(java.awt.Image imagen, String ruta){
        super(imagen);
        this.ruta=ruta;
    }

}
