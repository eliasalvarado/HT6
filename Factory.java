/**
 * Clase Factory. Encargada de la implementacion del MAP deseado por el usuario.
 * Autor: Elias Alberto Alvarado Raxon - 21808
 * Fecha de creacion: 19/03/2022
 * @version 1
 */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Factory<K,V>
{
    public static Map<String, String> constructor(int tipo) 
    {
        switch (tipo) {
            case 1:
                return new HashMap<>();
            case 2:
                return new TreeMap<>();
            case 3:
                return new LinkedHashMap<>();
            default:
                return null;
        }
    }
}
