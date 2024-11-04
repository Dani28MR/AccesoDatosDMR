import jakarta.xml.bind.JAXBException;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {
        // Crear estudiantes
        Estudiante estudiante1 = new Estudiante("Juan", 20);
        Estudiante estudiante2 = new Estudiante("Ana", 22);
        Estudiante estudiante3 = new Estudiante("Luis", 21);

        // Crear una lista de estudiantes y un objeto Curso
        List<Estudiante> listaEstudiantes = Arrays.asList(estudiante1, estudiante2, estudiante3);
        Curso curso = new Curso("Matemáticas", listaEstudiantes);

        try {
            // Archivo XML de salida para la serialización
            File file = new File("curso.xml");

            // Serializar el objeto Curso en XML
            JAXBUtil.marshalCurso(curso, file);
            System.out.println("Curso serializado a curso.xml");

            // Deserializar el objeto Curso desde el archivo XML
            Curso cursoDeserializado = JAXBUtil.unmarshalCurso(file);
            System.out.println("Curso deserializado desde curso.xml");

            // Imprimir los detalles del curso deserializado
            System.out.println("Nombre del Curso: " + cursoDeserializado.getNombreCurso());
            System.out.println("Lista de Estudiantes:");
            for (Estudiante estudiante : cursoDeserializado.getEstudiantes()) {
                System.out.println(" - Nombre: " + estudiante.getNombre() + ", Edad: " + estudiante.getEdad());
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
