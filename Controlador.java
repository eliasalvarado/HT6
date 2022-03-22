/**
 * Clase Controlador. Sera el encargado de manejar todo lo que el usuario solicite
 * Autor: Elias Alberto Alvarado Raxon - 21808
 * Fecha de creacion: 21/03/2022
 * @version 5
 */

import java.util.ArrayList;
import java.util.Map;

public class Controlador
{
    private Archivo archivo = new Archivo();
    private Factory<String, ArrayList<String>> mapFactory;
    private Map<String, ArrayList<String>> inventario;
    private Map<String, ArrayList<String>> carrito;

    public String usoMap(int tipo)
    {
        mapFactory = new Factory<>(tipo);
        this.inventario = this.mapFactory.getInstance();
        this.carrito = this.mapFactory.getInstance();
        if(tipo == 1) return "\n---Utilizando HashMap---";
        else if(tipo == 2) return "\n---Utilizando TreeMap---";
        else return "\n---Utilizando LinkedHashMap---";
    }

    public String leerArchivo(String ruta)
    {
        try {
            return archivo.crearArchivo(ruta);
            
        } catch (Exception e) {
            //TODO: handle exception
            return "\nNo se pudo leer el documento. Por favor, asegurese que la ruta sea la correcta.";
        }
    }

    private void convertirInventario()
    {
        ArrayList<String> lista = archivo.leerArchivo();
        String categoriaAnterior = "";
        String[] split;
        String[] split2;
        for(int x = 0; x < lista.size(); x++)
        {
            ArrayList<String> productosCategoria = new ArrayList<String>();
            if(x == 0) split2 = lista.get(x).split("\\|");
            else split2 = lista.get(x - 1).split("\\|");
            categoriaAnterior = split2[0].trim();

            split = lista.get(x).split("\\|");
            split[0] = split[0].trim();
            split[1] = split[1].trim();

            if(split[0].equals(categoriaAnterior))
            {
                if(x != 0) productosCategoria = inventario.get(split[0]);
            }
            productosCategoria.add(split[1]);

            this.inventario.put(split[0], productosCategoria);
        }

    }

    public String verArticulos()
    {        
        convertirInventario();

        String info = "";
        int contador = 1;
        
        for(String categoria: this.inventario.keySet())
        {
            info += "\n" + categoria;
            contador = 1;
            ArrayList<String> productos = this.inventario.get(categoria);
            for(String producto: productos)
            {
                info += "\n\t\t" + contador + ". " + producto;
                contador++;
            }
            
        }
        return info;
    }
}
