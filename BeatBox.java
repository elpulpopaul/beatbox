import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BeatBox {
    
    private List<Pista> pistas;

    public BeatBox() {
        pistas = new ArrayList<>();
    }

    public void agregarPista(Pista p) {
        pistas.add(p);
    }

    public List<Pista> porArtista(Artista artista) {
        List<Pista> lista = new ArrayList<>();

        for (Pista pista : pistas) {
            Artista unArtista = pista.getArtista();
            if (unArtista.equals(artista)) {
                lista.add(pista);
            }
        }
        if (lista.isEmpty())
            lista = null;

        return lista;
    }

    public List<Pista> porCancion(String palabraClave) {
        List<Pista> lista = new ArrayList<>();

        for (Pista pista : pistas) {
            if (pista.getNombreCancion().contains(palabraClave))
                lista.add(pista);
        }

        if (lista.isEmpty())
            lista = null;
        
        return lista;
    }

    public int numeroPistas() {
        return pistas.size();
    }

    public Pista pistaHit() {
        Pista pistaHit = pistas.get(0);

        if (numeroPistas()>0) {
            for (Pista pista : pistas) {
                if (pista.getReproducciones() > pistaHit.getReproducciones())
                    pistaHit = pista; 
            }
        }
        return pistaHit;
    }

    public Map<Artista, ArrayList<Pista>> cancionesArtistas() {
        Map<Artista, ArrayList<Pista>> mapa = new TreeMap<Artista,ArrayList<Pista>>();

        for (Pista pista : pistas) {
            Artista a = pista.getArtista();
            if (!mapa.containsKey(a))
                mapa.put(a, new ArrayList<>());
            
            mapa.get(a).add(pista);
            
        }
        return mapa;
    }

    public List<Pista> playList(int duracionMaxima) {
        List<Pista> pistasOrd = new ArrayList<>(pistas);
        List<Pista> playlist = new ArrayList<>();
        Collections.sort(pistasOrd, (x, y) -> y.getReproducciones() - x.getReproducciones());

        for (Pista pista : pistasOrd) {
            int duracion = pista.getDuracion();
            if (duracionMaxima - duracion < 0)
                break;

            duracionMaxima -= duracion;
            playlist.add(pista);
        }

        return playlist;
    }

    public static void main(String[] args) {
        
        Artista a;
        Pista p1, p2, p3, p4, p5, p6;
        BeatBox beatBox = new BeatBox();

        System.out.println();
        a = new Artista("Shakira");
        p1 = new Pista("Shakira: Bzrp music sessions, Vol. 53", a, 180);
        beatBox.agregarPista(p1);
        System.out.println("+Agregando pista: " + p1);

        a = new Artista("Miley", "Cyrus");
        p2 = new Pista("Flowers", a, 190);
        beatBox.agregarPista(p2);
        System.out.println("+Agregando pista: " + p2);

        a = new Artista("Karol", "G");
        p3 = new Pista("TQG", a, 185);
        beatBox.agregarPista(p3);
        System.out.println("+Agregando pista: " + p3);

        a = new Artista("Shakira");
        p4 = new Pista("Te felicito", a, 175);
        beatBox.agregarPista(p4);
        System.out.println("+Agregando pista: " + p4);

        a = new Artista("Quevedo");
        p5 = new Pista("Quevedo: Bzrp music sessions, Vol. 52", a, 183);
        beatBox.agregarPista(p5);
        System.out.println("+Agregando pista: " + p5);

        a = new Artista("Young", "Miko");
        p6 = new Pista("Young Miko: Bzrp music sessions, Vol. 58", a, 181);
        beatBox.agregarPista(p6);
        System.out.println("+Agregando pista: " + p6);
        System.out.println();

        p1.play(); // shakira bzrp
        System.out.println("->Play: " + p1.getNombreCancion());
        p2.play(); // flowers miley cyrus
        System.out.println("->Play: " + p2.getNombreCancion());
        p1.play(); // shakira bzrp
        System.out.println("->Play: " + p1.getNombreCancion());
        p2.play(); // flowers miley cyrus
        System.out.println("->Play: " + p2.getNombreCancion());
        p4.play(); // te felicito
        System.out.println("->Play: " + p4.getNombreCancion());
        p2.play(); // flowers miley cyrus
        System.out.println("->Play: " + p2.getNombreCancion());
        p4.play(); // te felicito
        System.out.println("->Play: " + p4.getNombreCancion());

        System.out.println();
        System.out.println("#Hit del momento : " + beatBox.pistaHit());

        System.out.println();
        a = new Artista("Shakira");
        System.out.println("***** Listado por artista: " + a.nombreCompleto() + "*****");
        List<Pista> listaShakira = beatBox.porArtista(a);
        for (Pista pista : listaShakira) {  System.out.println(pista)   ;}

        System.out.println();
        String palabraClave = "Bzrp music sessions";
        System.out.println("***** Listado que incluye: " + palabraClave + "*****");
        List<Pista> listaBzrp = beatBox.porCancion(palabraClave);
        for (Pista pista : listaBzrp) {     System.out.println(pista)   ;}

        System.out.println();
        int duracionMaxima = 374;
        List<Pista> listaHits = beatBox.playList(374);
        System.out.println("***** Playlist de menos de " + duracionMaxima + " segundos de las canciones mas reproducidas *****");
        for (Pista pista : listaHits) {     System.out.println(pista)   ;}

        System.out.println();
        beatBox.porCancion("Shakira: Bzrp music sessions, Vol. 53").get(0).play();
        System.out.println("->Play: " + "Shakira: Bzrp music sessions, Vol. 53");

        System.out.println();
        System.out.println("#Hit del momento : " + beatBox.pistaHit());
        
        System.out.println();
        System.out.println("***** Todos los artistas y sus canciones *****");
        beatBox.cancionesArtistas().forEach((artista, canciones) -> System.out.println(artista.nombreCompleto() + ":" + canciones));
        System.out.println();
    }
}
