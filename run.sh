#!/bin/bash

# --- Script para compilar y ejecutar el proyecto de estadísticas ---

# 1. Chequeo de bandera de ayuda (-h o --help)
#    Iteramos sobre todos los argumentos pasados
for arg in "$@"
do
    if [ "$arg" == "-h" ] || [ "$arg" == "--help" ]; then
        echo "Uso: $0 <ruta_del_csv> [banderas...]"
        echo ""
        echo "Script para calcular estadísticas (Promedio, Mediana, Moda) desde un archivo CSV."
        echo ""
        echo "Argumentos:"
        echo "  <ruta_del_csv>    Ruta al archivo de datos CSV."
        echo ""
        echo "Banderas (al menos una es requerida):"
        echo "  -p                Calcula el Promedio."
        echo "  -me               Calcula la Mediana."
        echo "  -mo               Calcula la Moda."
        echo "  -h, --help        Muestra este mensaje de ayuda."
        echo ""
        echo "Ejemplo:"
        echo "  $0 datos.csv -p -me"
        exit 0 # Salir con éxito después de mostrar la ayuda
    fi
done

# 2. Validar que se pasaron argumentos (al menos un archivo y una bandera)
if [ "$#" -lt 2 ]; then
    echo "Error: Faltan argumentos."
    echo "Uso: $0 <ruta_del_csv> [banderas...]"
    echo "Ejemplo: $0 datos.csv -p -me -mo"
    echo ""
    echo "Ejecuta '$0 -h' para ver el mensaje de ayuda completo."
    exit 1
fi

echo "1/3 - Compilando y empaquetando el proyecto..."

# 3. Compila y empaqueta el proyecto con Maven.
#    Redirigimos la salida a /dev/null para una ejecución limpia.
mvn clean package > /dev/null 2>&1

# 4. Verificar si la compilación fue exitosa
if [ $? -ne 0 ]; then
    echo "Error: La compilación de Maven falló."
    exit 1
fi

echo "2/3 - Paquete JAR creado."

# 5. Encontrar el nombre del archivo JAR (evita hardcodear la versión)
JAR_FILE=$(find target -maxdepth 1 -name "estadisticas-solid-*.jar" | head -n 1)

if [ -z "$JAR_FILE" ]; then
    echo "Error: No se encontró el archivo .jar en la carpeta 'target'."
    exit 1
fi

echo "3/3 - Ejecutando la aplicación..."
echo "------------------------------------------"

# 6. Ejecutar el JAR
#    "$@" pasa TODOS los argumentos que recibió el script (ej. "datos.csv -p -me")
#    directamente a la aplicación de Java.
java -jar "$JAR_FILE" "$@"