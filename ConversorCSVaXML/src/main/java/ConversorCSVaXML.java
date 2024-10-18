import com.opencsv.CSVReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.List;

public class ConversorCSVaXML {

    public void convertirCSVaXML(String archivoCSV, String archivoXML) throws IOException, ParserConfigurationException, TransformerException {
        // Leer el archivo CSV
        try (CSVReader csvReader = new CSVReader(new FileReader(archivoCSV))) {
            List<String[]> datos = csvReader.readAll();

            // Crear un documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Elemento raíz <Estudiantes>
            Element rootElement = doc.createElement("Estudiantes");
            doc.appendChild(rootElement);

            // Iterar sobre los datos del CSV (salta la primera fila que es el encabezado)
            for (int i = 1; i < datos.size(); i++) {
                String[] fila = datos.get(i);

                // Crear el elemento <Estudiante>
                Element estudiante = doc.createElement("Estudiante");
                rootElement.appendChild(estudiante);

                // Añadir ID
                Element id = doc.createElement("ID");
                id.appendChild(doc.createTextNode(fila[0]));
                estudiante.appendChild(id);

                // Añadir Nombre
                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(fila[1]));
                estudiante.appendChild(nombre);

                // Añadir Apellido
                Element apellido = doc.createElement("Apellido");
                apellido.appendChild(doc.createTextNode(fila[2]));
                estudiante.appendChild(apellido);

                // Añadir Edad
                Element edad = doc.createElement("Edad");
                edad.appendChild(doc.createTextNode(fila[3]));
                estudiante.appendChild(edad);

                // Añadir Curso
                Element curso = doc.createElement("Curso");
                curso.appendChild(doc.createTextNode(fila[4]));
                estudiante.appendChild(curso);
            }

            // Escribir el archivo XML a disco
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(archivoXML));

            transformer.transform(source, result);
            System.out.println("Archivo XML generado: " + archivoXML);
        }
    }
}
