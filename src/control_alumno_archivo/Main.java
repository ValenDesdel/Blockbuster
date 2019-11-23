package control_alumno_archivo;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        TreeMap treeMapPeliculas = new TreeMap();
        TreeMap treeMapUsuarios = new TreeMap();
        TreeMap treeMapAlquiler = new TreeMap();
        ManejadorArchivos manejadorArchivos = new ManejadorArchivos(treeMapPeliculas, treeMapUsuarios, treeMapAlquiler);
        manejadorArchivos.leerRepositorio();
        Scanner scanner=new Scanner (System.in);
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

                    switch (accion) {
                        case 1:
                            //Consultar Pelicula
                            break;
                        case 2:
                            // Consultar Usuario
                            break;
                        case 3:
                            // Consultar peliculas alquiladas p/u
                            break;
                        case 4:
                            // Consultar peliculas de un género
                            break;
                        case 5:
                            // Consultar peliculas a devolver en una fecha
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
                    int proceso = scanner.nextInt();
                    scanner.nextLine();

                    switch (proceso) {
                        case 1:
                            // Agregar Pelicula
                            String titulo, genero;
                            System.out.println("Ingrese el título de la pelicula:");
                            titulo = scanner.nextLine();
                            scanner.nextLine();
                            System.out.println("Ingrese el género de la pelicula:");
                            genero = scanner.nextLine();
                            scanner.nextLine();
                            manejadorArchivos.IngresarDatos(titulo, genero, "null", "null");
                            break;
                        case 2:
                            // Modificar Película
                            break;
                        case 3:
                            // Agregar Usuario
                            int cedula;
                            String nombre, telefono;
                            System.out.println("Ingrese la cedula del usuario:");
                            cedula = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ingrese el nombre del usuario:");
                            nombre = scanner.nextLine();
                            scanner.nextLine();
                            System.out.println("Ingrese el telefono del usuario:");
                            telefono = scanner.nextLine();
                            scanner.nextLine();
                            manejadorArchivos.IngresarDatos(cedula, nombre, telefono);
                            break;
                        case 4:
                            // Modificar Usuario
                            break;
                        case 5:
                            // Registrar alquiler de una película
                            break;
                        case 6:
                            // Registrar devolución de película
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