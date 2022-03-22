/**
 * Clase Factory. Encargada de la implementacion del MAP deseado por el usuario.
 * Autor: Elias Alberto Alvarado Raxon - 21808
 * Fecha de creacion: 19/03/2022
 * @version 3
 */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Factory<T,V>
{   
    /** 
     * @param tipo
     * @return Map<T, V>
     */
    public Map<T,V> crear(int tipo) 
    {
        switch (tipo) {
            case 1:
                return new HashMap<T,V>();
            case 2:
                return new TreeMap<T,V>();
            case 3:
                return new LinkedHashMap<T,V>();
            default:
                return null;
        }
    }
}