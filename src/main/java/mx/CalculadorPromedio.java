import java.util.Collections;
import java.util.List;

public class CalculadorPromedio implements IEstrategiaEstadistica {

    @Override
    public String getNombre() {
        return "Promedio";
    }

    @Override
    public List<Double> calcular(List<Integer> numeros) {
        if (numeros == null || numeros.isEmpty()) {
            return Collections.emptyList();
        }

        double sum = 0;
        for (int num : numeros) {
            sum += num;
        }
        
        return List.of(sum / numeros.size());
    }
}