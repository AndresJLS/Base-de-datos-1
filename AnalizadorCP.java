import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

public class AnalizadorCP {
    public static void main(String[] args) {
        String rutaArchivo = "codigos_postales_hmo.csv";
        Map<String, Integer> conteoCP = new TreeMap<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = lector.readLine()) != null) {
                // Mostrar lo que se está leyendo
                System.out.println(" Línea leída: " + linea);

                // Omitir encabezado
                if (primeraLinea && linea.toLowerCase().contains("asentamiento")) {
                    primeraLinea = false;
                    continue;
                }

                String[] partes = linea.split(",");//divide la línea en partes usando la coma

                if (partes.length >= 2) {
                    String codigoPostal = partes[0].trim();

                    conteoCP.put(codigoPostal, conteoCP.getOrDefault(codigoPostal, 0) + 1);
                }
            }
        } catch (Exception e) {
            System.out.println(" Error al leer el archivo:");
            e.printStackTrace();
            return;
        }

        // Ordenar por código postal usando TreeMap
        Map<String, Integer> conteoOrdenado = new TreeMap<>(conteoCP);

        // esto solo es por orden en el formato 
        System.out.println("\n Resultados:");
        if (conteoOrdenado.isEmpty()) {
            System.out.println(" No se encontraron códigos postales en el archivo.");
        } else {
            for (Map.Entry<String, Integer> entrada : conteoOrdenado.entrySet()) {
                System.out.printf("Código postal: %-6s - Número de asentamientos: %d%n", entrada.getKey(), entrada.getValue());
            }
        }
    }
}
