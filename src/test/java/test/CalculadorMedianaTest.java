import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class CalculadorMedianaTest {

    private final CalculadorMediana calculador = new CalculadorMediana();

    @Test
    void testMedianaListaImpar() {
        List<Double> resultado = calculador.calcular(List.of(5, 1, 3));
        assertEquals(1, resultado.size());
        assertEquals(3.0, resultado.get(0));
    }

    @Test
    void testMedianaListaPar() {
        List<Double> resultado = calculador.calcular(List.of(1, 4, 2, 3));
        assertEquals(1, resultado.size());
        assertEquals(2.5, resultado.get(0)); // (2+3)/2
    }
    
    @Test
    void testMedianaNoModificaListaOriginal() {
        List<Integer> original = new java.util.ArrayList<>(List.of(5, 1, 3));
        calculador.calcular(original);
        // La lista original no debe haber sido ordenada
        assertEquals(List.of(5, 1, 3), original);
    }
}