/**
 * Clase Controlador. Sera el encargado de manejar todo lo que el usuario solicite
 * Autor: Elias Alberto Alvarado Raxon - 21808
 * Fecha de creacion: 21/03/2022
 * @version 10
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Controlador
{
    private Archivo archivo = new Archivo();
    private Factory<String, ArrayList<String>> mapFactory = new Factory<>();
    private Map<String, ArrayList<String>> inventario, carrito;

    /** 
     * @param tipo
     * @return String
     */
    public String usoMap(int tipo)
    {
        this.inventario = this.mapFactory.crear(tipo);
        this.carrito = this.mapFactory.crear(tipo);
        if(tipo == 1) return "\n---Utilizando HashMap---";
        else if(tipo == 2) return "\n---Utilizando TreeMap---";
        else return "\n---Utilizando LinkedHashMap---";
    }
    
    /** 
     * @param categoria
     * @return String
     */
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
    
    /** 
     * @param producto
     * @return String
     */
    public String categoriaProducto(String producto)
    {
        for(String categoria: this.inventario.keySet())
        {
            ArrayList<String> productos = this.inventario.get(categoria);
            for(String productoInventario: productos)
            {
                if(producto.equals(productoInventario)) return "\nEl producto:" + producto + " pertenece a la categoria: " + categoria;
            }
        }
        return "\nNo se encontro la categoria para el producto indicado, probablemente este mal ingresado.";
    }
    
    /** 
     * @param ordenado
     * @return String
     */
    public String datosProducto(boolean ordenado)
    {
        String info = "";
        int contador = 1;
        if(ordenado)
        {
            for(String categoria: this.ordenados(this.carrito))
            {
                ArrayList<String> productos = this.carrito.get(categoria);
                Set<String> hashSet = new HashSet<String>(productos);
                ArrayList<String> sinDuplicados = new ArrayList<String>();
                sinDuplicados.addAll(hashSet);
                for(String producto: sinDuplicados)
                {
                    int repeticiones = Collections.frequency(productos, producto);
                    info += "\n" + contador + ". Producto: " + producto + "\n\tCategoria: " + categoria + "\n\tCantidad: " + repeticiones;
                    contador++;
                }
            }
        }
        else
        {
            for(String categoria: this.carrito.keySet())
            {
                ArrayList<String> productos = this.carrito.get(categoria);
                Set<String> hashSet = new HashSet<String>(productos);
                ArrayList<String> sinDuplicados = new ArrayList<String>();
                sinDuplicados.addAll(hashSet);
                for(String producto: sinDuplicados)
                {
                    int repeticiones = Collections.frequency(productos, producto);
                    info += "\n" + contador + ". Producto: " + producto + "\n\tCategoria: " + categoria + "\n\tCantidad: " + repeticiones;
                    contador++;
                }
            }
        }

        return info;
    }
    
    /** 
     * @param map
     * @return ArrayList<String>
     */
    public ArrayList<String> ordenados(Map<String, ArrayList<String>> map)
    {
        ArrayList<String> categorias = new ArrayList<String>(map.keySet());
        Collections.sort(categorias, new OrdenarCategoria());
        return categorias;
    }
    
    /** 
     * @param categoria
     * @param producto
     * @return String
     */
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
                else info = "\nNo se cuenta con el producto indicado.\nQuizas esta lista le ayude:" + productosCategoria(categoria);
            }
        }
        else info = "\nNo se cuenta con la categoria indicada.";

        return info;
    }
    
    /** 
     * @param ruta
     * @return String
     */
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
                if(x != 0) productosCategoria = this.inventario.get(split[0]);
            }
            productosCategoria.add(split[1]);

            this.inventario.put(split[0], productosCategoria);
        }

    }
    
    /** 
     * @param map
     * @param ordenado
     * @return String
     */
    public String verArticulos(Map<String, ArrayList<String>> map, boolean ordenado)
    {
        String info = "";
        int contador = 1;
        
        if(ordenado)
        {
            for(String categoria: ordenados(map))
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
        }
        else
        {
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
        }
        return info;
    }
    
    /** 
     * @return Map<String, ArrayList<String>>
     */
    public Map<String, ArrayList<String>> getInventario()
    {
        return this.inventario;
    }
    
    /** 
     * @return Map<String, ArrayList<String>>
     */
    public Map<String, ArrayList<String>> getCarrito()
    {
        return this.carrito;
    }
}