import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculadorModa implements IEstrategiaEstadistica {

    @Override
    public String getNombre() {
        return "Moda";
    }

    @Override
    public List<Double> calcular(List<Integer> numeros) {
        if (numeros == null || numeros.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Integer, Integer> frecuencias = new HashMap<>();
        for (int num : numeros) {
            frecuencias.put(num, frecuencias.getOrDefault(num, 0) + 1);
        }

        int maxFrecuencia = 0;
        for (int frec : frecuencias.values()) {
            if (frec > maxFrecuencia) {
                maxFrecuencia = frec;
            }
        }

        // Si todos los números aparecen la misma cantidad de veces, no hay moda (o todos son la moda)
        // Para este ejemplo, si maxFrecuencia es 1 y hay más de un número, no hay moda.
        if (maxFrecuencia == 1 && frecuencias.size() > 1) {
             return Collections.emptyList(); // O devolver todos, según la definición que prefieras
        }

        List<Double> modas = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frecuencias.entrySet()) {
            if (entry.getValue() == maxFrecuencia) {
                modas.add(entry.getKey().doubleValue());
            }
        }
        
        Collections.sort(modas); // Para consistencia en las pruebas
        return modas;
    }
}