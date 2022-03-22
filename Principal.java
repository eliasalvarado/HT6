/**
 * Clase principal. La cual sera la encargada de interactuar con el usuario
 * Autor: Elias Alberto Alvarado Raxon - 21808
 * Fecha de creacion: 19/03/2022
 * @version 9
 */

import java.util.Scanner;

public class Principal
{   
    /** 
     * @param args
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Controlador controlador = new Controlador();

        boolean buclePrincipal = true;
        boolean bucle = true;
        String ruta = "";
        int respuesta = 0;
        String categoria;
        String producto;

        int tipo = pregunta("\n¿Que tipo de MAP desea utilizar?\n1. HashMap.\n2. TreeMap.\n3. LinkedHashMap.\nRespueta: ", 3);
        System.out.println(controlador.usoMap(tipo));

        while(bucle)
        {
            try {
                System.out.println("\nPor favor, ingrese la ruta de su archivo de tipo texto.\nRespuesta: ");
                ruta = scanner.nextLine();
                ruta = ruta + "\\ListadoProducto.txt";
                controlador.leerArchivo(ruta);
                
                if(controlador.comprobarArchivo())
                {
                    bucle = false; 
                    System.out.println(controlador.leerArchivo(ruta));
                }
                else
                {
                    bucle = false;
                    System.out.println("\nHa ocurrido un error al intentar leer el archivo.");
                    if(pregunta("¿Desea intentarlo de nuevo?\n1. Si.\n2. No.\nRespuesta: ", 2) == 1)
                    {
                        bucle = true; 
                    }
                    else buclePrincipal = false;
                }
            } catch (Exception e) {
                //TODO: handle exception
                bucle = false;
                    System.out.println("\nHa ocurrido un error al intentar leer el archivo.");
                    if(pregunta("¿Desea intentarlo de nuevo?\n1. Si.\n2. No.\nRespuesta: ", 2) == 1)
                    {
                        bucle = true; 
                    }
                    else buclePrincipal = false;
            }
        }    
        
        while(buclePrincipal)
        {
            controlador.convertirInventario();
            respuesta = pregunta("\n¿Que desea realizar?\n1. Agregar producto.\n2. Mostrar categoria de un producto.\n3. Mostrar carrito.\n4. Mostrar inventario.\n5. Salir.\nRespuesta: ", 5);
            switch (respuesta) {
                case 1:
                    System.out.println("\nIngrese la categoria del producto que desee agregar.\nRespuesta: ");
                    categoria = scanner.nextLine();
                    System.out.println("\nIngrese el producto que desee agregar.\nRespuesta: ");
                    producto = scanner.nextLine();
                    System.out.println(controlador.agregarProducto(categoria.trim(), producto.trim()));
                    break;

                case 2:
                    System.out.println("\nIngrese el producto que desee buscar.\nRespuesta: ");
                    producto = scanner.nextLine();
                    System.out.println(controlador.categoriaProducto(producto));
                    break;

                case 3:
                    if(controlador.getCarrito().isEmpty()) System.out.println("\nAun no ha agregado ningun producto a su coleccion.");
                    else
                    {
                        respuesta = pregunta("\n¿Como lo desea visualizar?\n1. Ordenados alfabeticamente por categoria.\n2. Sin ordenar.\nRespueta: ", 2);
                        if(respuesta == 1) System.out.println(controlador.datosProducto(true));

                        else System.out.println(controlador.datosProducto(false));

                    }
                    break;
                
                case 4:
                    if(controlador.getInventario().isEmpty()) System.out.println("\nAun no se tiene un inventario creado.");
                    else
                    {
                        respuesta = pregunta("\n¿Como lo desea visualizar?\n1. Ordenados alfabeticamente por categoria.\n2. Sin ordenar.\nRespueta: ", 2);
                        if(respuesta == 1) System.out.println(controlador.verArticulos(controlador.getInventario(), true));

                        else System.out.println(controlador.verArticulos(controlador.getInventario(), false));

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
