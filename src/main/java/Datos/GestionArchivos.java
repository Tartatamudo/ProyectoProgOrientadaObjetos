package Datos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestionArchivos {
    //crear un archivo
    public static void CrearArchivo(String ruta, String contenido) {
        Path archivo = Paths.get(ruta);
        try {
            Files.write(archivo, contenido.getBytes());
            //System.out.println("El archivo fue creado exitosamente");
        } catch (IOException e) {
            //System.out.println("El archivo no pudo ser creado");
        }
    }

    //nuevaLinea
    public static void NuevaLinea(String ruta, String contenido) {
        String oldContenido = LeerArchivo(ruta);
        CrearArchivo(ruta, oldContenido + "\n" + contenido);
    }

    //leer un archivo
    public static String LeerArchivo(String ruta) {
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
