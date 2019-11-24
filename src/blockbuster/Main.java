package blockbuster;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {

        TreeMap treeMapPeliculas = new TreeMap();
        TreeMap treeMapUsuarios = new TreeMap();
        TreeMap treeMapAlquiler = new TreeMap();
        ManejadorArchivos manejadorArchivos = new ManejadorArchivos(treeMapPeliculas, treeMapUsuarios, treeMapAlquiler);
        manejadorArchivos.leerRepositorio();
        Procesos procesos = new Procesos(treeMapPeliculas, treeMapUsuarios, treeMapAlquiler, manejadorArchivos);
        Consultas consultas = new Consultas(treeMapPeliculas, treeMapUsuarios, treeMapAlquiler);
        Scanner scanner = new Scanner (System.in);
        int opciones=1;

        while (opciones != 3) {
            // Menú Principal
            System.out.println("\n Menu:\n");
            System.out.println("1. Consulta de datos");
            System.out.println("2. Procesos");
            System.out.println("3. Cerrar");
            opciones = scanner.nextInt();
            scanner.nextLine();

            switch (opciones) {
                case 1:
                    // Menú de consulta de datos
                    System.out.println("1. Consultar Pelicula");
                    System.out.println("2. Consultar Usuario");
                    System.out.println("3. Consultar peliculas alquiladas por usuario");
                    System.out.println("4. Consultar peliculas de un género");
                    System.out.println("5. Consultar peliculas a ser devueltas en una fecha específica");
                    System.out.println("6. Retroceder");
                    int accion = scanner.nextInt();
                    scanner.nextLine();

                    switch (accion) {
                        case 1:
                            //Consultar Pelicula
                            consultas.consultarPelicula();
                            break;
                        case 2:
                            // Consultar Usuario
                            consultas.consultarUsuario();
                            break;
                        case 3:
                            // Consultar peliculas alquiladas por usuario
                            consultas.consultarPeliculasAlquiladas();
                            break;
                        case 4:
                            // Consultar peliculas de un género
                            consultas.consultarPeliculasPorGenero();
                            break;
                        case 5:
                            // Consultar peliculas a devolver en una fecha
                            consultas.consultarPeliculasDevolucion();
                            break;
                        case 6:
                            // Retroceder
                            break;
                        default:
                            System.out.println("Opcion no valida, solo del 1 al 6...");
                    }
                    break;
                case 2:
                    // Menú de procesos
                    System.out.println("1. Agregar Pelicula");
                    System.out.println("2. Modificar Película");
                    System.out.println("3. Agregar Usuario");
                    System.out.println("4. Modificar Usuario");
                    System.out.println("5. Registrar alquiler de una película");
                    System.out.println("6. Registrar devolución de película");
                    System.out.println("7. Retroceder");
                    int eleccion = scanner.nextInt();
                    scanner.nextLine();

                    switch (eleccion) {
                        case 1:
                            // Agregar Pelicula
                            procesos.agregarPelicula();
                            break;
                        case 2:
                            // Modificar Película
                            procesos.modificarPelicula();
                            break;
                        case 3:
                            // Agregar Usuario
                            procesos.agregarUsuario();
                            break;
                        case 4:
                            // Modificar Usuario
                            procesos.modificarUsuario();
                            break;
                        case 5:
                            // Registrar alquiler de una película
                            procesos.registrarAlquiler();
                            break;
                        case 6:
                            // Registrar devolución de película
                            procesos.registrarDevolucion();
                            break;
                        case 7:
                            // Retroceder
                            break;
                        default:
                            System.out.println("Opcion no valida, solo del 1 al 6...");
                    }
                    break;
                case 3:
                    // Cerrar Programa
                    System.out.println("Saliendo del sistema");
                    break;
                default:
                    System.out.println("Opcion no valida, solo del 1 al 6...");
            }

        }
    }
}