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
    private Factory<String, ArrayList<String>> mapFactory = new Factory<>();
    private Map<String, ArrayList<String>> inventario, carrito;

    public String usoMap(int tipo)
    {
        this.inventario = this.mapFactory.crear(tipo);
        this.carrito = this.mapFactory.crear(tipo);
        if(tipo == 1) return "\n---Utilizando HashMap---";
        else if(tipo == 2) return "\n---Utilizando TreeMap---";
        else return "\n---Utilizando LinkedHashMap---";
    }

    public String productosCategoria(String categoria)
    {
        String info = "";
        int contador = 1;
        if(this.inventario.containsKey(categoria))
        {
            ArrayList<String> productos = this.inventario.get(categoria);
            for(String producto: productos)
            {
                info += "\n\t" + contador + ". " + producto;
                contador++;
            }
        }
        else info = "\nNo se cuenta con la categoria.";

        return info;
    }


    public String agregarProducto(String categoria, String producto)
    {
        String info = "";
        if(this.inventario.containsKey(categoria))
        {
            ArrayList<String> productosInventario = this.inventario.get(categoria);
            for(String productoInventario: productosInventario)
            {
                if(producto.equals(productoInventario))
                {
                    ArrayList<String> productosCarrito = new ArrayList<>();
                    if(this.carrito.containsKey(categoria)) productosCarrito = carrito.get(categoria);
                    productosCarrito.add(producto);
                    this.carrito.put(categoria, productosCarrito);
                    info = "\nSe ha agregado el producto: " + producto + " a su coleccion.";
                    break;
                }
                else info = "\nNo se cuenta con el producto indicado.";
            }
        }
        else info = "\nNo se cuenta con la categoria indicada.";

        return info;
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

    public void convertirInventario()
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

    public String verArticulos(Map<String, ArrayList<String>> map)
    {
        String info = "";
        int contador = 1;
        
        for(String categoria: map.keySet())
        {
            info += "\n" + categoria;
            contador = 1;
            ArrayList<String> productos = map.get(categoria);
            for(String producto: productos)
            {
                info += "\n\t" + contador + ". " + producto;
                contador++;
            }
            
        }
        return info;
    }

    public Map<String, ArrayList<String>> getInventario()
    {
        return this.inventario;
    }

    public Map<String, ArrayList<String>> getCarrito()
    {
        return this.carrito;
    }
}
