package blockbuster;

import java.util.Scanner;

public class Consultas {

    private RegistroPelicula registroPelicula;
    private RegistroUsuario registroUsuario;
    private RegistroAlquiler registroAlquiler;
    private Scanner scanner  = new Scanner (System.in);

    public Consultas(RegistroPelicula registroPelicula, RegistroUsuario registroUsuario, RegistroAlquiler registroAlquiler) {
        this.registroPelicula = registroPelicula;
        this.registroUsuario = registroUsuario;
        this.registroAlquiler = registroAlquiler;
    }

    public void consultarPelicula() {
        System.out.println("Ingrese el index de la pelicula a consultar:");
        int indexpelicula = scanner.nextInt();
        scanner.nextLine();
        if (registroPelicula.existePelicula(indexpelicula)) {
            Pelicula pelicula = registroPelicula.getPeliculaDirecto(indexpelicula);
            System.out.println("Título: " + pelicula.getTitulo());
            System.out.println("Género: " + pelicula.getGenero());
            if (!pelicula.getCiAlquiler().equals("null")) {
                System.out.println("Alquilada a: " + pelicula.getCiAlquiler());
                System.out.println("Fecha de devolucion: " + pelicula.getFechaDevolucion());
            }
        } else {
            System.out.println("La película no existe");
        }
    }

    public void consultarUsuario() {
        System.out.println("Ingrese la cédula del usuario: ");
        int cedula = scanner.nextInt();
        scanner.nextLine();
        if (registroUsuario.existeUsuario(cedula)) {
            Usuario usuario = registroUsuario.getUsuario(cedula);
            System.out.println("Cedula: " + usuario.getCedula());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Teléfono: " + usuario.getTelefono());
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void consultarPeliculasAlquiladas() {
        // Por usuario
        System.out.println("Ingresa la cédula del usuario:");
        int cedula = scanner.nextInt();
        scanner.nextLine();
        if (registroUsuario.existeUsuario(cedula) && registroAlquiler.getAlquiler(cedula) != null) {
            Alquiler alquiler = registroAlquiler.getAlquiler(cedula);
            System.out.println("Peliculas alquiladas:");
            for (int i = 0; i < alquiler.getPeliculasAlquiladas().size(); i++) {
                Pelicula pelicula = registroPelicula.getPeliculaDirecto(Integer.parseInt(alquiler.getPeliculasAlquiladas().get(i)));
                System.out.println("\n" + (i+1) + ". " + pelicula.getTitulo());
                System.out.println("   Fecha de entrega: " + pelicula.getFechaDevolucion());
            }
        } else if (registroUsuario.existeUsuario(cedula) && !(registroAlquiler.getAlquiler(cedula) == null)) {
            System.out.println("El usuario no tiene peliculas alquiladas");
        } else if (!registroUsuario.existeUsuario(cedula)) {
            System.out.println("El usuario no existe");
        }
    }

    public void consultarPeliculasDevolucion() {
        System.out.println("Ingresa la fecha de devolucion: DD/MM/AAAA ");
        String fechaDevolucion = scanner.nextLine();
        scanner.nextLine();
        boolean hayPeliculas = false;
        for (int i = 0; i < registroPelicula.getCantPeliculas(); i++) {
            Pelicula pelicula = registroPelicula.getPeliculaDirecto(i);
            if (pelicula.getFechaDevolucion().equals(fechaDevolucion)) {
                System.out.println((i+1) + ". Título: " + pelicula.getTitulo());
                System.out.println("   Género: " + pelicula.getGenero());
                Usuario usuario = registroUsuario.getUsuario(Integer.parseInt(pelicula.getCiAlquiler()));
                System.out.println("   Cédula de quien alquiló: " + usuario.getCedula());
                System.out.println("   Nombre: " + usuario.getNombre());
                System.out.println("   Teléfono: " + usuario.getTelefono());
                hayPeliculas = true;
            }
        }
        if (!hayPeliculas) {
            System.out.println("No hay peliculas con esa fecha");
        }
    }

    public void consultarPeliculasPorGenero() {
        System.out.println("Ingresa un género: ");
        String genero = scanner.nextLine();
        scanner.nextLine();
        boolean hayPeliculas = false;
        for (int i = 0; i < registroPelicula.getCantPeliculas(); i++) {
            Pelicula pelicula = registroPelicula.getPeliculaDirecto(i);
            if (pelicula.getGenero().equals(genero)) {
                System.out.println((i+1) + ". " + pelicula.getTitulo());
                hayPeliculas = true;
            }
        }
        if (!hayPeliculas) {
            System.out.println("No existen películas con ese género");
        }
    }
}
