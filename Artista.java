public class Artista implements Comparable<Artista> {

    private String nombre;
    private String apellido;

    public Artista(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Artista(String nombre) {
        this(nombre, "");
    }

    @Override
    public String toString() {
        return "Artista [nombre=" + nombre + ", apellido=" + apellido + "]";
    }

    public String nombreCompleto() {
        return (apellido.equals("") ? nombre : nombre + " " + apellido);
    }

    @Override
    public boolean equals(Object o) {
        Artista a = (Artista) o;
        return this.nombreCompleto().equals(a.nombreCompleto());
    }

    @Override
    public int hashCode() {
        return nombreCompleto().length() * nombre.charAt(0);
    }

    @Override
    public int compareTo(Artista a) {
        return this.nombreCompleto().compareToIgnoreCase(a.nombreCompleto());
    }
}