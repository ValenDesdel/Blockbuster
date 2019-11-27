package blockbuster;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class ManejadorArchivos {

    private RegistroPelicula registroPelicula;
    private RegistroUsuario registroUsuario;
    private RegistroAlquiler registroAlquiler;

    File filePeliculas = new File("peliculas.txt");
    FileReader fileReaderPeliculas = new FileReader(filePeliculas);
    BufferedReader bufferedReaderPeliculas = new BufferedReader(fileReaderPeliculas);

    File fileUsuarios = new File("usuarios.txt");
    FileReader fileReaderUsuario = new FileReader(fileUsuarios);
    BufferedReader bufferedReaderUsuario = new BufferedReader(fileReaderUsuario);

    // Atributos de Pelicula
    private ArrayList<String> lineaPeliculas = new ArrayList<>();
    private int cantPeliculas = 0;

    // Atributos de Usuario
    private ArrayList<String> lineaUsuarios = new ArrayList<>();

    public ManejadorArchivos(RegistroPelicula registroPelicula, RegistroUsuario registroUsuario, RegistroAlquiler registroAlquiler) throws IOException {
        this.registroPelicula = registroPelicula;
        this.registroUsuario = registroUsuario;
        this.registroAlquiler = registroAlquiler;
    }

    // Crear Pelicula
    public void IngresarDatos(String titulo, String genero, String ciUsuario, String fechaDevolucion) throws IOException {
        FileWriter fileWriterPeliculas = new FileWriter(filePeliculas, true);
        try {
            String registro = lineaPeliculas.size() + "," + titulo + "," + genero + "," + ciUsuario + "," + fechaDevolucion;
            lineaPeliculas.add(registro);
            fileWriterPeliculas.write(registro + "\r\n");
            fileWriterPeliculas.close();
            Pelicula pelicula = new Pelicula((lineaPeliculas.size()-1), titulo, genero, ciUsuario, fechaDevolucion);
            registroPelicula.putPelicula(pelicula);
            cantPeliculas++;
        }catch (IOException ex){
            System.out.println("No se ha encontrado el archivo");
        }
    }

    // Crear Usuario
    public void IngresarDatos(int cedula, String nombre, String telefono) throws IOException {
        FileWriter fileWriterUsuarios = new FileWriter(this.fileUsuarios, true);
        try {
            String registro = cedula +","+ nombre + "," + telefono;
            lineaUsuarios.add(registro);
            fileWriterUsuarios.write(registro + "\r\n");
            fileWriterUsuarios.close();
            Usuario usuario = new Usuario(cedula, nombre, telefono);

            registroUsuario.putUsuario(usuario);
        }catch (IOException ex){
            System.out.println("No se ha encontrado el archivo");
        }
    }

    public void leerRepositorio() throws IOException {
        String lines;
        while ((lines = bufferedReaderUsuario.readLine()) != null) {
            lineaUsuarios.add(lines);
        }
        while ((lines = bufferedReaderPeliculas.readLine()) != null) {
            lineaPeliculas.add(lines);
        }
        SepararStringPelicula();
        SepararStringUsuario();
    }

    private void SepararStringPelicula(){
        for (int i = 0; i < lineaPeliculas.size(); i++) {
            int indexPelicula = Integer.parseInt(lineaPeliculas.get(i).split(",")[0]);
            String titulo = lineaPeliculas.get(i).split(",")[1];
            String genero = lineaPeliculas.get(i).split(",")[2];
            String ciUsuario = lineaPeliculas.get(i).split(",")[3];
            String fechaDevolucion = lineaPeliculas.get(i).split(",")[4];
            Pelicula pelicula = new Pelicula(indexPelicula, titulo, genero, ciUsuario, fechaDevolucion);
            if (!pelicula.getCiAlquiler().equals("null")) {
                    Alquiler alquiler = new Alquiler(Integer.parseInt(pelicula.getCiAlquiler()));
                    alquiler.getPeliculasAlquiladas().add(String.valueOf(pelicula.getIndex()));
                    registroAlquiler.putAlquiler(alquiler);
                    registroAlquiler.reordenar();
            }
            this.registroPelicula.putPelicula(pelicula);
        }
    }

    private void SepararStringUsuario(){
        for(int i = 0; i < lineaUsuarios.size(); i++) {
            int cedula = Integer.parseInt(lineaUsuarios.get(i).split(",")[0]);
            String nombre = lineaUsuarios.get(i).split(",")[1];
            String telefono = lineaUsuarios.get(i).split(",")[2];
            Usuario usuario = new Usuario(cedula, nombre, telefono);
            registroUsuario.putUsuario(usuario);
        }
    }

    public void modificarFilePelicula(Pelicula pelicula) throws IOException {
        String linea = pelicula.getIndex() + "," + pelicula.getTitulo() + "," + pelicula.getGenero() + "," + pelicula.getCiAlquiler() + "," + pelicula.getFechaDevolucion();
        lineaPeliculas.set(pelicula.getIndex(), linea);
        reescribirFichero(filePeliculas, this.lineaPeliculas);
    }

    public void modificarFileUsuario(Usuario usuario, String linea) throws IOException {
        int indexAnterior = lineaUsuarios.indexOf(linea);
        if (indexAnterior != -1) {
            String lineaNueva = usuario.getCedula() + "," + usuario.getNombre() + "," + usuario.getTelefono();
            lineaUsuarios.set(indexAnterior, lineaNueva);
            reescribirFichero(fileUsuarios, this.lineaUsuarios);
        } else {
            System.out.println("Hubo un problema");
        }
    }

    private void reescribirFichero(File file, ArrayList<String> lineas) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("");
        bufferedWriter.close();
        for (int i = 0; i < lineas.size(); i++) {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(lineas.get(i) + "\r\n");
            fileWriter.close();
        }
    }
}
