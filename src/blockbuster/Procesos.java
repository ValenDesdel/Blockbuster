package blockbuster;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Procesos {
    private TreeMap treeMapPeliculas;
    private TreeMap treeMapUsuarios;
    private TreeMap treeMapAlquiler;
    private ManejadorArchivos manejadorArchivos;
    private Scanner scanner  = new Scanner (System.in);

    public Procesos(TreeMap treeMapPeliculas, TreeMap treeMapUsuarios, TreeMap treeMapAlquiler, ManejadorArchivos manejadorArchivos) {
        this.treeMapPeliculas = treeMapPeliculas;
        this.treeMapUsuarios = treeMapUsuarios;
        this.treeMapAlquiler = treeMapAlquiler;
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
        manejadorArchivos.IngresarDatos(titulo, genero, "null", "null");
    }

    public void modificarPelicula() throws IOException {
        System.out.println("Ingrese el índice de la película:");
        int indexPelicula = scanner.nextInt();
        scanner.nextLine();
        if (treeMapPeliculas.containsKey(indexPelicula)) {
            Pelicula pelicula = (Pelicula) treeMapPeliculas.get(indexPelicula);
            treeMapPeliculas.remove(indexPelicula);
            System.out.println("Pelicula a modificar: " + pelicula.getTitulo() + ", " + pelicula.getGenero());
            System.out.println("Ingrese el título:");
            String titulo = scanner.nextLine();
            scanner.nextLine();
            System.out.println("Ingrese el género:");
            String genero = scanner.nextLine();
            scanner.nextLine();
            pelicula.setTitulo(titulo);
            pelicula.setGenero(genero);
            treeMapPeliculas.put(indexPelicula, pelicula);
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
        if (treeMapUsuarios.containsKey(cedula)) {
            Usuario usuario = (Usuario) treeMapUsuarios.get(cedula);
            String linea = usuario.getCedula() + "," + usuario.getNombre() + "," + usuario.getTelefono();
            treeMapUsuarios.remove(cedula);
            System.out.println("Usuario a modificar: " + usuario.getCedula() + ", " + usuario.getNombre() + ", " + usuario.getTelefono());
            System.out.println("Ingresa la cédula: ");
            cedula = scanner.nextInt();
            scanner.nextLine();
            if (!treeMapUsuarios.containsKey(cedula)) {
                System.out.println("Ingresa el nombre:");
                String nombre = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Ingresa el teléfono:");
                String telefono = scanner.nextLine();
                scanner.nextLine();
                usuario.setCedula(cedula);
                usuario.setNombre(nombre);
                usuario.setTelefono(telefono);
                treeMapUsuarios.put(cedula, usuario);
                manejadorArchivos.modificarFileUsuario(usuario, linea);
            } else {
                System.out.println("La cédula está repetida");
                treeMapUsuarios.put(usuario.getCedula(), usuario);
            }
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void registrarAlquiler() throws IOException {
        System.out.println("Ingrese la cédula del usuario: ");
        int cedula = scanner.nextInt();
        if (treeMapUsuarios.containsKey(cedula)) {
            System.out.println("Ingrese el indice de la película a alquilar:");
            int indexPelicula = scanner.nextInt();
            scanner.nextLine();
            if (treeMapPeliculas.containsKey(indexPelicula)) {
                Pelicula pelicula = (Pelicula) treeMapPeliculas.get(indexPelicula);
                if (pelicula.getCiAlquiler().equals("null")) {
                    System.out.println("Ingrese la fecha a devolver la película:  DD/MM/AAAA");
                    String fechaEntrega = scanner.nextLine();
                    scanner.nextLine();
                    if (treeMapAlquiler.containsKey(cedula)) {
                        Alquiler alquiler = (Alquiler) treeMapAlquiler.get(cedula);
                        treeMapAlquiler.remove(cedula);
                        alquiler.addPeliculasAlquilada(indexPelicula);
                        treeMapAlquiler.put(cedula, alquiler);
                        treeMapPeliculas.remove(indexPelicula);
                        pelicula.setCiAlquiler(String.valueOf(cedula));
                        pelicula.setFechaDevolucion(fechaEntrega);
                        treeMapPeliculas.put(indexPelicula, pelicula);
                        manejadorArchivos.modificarFilePelicula(pelicula);
                    } else {
                        Alquiler alquiler = new Alquiler(cedula);
                        alquiler.addPeliculasAlquilada(indexPelicula);
                        treeMapAlquiler.put(cedula, alquiler);
                        treeMapPeliculas.remove(indexPelicula);
                        pelicula.setCiAlquiler(String.valueOf(cedula));
                        pelicula.setFechaDevolucion(fechaEntrega);
                        treeMapPeliculas.put(indexPelicula, pelicula);
                        manejadorArchivos.modificarFilePelicula(pelicula);
                    }
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
        if (treeMapPeliculas.containsKey(indexPelicula)) {
            Pelicula pelicula = (Pelicula) treeMapPeliculas.get(indexPelicula);
            treeMapPeliculas.remove(indexPelicula);
            int cedula = Integer.parseInt(pelicula.getCiAlquiler());
            pelicula.setCiAlquiler("null");
            pelicula.setFechaDevolucion("null");
            treeMapPeliculas.put(indexPelicula, pelicula);
            Alquiler alquiler = (Alquiler) treeMapAlquiler.get(cedula);
            treeMapAlquiler.remove(cedula);
            alquiler.devolverPelicula(indexPelicula);
            if (!alquiler.getPeliculasAlquiladas().isEmpty()) {
                treeMapAlquiler.put(cedula, alquiler);
            }
            System.out.println("Película devuelta!");
            manejadorArchivos.modificarFilePelicula(pelicula);
        } else {
            System.out.println("La película no existe");
        }
    }
}
