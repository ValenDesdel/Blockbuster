package control_alumno_archivo;

import java.io.*;

public class ManejadorArchivos {

    File filePeliculas = new File("peliculas.txt");
    FileWriter fileWriterPeliculas = new FileWriter(filePeliculas, true);
    BufferedWriter bufferedWriterPeliculas = new BufferedWriter(fileWriterPeliculas);
    PrintWriter printWriterPeliculas = new PrintWriter(bufferedWriterPeliculas);
    FileReader fileReaderPeliculas = new FileReader(filePeliculas);
    BufferedReader bufferedReaderPeliculas = new BufferedReader(fileReaderPeliculas);


    File fileUsuarios = new File("usuarios.txt");
    FileReader fileReaderUsuario = new FileReader(fileUsuarios);
    BufferedReader bufferedReaderUsuario = new BufferedReader(fileReaderUsuario);
    FileWriter fileWriterUsuarios = new FileWriter(fileUsuarios);
    BufferedWriter bufferedWriterUsuarios = new BufferedWriter(fileWriterUsuarios);
    PrintWriter printWriterUsuarios = new PrintWriter(bufferedWriterUsuarios);

    File fileAlquiler = new File("alquiler.txt");
    FileReader fileReaderAlquiler = new FileReader(fileAlquiler);
    BufferedReader bufferedReaderAlquiler = new BufferedReader(fileReaderAlquiler);
    FileWriter fileWriterAlquiler = new FileWriter(fileAlquiler);
    BufferedWriter bufferedWriterAlquiler = new BufferedWriter(fileWriterAlquiler);
    PrintWriter printWriterAlquiler = new PrintWriter(bufferedWriterAlquiler);

    // Atributos de Pelicula
    private String[] lineaPelicula = new String[arrayLength];
    private String[] indexPelicula = new String[arrayLength];
    private String[] titulo = new String[arrayLength];
    private String[] genero = new String[arrayLength];
    private String[] ciUsuario = new String[arrayLength];
    private String[] fechaDevolucion = new String[arrayLength];

    // Atributos de Usuario
    private String[] lineaUsuario = new String[arrayLength];
    private String[] cedula = new String[arrayLength];
    private String[] nombre = new String[arrayLength];
    private String[] telefono = new String[arrayLength];

    // Atributos de Alquiler
    private String[] lineaAlquiler = new String[arrayLength];
    private String[] cedulaAlquiler = new String[arrayLength];
    private String[] indexPeliculaAlquilada = new String[arrayLength];

    public final static int arrayLength = 15;

    public ManejadorArchivos() throws IOException {

    }

    // Crear Pelicula
    public void IngresarDatos(String titulo, String genero, int ciUsuario, String fechaDevolucion){
        try {
            this.fileWriterPeliculas.write(lineaPelicula.length + "," + titulo +","+ genero + "," + ciUsuario + "," + fechaDevolucion +"\r\n");
            this.fileWriterPeliculas.close();

        }catch (IOException ex){
            System.out.println("No se ha encontrado el archivo");
        }
    }

    // Crear Usuario
    public void IngresarDatos(int cedula, String nombre, int telefono){
        try {
            this.fileWriterUsuarios.write(cedula +","+ nombre + "," + telefono + "\r\n");
            this.fileWriterUsuarios.close();
        }catch (IOException ex){
            System.out.println("No se ha encontrado el archivo");
        }
    }

    // Crear Alquiler
    public void IngresarDatos(int cedula, int indexPelicula){
        try {
            this.fileWriterUsuarios.write(cedula +","+ indexPelicula + "\r\n");
            this.fileWriterUsuarios.close();
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
            }
        }

        SepararStringAlquiler();
        SepararStringPelicula();
        SepararStringUsuario();
    }

    public void leerFilePeliculas() throws IOException {

    }

    public void leerFileUsuarios() throws IOException {

    }

    public void leerFileAlquiler() throws IOException {

    }

    private void SepararStringPelicula(){
        for (int i = 0; i < lineaPelicula.length; i++) {
            if(lineaPelicula[i] != null){
                indexPelicula[i] = lineaPelicula[i].split(",")[0];
                titulo[i] = lineaPelicula[i].split(",")[1];
                genero[i] = lineaPelicula[i].split(",")[2];
                ciUsuario[i] = lineaPelicula[i].split(",")[3];
                fechaDevolucion[i] = lineaPelicula[i].split(",")[4];
            }
        }
    }

    private void SepararStringUsuario(){
        for (int i = 0; i < lineaUsuario.length; i++) {
            if(lineaPelicula[i] != null){
                cedula[i] = lineaUsuario[i].split(",")[0];
                nombre[i] = lineaUsuario[i].split(",")[1];
                telefono[i] = lineaUsuario[i].split(",")[2];
            }
        }
    }

    private void SepararStringAlquiler(){
        for (int i = 0; i < lineaAlquiler.length; i++) {
            if(lineaAlquiler[i] != null){
                cedulaAlquiler[i] = lineaAlquiler[i].split(",")[0];
                indexPeliculaAlquilada[i] = lineaAlquiler[i].split(",")[1];
            }
        }
    }
}
