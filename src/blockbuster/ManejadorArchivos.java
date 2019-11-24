package blockbuster;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.TreeMap;

public class ManejadorArchivos {

    private TreeMap treeMapPeliculas;
    private TreeMap treeMapUsuarios;
    private TreeMap treeMapAlquiler;
    // TODO: Hacer que se guarden las peliculas alquiladas al iniciar

    File filePeliculas = new File("peliculas.txt");
    FileReader fileReaderPeliculas = new FileReader(filePeliculas);
    BufferedReader bufferedReaderPeliculas = new BufferedReader(fileReaderPeliculas);

    File fileUsuarios = new File("usuarios.txt");
    FileReader fileReaderUsuario = new FileReader(fileUsuarios);
    BufferedReader bufferedReaderUsuario = new BufferedReader(fileReaderUsuario);

    // Atributos de Pelicula
    public String[] lineaPelicula = new String[arrayLength];
    private int cantPeliculas = 0;

    // Atributos de Usuario
    private String[] lineaUsuario = new String[arrayLength];

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
            treeMapUsuarios.put(cedula, usuario);
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
            if(( lines = bufferedReaderPeliculas.readLine()) != null){
                lineaPelicula[i] = lines;
                this.cantPeliculas++;

            }
        }

        SepararStringPelicula();
        SepararStringUsuario();
    }

    private void SepararStringPelicula(){
        for (String s : lineaPelicula) {
            if (s != null) {
                int indexPelicula = Integer.parseInt(s.split(",")[0]);
                String titulo = s.split(",")[1];
                String genero = s.split(",")[2];
                String ciUsuario = s.split(",")[3];
                String fechaDevolucion = s.split(",")[4];
                Pelicula pelicula = new Pelicula(indexPelicula, titulo, genero, ciUsuario, fechaDevolucion);
                if (!pelicula.getCiAlquiler().equals("null")){
                    if (treeMapAlquiler.containsKey(Integer.parseInt(ciUsuario))) {
                        Alquiler alquiler = (Alquiler) treeMapAlquiler.get(Integer.parseInt(ciUsuario));
                        treeMapAlquiler.remove(Integer.parseInt(ciUsuario));
                        alquiler.getPeliculasAlquiladas().add(String.valueOf(indexPelicula));
                        treeMapAlquiler.put(Integer.parseInt(ciUsuario), alquiler);
                    } else {
                        Alquiler alquiler = new Alquiler(Integer.parseInt(pelicula.getCiAlquiler()));
                        alquiler.getPeliculasAlquiladas().add(String.valueOf(pelicula.getIndex()));
                        treeMapAlquiler.put(Integer.parseInt(pelicula.getCiAlquiler()), alquiler);
                    }
                }
                this.treeMapPeliculas.put(indexPelicula, pelicula);
            }
        }
    }

    private void SepararStringUsuario(){
        for (String s : lineaUsuario) {
            if (s != null) {
                int cedula = Integer.parseInt(s.split(",")[0]);
                String nombre = s.split(",")[1];
                String telefono = s.split(",")[2];
                Usuario usuario = new Usuario(cedula, nombre, telefono);
                treeMapUsuarios.put(cedula, usuario);
            }
        }
    }
}
