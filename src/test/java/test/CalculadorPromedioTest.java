import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class CalculadorPromedioTest {

    private final CalculadorPromedio calculador = new CalculadorPromedio();

    @Test
    void testPromedioListaSimple() {
        List<Double> resultado = calculador.calcular(List.of(1, 2, 3, 4, 5));
        assertEquals(1, resultado.size());
        assertEquals(3.0, resultado.get(0));
    }
    
    @Test
    void testPromedioListaConNegativos() {
         List<Double> resultado = calculador.calcular(List.of(10, -5));
         assertEquals(2.5, resultado.get(0));
    }

    @Test
    void testPromedioListaVacia() {
        List<Double> resultado = calculador.calcular(List.of());
        assertTrue(resultado.isEmpty());
    }
}