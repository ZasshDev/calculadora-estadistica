import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class CalculadorModaTest {

    private final CalculadorModa calculador = new CalculadorModa();

    @Test
    void testModaUnica() {
        List<Double> resultado = calculador.calcular(List.of(1, 2, 2, 3, 3, 2, 4));
        assertEquals(List.of(2.0), resultado);
    }

    @Test
    void testModaMultiple() {
        // Bimodal
        List<Double> resultado = calculador.calcular(List.of(1, 1, 2, 2, 3));
        // Esperamos [1.0, 2.0] (asegúrate de que la implementación los ordene)
        assertEquals(List.of(1.0, 2.0), resultado);
    }

    @Test
    void testSinModa() {
        // Todos tienen la misma frecuencia (1)
        List<Double> resultado = calculador.calcular(List.of(1, 2, 3, 4));
        assertTrue(resultado.isEmpty());
    }
}