import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorContactos {
    private List<Contacto> contactos = new ArrayList<>();
    private final String archivo = "contactos.txt";

    public void agregarContacto(Contacto contacto) throws IOException {
        contactos.add(contacto);
        guardarEnArchivo();
    }

    public void listarContactos() {
        for (Contacto c : contactos) {
            System.out.println(c);
        }
    }

    public Contacto buscarContacto(String nombre) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    public void eliminarContacto(String nombre) throws IOException {
        Contacto contacto = buscarContacto(nombre);
        if (contacto != null) {
            contactos.remove(contacto);
            guardarEnArchivo();
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    private void guardarEnArchivo() throws IOException {
        try (FileWriter writer = new FileWriter(archivo)) {
            for (Contacto c : contactos) {
                writer.write(c.getNombre() + "," + c.getTelefono() + "," + c.getEmail() + "\n");
            }
        }
    }

    public void cargarDesdeArchivo() throws IOException {
        contactos.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    Contacto c = new Contacto(partes[0], partes[1], partes[2]);
                    contactos.add(c);
                }
            }
        }
    }
}
