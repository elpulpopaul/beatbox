public class Pista {
    
    private String nombreCancion;
    private Artista artista;
    private int duracion;
    private int reproducciones;
    private static int numeroPistas = 0;

    public Pista(String nombreCancion, Artista artista, int duracion, int reproducciones) {
        this.nombreCancion = nombreCancion;
        this.artista = artista;
        this.duracion = duracion;
        this.reproducciones = reproducciones;
        numeroPistas++;
    }

    public Pista(String nombreCancion, Artista artista, int duracion) {
        this(nombreCancion, artista, duracion, 0);
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public Artista getArtista() {
        return artista;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getReproducciones() {
        return reproducciones;
    }

    public static int getNumeroPistas() {
        return numeroPistas;
    }

    public void play() {
        reproducciones++;
    }

    

    @Override
    public String toString() {
        return nombreCancion + "|" + artista.nombreCompleto() + "|" + duracion + "sg";
    }
}
