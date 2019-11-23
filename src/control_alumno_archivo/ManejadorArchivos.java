package control_alumno_archivo;

import java.io.*;
import java.util.TreeMap;

public class ManejadorArchivos {

    private TreeMap treeMapPeliculas;
    private TreeMap treeMapUsuarios;
    private TreeMap treeMapAlquiler;

    File filePeliculas = new File("peliculas.txt");
    FileReader fileReaderPeliculas = new FileReader(filePeliculas);
    BufferedReader bufferedReaderPeliculas = new BufferedReader(fileReaderPeliculas);

    File fileUsuarios = new File("usuarios.txt");
    FileReader fileReaderUsuario = new FileReader(fileUsuarios);
    BufferedReader bufferedReaderUsuario = new BufferedReader(fileReaderUsuario);

    File fileAlquiler = new File("alquiler.txt");
    FileReader fileReaderAlquiler = new FileReader(fileAlquiler);
    BufferedReader bufferedReaderAlquiler = new BufferedReader(fileReaderAlquiler);

    // Atributos de Pelicula
    public String[] lineaPelicula = new String[arrayLength];
    private int cantPeliculas = 0;

    // Atributos de Usuario
    private String[] lineaUsuario = new String[arrayLength];

    // Atributos de Alquiler
    private String[] lineaAlquiler = new String[arrayLength];

    public final static int arrayLength = 15;

    public ManejadorArchivos(TreeMap treeMapPeliculas, TreeMap treeMapUsuarios, TreeMap treeMapAlquiler) throws IOException {
        this.treeMapPeliculas = treeMapPeliculas;
        this.treeMapUsuarios = treeMapUsuarios;
        this.treeMapAlquiler = treeMapAlquiler;
    }

    // Crear Pelicula
    public void IngresarDatos(String titulo, String genero, String ciUsuario, String fechaDevolucion) throws IOException {
        FileWriter fileWriterPeliculas = new FileWriter(filePeliculas, true);
        try {
            fileWriterPeliculas.write(cantPeliculas + "," + titulo + "," + genero + "," + ciUsuario + "," + fechaDevolucion +"\r\n");
            fileWriterPeliculas.close();
            Pelicula pelicula = new Pelicula(cantPeliculas, titulo, genero, ciUsuario, fechaDevolucion);
            treeMapPeliculas.put(cantPeliculas, pelicula);
            cantPeliculas++;
        }catch (IOException ex){
            System.out.println("No se ha encontrado el archivo");
        }
    }

    // Crear Usuario
    public void IngresarDatos(int cedula, String nombre, String telefono) throws IOException {
        FileWriter fileWriterUsuarios = new FileWriter(this.fileUsuarios, true);
        try {
            fileWriterUsuarios.write(cedula +","+ nombre + "," + telefono + "\r\n");
            fileWriterUsuarios.close();
            Usuario usuario = new Usuario(cedula, nombre, telefono);
        }catch (IOException ex){
            System.out.println("No se ha encontrado el archivo");
        }
    }

    // Crear Alquiler
    public void IngresarDatos(String cedula, String indexPelicula) throws IOException {
        FileWriter fileWriterAlquiler = new FileWriter(fileAlquiler, true);
        try {
            fileWriterAlquiler.write(cedula +","+ indexPelicula + "\r\n");
            fileWriterAlquiler.close();
        }catch (IOException ex){
            System.out.println("No se ha encontrado el archivo");
        }
    }

    public void leerRepositorio() throws IOException {

        String lines;

        for(int i = 0; i < arrayLength; i++){
            if(( lines = bufferedReaderUsuario.readLine()) != null){
                lineaUsuario[i] = lines;
            }
        }
        for(int i = 0; i < arrayLength; i++){
            if(( lines = bufferedReaderAlquiler.readLine()) != null){
                lineaAlquiler[i] = lines;
            }
        }
        for(int i = 0; i < arrayLength; i++){
            if(( lines = bufferedReaderPeliculas.readLine()) != null){
                lineaPelicula[i] = lines;
                this.cantPeliculas++;
            }
        }

        SepararStringAlquiler();
        SepararStringPelicula();
        SepararStringUsuario();
    }

    private void SepararStringPelicula(){
        for (int i = 0; i < lineaPelicula.length; i++) {
            if(lineaPelicula[i] != null){
                int indexPelicula = Integer.parseInt(lineaPelicula[i].split(",")[0]);
                String titulo = lineaPelicula[i].split(",")[1];
                String genero = lineaPelicula[i].split(",")[2];
                String ciUsuario = lineaPelicula[i].split(",")[3];
                String fechaDevolucion = lineaPelicula[i].split(",")[4];
                Pelicula pelicula = new Pelicula(indexPelicula, titulo, genero, ciUsuario, fechaDevolucion);
                this.treeMapPeliculas.put(indexPelicula, pelicula);
            }
        }
    }

    private void SepararStringUsuario(){
        for (int i = 0; i < lineaUsuario.length; i++) {
            if(lineaUsuario[i] != null){
                int cedula = Integer.parseInt(lineaUsuario[i].split(",")[0]);
                String nombre = lineaUsuario[i].split(",")[1];
                String telefono = lineaUsuario[i].split(",")[2];
                Usuario usuario = new Usuario(cedula, nombre, telefono);
                treeMapUsuarios.put(cedula, usuario);
            }
        }
    }

    private void SepararStringAlquiler(){
        for (int i = 0; i < lineaAlquiler.length; i++) {
            if(lineaAlquiler[i] != null){
                int cedulaAlquiler = Integer.parseInt(lineaAlquiler[i].split(",")[0]);
                int indexPeliculaAlquilada = Integer.parseInt(lineaAlquiler[i].split(",")[1]);
                if (treeMapAlquiler.containsKey(cedulaAlquiler)) {
                    Alquiler alquiler;
                    alquiler = (Alquiler) treeMapAlquiler.get(cedulaAlquiler);
                    alquiler.addPeliculasAlquilada(indexPeliculaAlquilada);
                    treeMapAlquiler.remove(cedulaAlquiler);
                    treeMapAlquiler.put(cedulaAlquiler, alquiler);
                } else {
                    Alquiler alquiler = new Alquiler(cedulaAlquiler);
                    alquiler.addPeliculasAlquilada(indexPeliculaAlquilada);
                    treeMapAlquiler.put(cedulaAlquiler, alquiler);
                }
            }
        }
    }
}
