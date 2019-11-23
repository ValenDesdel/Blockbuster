package control_alumno_archivo;

import java.util.ArrayList;

public class Alquiler {

    private int cedulaAlquiler;
    private ArrayList<Integer> peliculasAlquiladas;

    public Alquiler(int cedulaAlquiler) {
        this.cedulaAlquiler = cedulaAlquiler;
    }

    public int getCedulaAlquiler() {
        return cedulaAlquiler;
    }

    public void setCedulaAlquiler(int cedulaAlquiler) {
        this.cedulaAlquiler = cedulaAlquiler;
    }

    public ArrayList<Integer> getPeliculasAlquiladas() {
        return peliculasAlquiladas;
    }

    public void addPeliculasAlquilada(int indexPelicula) {
        this.peliculasAlquiladas.add(indexPelicula);
    }
}
