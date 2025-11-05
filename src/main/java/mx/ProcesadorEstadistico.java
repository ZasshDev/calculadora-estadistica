import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcesadorEstadistico {

    private final List<IEstrategiaEstadistica> estrategias;

    // Inyección de Dependencias (DIP)
    // Dependemos de la abstracción, no de la concreción.
    public ProcesadorEstadistico(List<IEstrategiaEstadistica> estrategias) {
        this.estrategias = estrategias;
    }

    public Map<String, List<Double>> procesar(List<Integer> numeros) {
        Map<String, List<Double>> resultados = new HashMap<>();

        // OCP en acción: iteramos sobre cualquier estrategia que nos hayan pasado.
        for (IEstrategiaEstadistica estrategia : estrategias) {
            List<Double> resultado = estrategia.calcular(numeros);
            resultados.put(estrategia.getNombre(), resultado);
        }
        
        return resultados;
    }
}