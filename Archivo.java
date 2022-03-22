/**
 * Clase Archivo. La cual se encagara de manejar el archivo de tipo txt
 * Autor: Elias Alberto Alvarado Raxon - 21808
 * Fecha de creacion: 19/03/2022
 * @version 3
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Archivo
{
    private File archivo;

	/** 
	 * @param ruta
	 * @return String
	 */
	public String crearArchivo(String ruta)
    {
        try {
            this.archivo = new File(ruta);
            return "\nArchivo leido con exito.";
        } catch (Exception e) {
            //TODO: handle exception
            return "\nOcurrio un error al intentar crear el archivo.";
        }
    }
    
	/** 
	 * @param cantidad
	 */
	public void agregarNumeros(int cantidad)
    {
		FileWriter flwriter = null;
        int x = 0;
        Random random = new Random();
		try {
			flwriter = new FileWriter(this.archivo);
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			while(x < cantidad)
            {
				bfwriter.write(random.nextInt(999999) + "\n");
                x++;
			}
			//cierra el buffer intermedio
			bfwriter.close();
			System.out.println("Escritura realizada satisfactoriamente.");
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {
					flwriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** 
	 * @return ArrayList<Integer>
	 */
	public ArrayList<String> leerArchivo()
	{
		try {
			ArrayList<String> lista = new ArrayList<String>();	
			Scanner scanner;
			try {
				scanner = new Scanner(this.archivo, "UTF-8");
				while (scanner.hasNextLine())
				{
					lista.add(scanner.nextLine());
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return lista;
		} catch (Exception e) {
			//TODO: handle exception
			return null;
		}
	}
}
