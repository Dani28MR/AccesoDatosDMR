import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class JAXBUtil {

    // Serialización (marshalling) para Estudiante
    public static void marshal(Estudiante estudiante, File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Estudiante.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(estudiante, file);
    }

    // Deserialización (unmarshalling) para Estudiante
    public static Estudiante unmarshal(File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Estudiante.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Estudiante) unmarshaller.unmarshal(file);
    }

    // Serialización (marshalling) para Curso
    public static void marshalCurso(Curso curso, File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Curso.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(curso, file);
    }

    // Deserialización (unmarshalling) para Curso
    public static Curso unmarshalCurso(File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Curso.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Curso) unmarshaller.unmarshal(file);
    }
}