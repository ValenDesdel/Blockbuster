/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control_alumno_archivo;

import java.io.*;
import java.util.*;

public class Control_alumno_archivo {

    public static void main(String[] args) throws IOException {
       
        Scanner lee=new Scanner (System.in);
       
       
        String nombre,nctrl,espec;
        int edad,sem;
        float fundp,prog1,prog2,ed;
       
        //----------------------------------------------------------------------variables del programa
        int opciones=1;
        while (opciones!=4)
        {//---------------------------------------------------------------------inicio del while
            
            //------------------------------------------------------------------menu del usuario
            System.out.println("\n Menu de Peliculas con archivos \n");
            System.out.println("1. Agregar Pelicula");
            System.out.println("2. Consultar Pelicula");
            System.out.println("3. Listado general de Peliculas");
            System.out.println("4. Salir\n");
            opciones=lee.nextInt();
            switch(opciones)
            {//inicio witch
                case 1:
                    //----------------------------------------------------------preparando el objeto para escribir en el archivo
                    File fichero=new File("peliculas.txt");
                    FileWriter fw=new FileWriter(fichero, true);
                    BufferedWriter bw=new BufferedWriter(fw);
                    PrintWriter escribef=new PrintWriter(bw);
                    
                    // --------------------------------------------------------- pidiendo datos peliculas
                    System.out.println("Titulo de la pelicula");
                    nombre=lee.next();
                    System.out.println("Numero de control de la pelicula");
                    nctrl=lee.next();
                    System.out.println("Genero");
                    espec=lee.next();
                    
                    
                    
                    //System.out.println("CALIFICACIONES: \n");
                    //System.out.println("fundamentos de programacion:");
                    //fundp=lee.nextFloat();
                    //System.out.println("programacion I:");
                    //prog1=lee.nextFloat();
                    //System.out.println("programacion II:");
                    //prog2=lee.nextFloat();
                    //System.out.println("estructura de datos:");
                    //ed=lee.nextFloat();
                    
                    escribef.println(nctrl);
                    escribef.println(nombre);
                    escribef.println(espec);
                   
                    
                    
                     System.out.println("\n DATOS ALMACENADOS:");
                    escribef.close();
                    break;
                    
         case 2:
                    
                    File fichero1 = new File("peliculas.txt");
                    FileReader readerEnt = new FileReader(fichero1);
                    BufferedReader leerf = new BufferedReader(readerEnt);
                    
                    String control, ctrl_busca;
                    int encontrado=0;
                    System.out.println("INTRODUZCA EL NUMERO DE CONTROL DE LA PELICULA: ");
                    ctrl_busca=lee.next();
                    while((control=leerf.readLine())!=null)
                    {
                        if (control.equals(ctrl_busca))
                        {
                            nombre=leerf.readLine();
                            espec=leerf.readLine();

                            System.out.println("Datos de la pelicula: \n");
                            System.out.println("Nombre: "+nombre);
                            System.out.println("Numero de control: "+control);
                            System.out.println("genero: "+espec);

                            encontrado++;
                        }//-----------------------------------------------------fin del if encontrado
                    }//---------------------------------------------------------fin de while buscar
                    if (encontrado==0)
                        System.out.println("\n LA PELICULA NO FUE ENCONTRADA \n");
                    leerf.close();
                    break;
                case 3:
                    
                    File fichero2 = new File("peliculas.txt");
                    FileReader readerEnt1 = new FileReader(fichero2);
                    BufferedReader leerf1 = new BufferedReader(readerEnt1);
                    System.out.println("\n LISTADO GENERAL DE PELICULAS \n");
                    String control1;
                    System.out.println("nctrl   nombre   espec");
                    while((control1=leerf1.readLine())!=null)
                    {
                            nombre=leerf1.readLine();
                            espec=leerf1.readLine();
                            //edad=Integer.parseInt(leerf1.readLine());
                            //sem=Integer.parseInt(leerf1.readLine());
                            //fundp=Float.parseFloat(leerf1.readLine());
                            //prog1=Float.parseFloat(leerf1.readLine());
                            //prog2=Float.parseFloat(leerf1.readLine());
                            //ed=Float.parseFloat(leerf1.readLine());
                            System.out.println(control1+"   "+nombre+"   "+
                                    espec);
                    }//---------------------------------------------------------fin de while listar
                    leerf1.close();
                    break;
                case 4: 
                    System.out.println("Salida del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida, solo del 1 al 4...");
            }//-----------------------------------------------------------------fin de switch
        }//---------------------------------------------------------------------fin de while        
    }//-------------------------------------------------------------------------fin de void main
}//-----------------------------------------------------------------------------fin del Main