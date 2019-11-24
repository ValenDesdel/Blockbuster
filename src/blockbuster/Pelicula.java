package blockbuster;

public class Pelicula {

    private int index;
    private String titulo;
    private String genero;
    private String ciAlquiler;
    private String fechaDevolucion;

    public Pelicula(int index, String titulo, String genero, String ciAlquiler, String fechaDevolucion) {
        this.index = index;
        this.titulo = titulo;
        this.genero = genero;
        this.ciAlquiler = ciAlquiler;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getIndex() {
        return index;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiAlquiler() {
        return ciAlquiler;
    }

    public void setCiAlquiler(String ciAlquiler) {
        this.ciAlquiler = ciAlquiler;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
