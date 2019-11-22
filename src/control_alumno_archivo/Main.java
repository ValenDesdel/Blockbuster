package control_alumno_archivo;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
       
        Scanner scanner=new Scanner (System.in);
        int opciones=1;

        File ficheroPeliculas =new File("peliculas.txt");
        FileWriter fileWriterPeliculas=new FileWriter(ficheroPeliculas, true);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriterPeliculas);
        PrintWriter printWriter=new PrintWriter(bufferedWriter);

        File fileUsuarios = new File("peliculas.txt");
        FileReader fileReaderUsuario = new FileReader(fileUsuarios);
        BufferedReader leerf = new BufferedReader(fileReaderUsuario);
       
        String nombre,nctrl,espec;

        while (opciones != 6) {
            
            // Menu del usuario
            System.out.println("\n Menu de Peliculas con archivos \n");
            System.out.println("1. Consultar Película");
            System.out.println("2. Agregar Pelicula");
            System.out.println("3. Consultar Usuario");
            System.out.println("4. Consultar Pelicula alquilada por usuario");
            System.out.println("4. Salir\n");
            opciones = scanner.nextInt();

            switch(opciones) {//inicio witch
                case 1:
                    // Consultar Pelicula

                    break;
                    
                case 2:
                    // Agregar Pelicula

                    break;
                case 3:
                    // Consultar Usuarios

                    break;
                case 4:
                    // Consultar pelicula alquilada por usuario

                    break;
                case 5:
                    // Consultar Peliculas de un genero

                case 6:
                    //Peliculas a ser devueltas en una fecha
                    System.out.println("Saliendo del sistema");
                    break;
                case 7:

                    break;
                default:
                    System.out.println("Opcion no valida, solo del 1 al 6...");
            }
        }
    }
}