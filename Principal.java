/**
 * Clase principal. La cual sera la encargada de interactuar con el usuario
 * Autor: Elias Alberto Alvarado Raxon - 21808
 * Fecha de creacion: 19/03/2022
 * @version 1
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;



public class Principal
{   
    public static void main(String[] args)
    {
        Archivo archivo = new Archivo();
        Scanner scanner = new Scanner(System.in);

        String ruta = "";
        
        try {
            System.out.println("\nPor favor, ingrese la ruta de su archivo de tipo texto.");
            ruta = scanner.nextLine();
            ruta = ruta + "\\ListadoProducto.txt";
            System.out.println(archivo.crearArchivo(ruta));
            
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("\nNo se pudo leer el documento. Por favor, asegurese que la ruta sea la correcta.");
        }

        ArrayList<String> lista = archivo.leerArchivo();

        String info = "";
        String[] split;
        for(String x: lista)
        {
            split = x.split("\\|");
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            System.out.println("\nCategoria: " + split[0] + " Producto: " + split[1]);
            info += x + "\n";
        }

        //System.out.println(info);
    }  

    
    /** 
     * @param pregunta
     * @param opciones
     * @return int
     */
    public static int pregunta(String pregunta, int opciones)
    {
        boolean bucle = true;
        int respuesta = 0;
        Scanner scanner = new Scanner(System.in);
        try 
        {
            while(bucle)
            {
                System.out.println(pregunta);
                respuesta = scanner.nextInt();
                scanner.nextLine();
                if(respuesta > 0 && respuesta <= opciones) bucle = false;
                else System.out.println("\nRepuesta no valida.\n");
            }    
        } catch (Exception e) {
            System.out.println("\nRepuesta no valida. Ingrese solamente numeros.\n");
            respuesta = pregunta(pregunta, opciones);
        }
        return respuesta;
    }
}
