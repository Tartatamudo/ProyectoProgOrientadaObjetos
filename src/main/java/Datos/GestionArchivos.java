package Datos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestionArchivos {
    //crear un archivo
    public static void crearArchivo(String ruta, String contenido) {
        Path archivo = Paths.get(ruta);
        try {
            Files.write(archivo, contenido.getBytes());
            //System.out.println("El archivo fue creado exitosamente");
        } catch (IOException e) {
            //System.out.println("El archivo no pudo ser creado");
        }
    }

    //nuevaLinea
    public static void nuevaLinea(String ruta, String contenido) {
        String oldContenido = leerArchivo(ruta);
        crearArchivo(ruta, oldContenido + "\n" + contenido);
    }


    //leer un archivo
    public static String leerArchivo(String ruta) {
        Path archivo = Paths.get(ruta);
        String contenido = "";
        try {
            contenido = new String(Files.readAllBytes(archivo));
        } catch (IOException e) {
            //System.out.println("El archivo no pudo ser leido");
        }
        return contenido;
    }
}
