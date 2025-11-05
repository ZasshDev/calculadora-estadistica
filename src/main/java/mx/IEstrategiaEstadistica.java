import java.util.List;

/**
 * Interfaz para cualquier estrategia de cálculo estadístico.
 * Cumple con OCP y DIP.
 */
public interface IEstrategiaEstadistica {
    
    /**
     * Obtiene el nombre de la estadística (ej. "Promedio").
     */
    String getNombre();

    /**
     * Calcula el valor estadístico para una lista de números.
     * Devuelve una Lista<Double> porque la moda puede tener múltiples valores.
     * El promedio y la mediana devolverán una lista con un solo elemento.
     */
    List<Double> calcular(List<Integer> numeros);
}