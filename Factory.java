/**
 * Clase Factory. Encargada de la implementacion del MAP deseado por el usuario.
 * Autor: Elias Alberto Alvarado Raxon - 21808
 * Fecha de creacion: 19/03/2022
 * @version 2
 */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Factory<T,V>
{
    private Map<T,V> map;

    public Factory(int tipo) 
    {
        switch (tipo) {
            case 1:
                this.map = new HashMap<T,V>();
            case 2:
                this.map = new TreeMap<T,V>();
            case 3:
                this.map = new LinkedHashMap<T,V>();
        }
    }

    public Map<T,V> getInstance()
    {
        return this.map;
    }
}
