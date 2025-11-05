import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculadorMediana implements IEstrategiaEstadistica {

    @Override
    public String getNombre() {
        return "Mediana";
    }

    @Override
    public List<Double> calcular(List<Integer> numeros) {
        if (numeros == null || numeros.isEmpty()) {
            return Collections.emptyList();
        }

        // Creamos una copia para no modificar la lista original
        List<Integer> sortedList = new ArrayList<>(numeros);
        Collections.sort(sortedList);

        int size = sortedList.size();
        double mediana;

        if (size % 2 == 1) {
            // Tamaño impar
            mediana = sortedList.get(size / 2);
        } else {
            // Tamaño par
            int medio1 = sortedList.get(size / 2 - 1);
            int medio2 = sortedList.get(size / 2);
            mediana = (medio1 + medio2) / 2.0;
        }
        
        return List.of(mediana);
    }
}