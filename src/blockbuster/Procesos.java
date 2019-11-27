package blockbuster;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Procesos {
    private RegistroPelicula registroPelicula;
    private RegistroUsuario registroUsuario;
    private RegistroAlquiler registroAlquiler;
    private ManejadorArchivos manejadorArchivos;
    private Scanner scanner  = new Scanner (System.in);

    public Procesos(RegistroPelicula registroPelicula, RegistroUsuario registroUsuario, RegistroAlquiler registroAlquiler, ManejadorArchivos manejadorArchivos) {
        this.registroPelicula = registroPelicula;
        this.registroUsuario = registroUsuario;
        this.registroAlquiler = registroAlquiler;
        this.manejadorArchivos = manejadorArchivos;
    }

    public void agregarPelicula() throws IOException {
        String titulo, genero;
        System.out.println("Ingrese el título de la pelicula:");
        titulo = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Ingrese el género de la pelicula:");
        genero = scanner.nextLine();
        scanner.nextLine();
        boolean validacion = true;
        if (!genero.equals("Cultural") || !genero.equals("Drama") || !genero.equals("Deportivo") || !genero.equals("Comedia")) {
            validacion = true;
        }
        while(!validacion) {
            System.out.println("Ingrese un genero valido (Cultural, Drama, Deportivo, Comedia)");
            genero = scanner.nextLine();
            scanner.nextLine();
            if (genero.equals("Cultural") || genero.equals("Drama") || genero.equals("Deportivo") || genero.equals("Comedia")) {
                validacion = true;
            }
        }
        manejadorArchivos.IngresarDatos(titulo, genero, "null", "null");
    }

    public void modificarPelicula() throws IOException {
        System.out.println("Ingrese el índice de la película:");
        int indexPelicula = scanner.nextInt();
        scanner.nextLine();
        if (registroPelicula.existePelicula(indexPelicula)) {
            Pelicula pelicula = registroPelicula.getPeliculaDirecto(indexPelicula);
            System.out.println("Pelicula a modificar: " + pelicula.getTitulo() + ", " + pelicula.getGenero());
            System.out.println("Ingrese el título:");
            String titulo = scanner.nextLine();
            scanner.nextLine();
            System.out.println("Ingrese el género:");
            String genero = scanner.nextLine();
            scanner.nextLine();
            boolean validacion = true;
            if (!genero.equals("Cultural") || !genero.equals("Drama") || !genero.equals("Deportivo") || !genero.equals("Comedia")) {
                validacion = true;
            }
            while(!validacion) {
                System.out.println("Ingrese un genero valido (Cultural, Drama, Deportivo, Comedia)");
                genero = scanner.nextLine();
                scanner.nextLine();
                if (genero.equals("Cultural") || genero.equals("Drama") || genero.equals("Deportivo") || genero.equals("Comedia")) {
                    validacion = true;
                }
            }
            pelicula.setTitulo(titulo);
            pelicula.setGenero(genero);
            registroPelicula.modificarPelicula(pelicula, indexPelicula);
            manejadorArchivos.modificarFilePelicula(pelicula);
        } else {
            System.out.println("La película no existe");
        }
    }

    public void agregarUsuario() throws IOException {
        System.out.println("Ingrese la cédula del usuario:");
        int cedula = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el nombre del usuario:");
        String nombre = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Ingrese el telefono del usuario:");
        String telefono = scanner.nextLine();
        scanner.nextLine();
        manejadorArchivos.IngresarDatos(cedula, nombre, telefono);
    }

    public void modificarUsuario() throws IOException {
        System.out.println("Ingrese la cédula del usuario");
        int cedula = scanner.nextInt();
        scanner.nextLine();
        if (registroUsuario.existeUsuario(cedula)) {
            Usuario usuario = registroUsuario.getUsuario(cedula);
            String linea = usuario.getCedula() + "," + usuario.getNombre() + "," + usuario.getTelefono();
            System.out.println("Usuario a modificar: " + usuario.getCedula() + ", " + usuario.getNombre() + ", " + usuario.getTelefono());
            System.out.println("Ingresa la cédula: ");
            cedula = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingresa el nombre:");
            String nombre = scanner.nextLine();
            scanner.nextLine();
            System.out.println("Ingresa el teléfono:");
            String telefono = scanner.nextLine();
            scanner.nextLine();
            usuario.setCedula(cedula);
            usuario.setNombre(nombre);
            usuario.setTelefono(telefono);
            registroUsuario.putUsuario(usuario);
            manejadorArchivos.modificarFileUsuario(usuario, linea);
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void registrarAlquiler() throws IOException {
        System.out.println("Ingrese la cédula del usuario: ");
        int cedula = scanner.nextInt();
        if (registroUsuario.existeUsuario(cedula)) {
            System.out.println("Ingrese el indice de la película a alquilar:");
            int indexPelicula = scanner.nextInt();
            scanner.nextLine();
            if (registroPelicula.existePelicula(indexPelicula)) {
                Pelicula pelicula = registroPelicula.getPeliculaDirecto(indexPelicula);
                if (pelicula.getCiAlquiler().equals("null")) {
                    System.out.println("Ingrese la fecha a devolver la película:  DD/MM/AAAA");
                    String fechaEntrega = scanner.nextLine();
                    scanner.nextLine();
                    Alquiler alquiler = new Alquiler(cedula);
                    alquiler.addPeliculasAlquilada(indexPelicula);
                    registroAlquiler.putAlquiler(alquiler);
                    pelicula.setCiAlquiler(String.valueOf(cedula));
                    pelicula.setFechaDevolucion(fechaEntrega);
                    registroPelicula.modificarPelicula(pelicula, indexPelicula);
                    manejadorArchivos.modificarFilePelicula(pelicula);
                    registroAlquiler.reordenar();

                } else {
                    System.out.println("La película está alquilada");
                }
            } else {
                System.out.println("La película no existe");
            }
        } else {
            System.out.println("El usuario no existe \n Procede a crearlo");
        }
    }

    public void registrarDevolucion() throws IOException {
        System.out.println("Ingrese el indice de la película: ");
        int indexPelicula = scanner.nextInt();
        scanner.nextLine();
        if (registroPelicula.existePelicula(indexPelicula)) {
            Pelicula pelicula = registroPelicula.getPeliculaDirecto(indexPelicula);
            int cedula = Integer.parseInt(pelicula.getCiAlquiler());
            pelicula.setCiAlquiler("null");
            pelicula.setFechaDevolucion("null");
            registroPelicula.modificarPelicula(pelicula, indexPelicula);
            Alquiler alquiler = registroAlquiler.getAlquiler(cedula);
            alquiler.devolverPelicula(indexPelicula);
            if (!alquiler.getPeliculasAlquiladas().isEmpty()) {
                registroAlquiler.setAlquiler(alquiler, cedula);
            }
            System.out.println("Película devuelta!");
            manejadorArchivos.modificarFilePelicula(pelicula);
        } else {
            System.out.println("La película no existe");
        }
    }
}
