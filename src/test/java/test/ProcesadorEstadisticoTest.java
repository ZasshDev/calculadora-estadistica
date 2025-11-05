import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

class ProcesadorEstadisticoTest {

    @Test
    void testProcesamientoCompleto() {
        // Creamos las dependencias (estrategias)
        List<IEstrategiaEstadistica> estrategias = List.of(
            new CalculadorPromedio(),
            new CalculadorMediana(),
            new CalculadorModa()
        );
        
        // Inyectamos las dependencias
        ProcesadorEstadistico procesador = new ProcesadorEstadistico(estrategias);

        List<Integer> datos = List.of(1, 2, 3, 4, 5, 5, 6);

        // Act
        Map<String, List<Double>> resultados = procesador.procesar(datos);

        // Assert
        assertEquals(3, resultados.size()); // Se procesaron 3 estrategias
        assertEquals(List.of(3.7142857142857144), resultados.get("Promedio"));
        assertEquals(List.of(4.0), resultados.get("Mediana"));
        assertEquals(List.of(5.0), resultados.get("Moda"));
    }
}