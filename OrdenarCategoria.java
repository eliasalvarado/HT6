import java.util.Comparator;

/**
 * Clase OrdenarCategoria. Implementara el Comparator para poder realizar el ordenamiento de los MAPs alfabeticamente
 * Autor: Elias Alberto Alvarado Raxon - 21808
 * Fecha de creacion: 21/03/2022
 * @version 1
 */

public class OrdenarCategoria implements Comparator<String>
{   
    /** 
     * @param o1
     * @param o2
     * @return int
     */
    @Override
    public int compare(String o1, String o2)
    {
        // TODO Auto-generated method stub
        return o1.compareTo(o2);
    }
}
