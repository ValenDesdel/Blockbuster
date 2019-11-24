package blockbuster;

import java.util.ArrayList;

public class Alquiler {

    private int cedulaAlquiler;
    private ArrayList<String> peliculasAlquiladas = new ArrayList<>();

    public Alquiler(int cedulaAlquiler) {
        this.cedulaAlquiler = cedulaAlquiler;
    }

    public ArrayList<String> getPeliculasAlquiladas() {
        return peliculasAlquiladas;
    }

    public void addPeliculasAlquilada(int indexPelicula) {
        this.peliculasAlquiladas.add(String.valueOf(indexPelicula));
    }

    public void devolverPelicula(int indexPelicula) {
        String index = String.valueOf(indexPelicula);
        this.peliculasAlquiladas.remove(index);
    }
}
