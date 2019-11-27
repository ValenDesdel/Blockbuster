package blockbuster;

public class RegistroPelicula {

    private Pelicula[] peliculas = new Pelicula[15];
    private int cantPeliculas = 0;

    public Pelicula getPeliculaDirecto(int index) {
        return this.peliculas[index];
    }

    public void putPelicula(Pelicula pelicula) {
        peliculas[cantPeliculas] = pelicula;
        cantPeliculas++;
    }

    public void modificarPelicula(Pelicula pelicula, int index) {
        peliculas[index] = pelicula;
    }

    public boolean existePelicula(int index) {
        if (peliculas[index] != null) {
            return true;
        } else {
            return false;
        }
    }

    public int getCantPeliculas() {
        return cantPeliculas;
    }
}
