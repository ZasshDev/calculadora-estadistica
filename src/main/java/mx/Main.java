import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    /**
     * Mapeo de banderas de la terminal a las clases de Estrategia.
     * Esto cumple con el Principio Abierto/Cerrado (OCP).
     * Si quieres agregar un nuevo cálculo con la bandera "-r" (Rango),
     * solo necesitas agregarlo a este mapa.
     */
    private static final Map<String, IEstrategiaEstadistica> ESTRATEGIAS_DISPONIBLES = new HashMap<>();
    static {
        ESTRATEGIAS_DISPONIBLES.put("-p", new CalculadorPromedio());
        ESTRATEGIAS_DISPONIBLES.put("-me", new CalculadorMediana());
        ESTRATEGIAS_DISPONIBLES.put("-mo", new CalculadorModa());
        // Nuevas estrategias:
        // ESTRATEGIAS_DISPONIBLES.put("-r", new CalculadorRango());
    }


    /**
     * El método main ahora lee los argumentos de la línea de comandos (args).
     * args[0] = ruta del archivo
     * args[1...] = banderas (ej. -p, -me, -mo)
     */
    public static void main(String[] args) {
        
        // --- 1. Validar y Parsear Argumentos ---
        if (args.length < 2) {
            System.err.println("Error: Faltan argumentos.");
            System.err.println("Uso: java -jar ... <ruta_del_csv> [banderas...]");
            System.err.println("Banderas disponibles: -p (Promedio), -me (Mediana), -mo (Moda)");
            return;
        }

        String rutaArchivo = args[0];
        List<IEstrategiaEstadistica> estrategiasSeleccionadas = new ArrayList<>();

        for (int i = 1; i < args.length; i++) {
            String bandera = args[i];
            if (ESTRATEGIAS_DISPONIBLES.containsKey(bandera)) {
                estrategiasSeleccionadas.add(ESTRATEGIAS_DISPONIBLES.get(bandera));
            } else {
                System.err.println("Advertencia: Bandera desconocida '" + bandera + "' será ignorada.");
            }
        }

        if (estrategiasSeleccionadas.isEmpty()) {
            System.err.println("Error: No se seleccionó ninguna operación válida.");
            return;
        }

        // --- 2. Cargar Datos ---
        CargadorDatosCSV cargador = new CargadorDatosCSV();
        List<Integer> datos;

        try {
            datos = cargador.cargarNumeros(rutaArchivo);
            if (datos.isEmpty()) {
                System.out.println("No se encontraron datos numéricos en el archivo.");
                return;
            }
            System.out.println("Datos cargados desde " + rutaArchivo + ": " + datos.size() + " números.");

        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV '" + rutaArchivo + "': " + e.getMessage());
            return;
        }

        // --- 3. Inyectar y Procesar ---
        // El procesador recibe solo las estrategias que el usuario pidió.
        ProcesadorEstadistico miProcesador = new ProcesadorEstadistico(estrategiasSeleccionadas);
        Map<String, List<Double>> resultados = miProcesador.procesar(datos);

        // --- 4. Mostrar Resultados ---
        System.out.println("--- Resultados ---");
        resultados.forEach((nombre, valor) -> {
            System.out.println(nombre + ": " + valor);
        });
    }
}