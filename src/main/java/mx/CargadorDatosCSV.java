import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase tiene la Responsabilidad Única (SRP)
 * de cargar y parsear números desde un archivo CSV.
 */
public class CargadorDatosCSV {

    /**
     * Lee un archivo CSV y extrae todos los campos numéricos que encuentra.
     *
     * @param rutaArchivo La ruta al archivo .csv
     * @return Una lista de todos los números encontrados.
     * @throws IOException Si hay un error al leer el archivo.
     */
    public List<Integer> cargarNumeros(String rutaArchivo) throws IOException {
        List<Integer> numeros = new ArrayList<>();

        // Usamos try-with-resources para asegurar que el 'reader' y 'parser' se cierren
        try (
            Reader reader = Files.newBufferedReader(Paths.get(rutaArchivo), StandardCharsets.UTF_8);
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT)
        ) {
            
            // Itera sobre cada fila (CSVRecord)
            for (CSVRecord record : parser) {
                // Itera sobre cada columna (campo) en la fila
                for (String campo : record) {
                    try {
                        // Intenta convertir el campo a número
                        int numero = Integer.parseInt(campo.trim());
                        numeros.add(numero);
                    } catch (NumberFormatException e) {
                        // Si un campo no es un número (ej. un encabezado), lo ignora
                        System.err.println("Valor ignorado (no numérico): " + campo);
                    }
                }
            }
        }
        
        return numeros;
    }
}