import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AnalizadorLog {

    private Map<String, Integer> contadorLogs = new HashMap<>();
    private Map<String, Integer> errores = new HashMap<>();

    public void analizarArchivo(String archivoLog) throws IOException {
        // Inicializar contador de niveles de log
        contadorLogs.put("INFO", 0);
        contadorLogs.put("WARNING", 0);
        contadorLogs.put("ERROR", 0);

        // Leer archivo línea por línea
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoLog))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Separar la línea por comas
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nivelLog = partes[1];
                    String mensaje = partes[2];

                    // Contar la ocurrencia del nivel de log
                    contadorLogs.put(nivelLog, contadorLogs.getOrDefault(nivelLog, 0) + 1);

                    // Si es un error, agregar a la lista de errores
                    if ("ERROR".equals(nivelLog)) {
                        errores.put(mensaje, errores.getOrDefault(mensaje, 0) + 1);
                    }
                }
            }
        }
    }

    public void generarInforme(String archivoInforme) throws IOException {
        try (FileWriter writer = new FileWriter(archivoInforme)) {
            writer.write("--- Informe de Logs ---\n");
            writer.write("Niveles de log:\n");
            for (String nivel : contadorLogs.keySet()) {
                writer.write(nivel + ": " + contadorLogs.get(nivel) + "\n");
            }

            writer.write("\nErrores más comunes:\n");
            errores.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(5)
                    .forEach(entry -> {
                        try {
                            writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }
    public static void main(String[] args) {
        AnalizadorLog analizador = new AnalizadorLog();
        String archivoLog = "log.txt";
        String archivoInforme = "informe.txt";

        try {
            analizador.analizarArchivo(archivoLog);
            analizador.generarInforme(archivoInforme);
            System.out.println("Informe generado: " + archivoInforme);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al procesar el archivo de log: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
