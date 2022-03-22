/**
 * Clase principal. La cual sera la encargada de interactuar con el usuario
 * Autor: Elias Alberto Alvarado Raxon - 21808
 * Fecha de creacion: 19/03/2022
 * @version 5
 */

import java.util.Scanner;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Principal
{   
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Controlador controlador = new Controlador();

        boolean buclePrincipal = true;
        String ruta = "";
        int respuesta = 0;

        System.out.println("\nPor favor, ingrese la ruta de su archivo de tipo texto.\nRespuesta: ");
        ruta = scanner.nextLine();
        ruta = ruta + "\\ListadoProducto.txt";
        System.out.println(controlador.leerArchivo(ruta));

        int tipo = pregunta("¿Que tipo de MAP desea utilizar?\n1. HashMap.\n2. TreeMap.\n3. LinkedHashMap.\nRespueta: ", 3);
        System.out.println(controlador.usoMap(tipo));

        while(buclePrincipal)
        {
            respuesta = pregunta("\n¿Que desea realizar?\n1. Agregar producto.\n2. Mostrar categoria de un producto.\n3. Mostrar carrito.\n4. Mostrar inventario.\n5. Salir.\nRespuesta: ", 5);
            switch (respuesta) {
                case 1:
                    System.out.println("\n1.");
                    break;

                case 2:
                    System.out.println("\n2.");
                    break;

                case 3:
                    respuesta = pregunta("\n¿Como lo desea visualizar?\n1. Ordenados por tipo.\n2. Sin ordenar.\nRespueta: ", 2);
                    if(respuesta == 1)
                    {
                        System.out.println("\nOrdenados");
                    }
                    else
                    {
                        System.out.println("\nSin ordenar");
                    }
                    break;
                
                case 4:
                    respuesta = pregunta("\n¿Como lo desea visualizar?\n1. Ordenados por tipo.\n2. Sin ordenar.\nRespueta: ", 2);
                    if(respuesta == 1)
                    {
                        System.out.println("\nOrdenados");
                    }
                    else
                    {
                        System.out.println(controlador.verArticulos());
                    }
                    break;
                
                case 5:
                    buclePrincipal = false;
                    break;
            }
        }

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
