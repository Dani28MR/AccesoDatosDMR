import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorContactos gestor = new GestorContactos();
        try {
            gestor.cargarDesdeArchivo();
        } catch (IOException e) {
            System.out.println("Error al cargar contactos: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú de Gestor de Contactos ---");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Introduce el teléfono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Introduce el email: ");
                    String email = scanner.nextLine();
                    Contacto contacto = new Contacto(nombre, telefono, email);
                    try {
                        gestor.agregarContacto(contacto);
                    } catch (IOException e) {
                        System.out.println("Error al guardar contacto: " + e.getMessage());
                    }
                    break;
                case 2:
                    gestor.listarContactos();
                    break;
                case 3:
                    System.out.print("Introduce el nombre del contacto a buscar: ");
                    nombre = scanner.nextLine();
                    Contacto encontrado = gestor.buscarContacto(nombre);
                    if (encontrado != null) {
                        System.out.println("Contacto encontrado: " + encontrado);
                    } else {
                        System.out.println("Contacto no encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Introduce el nombre del contacto a eliminar: ");
                    nombre = scanner.nextLine();
                    try {
                        gestor.eliminarContacto(nombre);
                    } catch (IOException e) {
                        System.out.println("Error al eliminar contacto: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
