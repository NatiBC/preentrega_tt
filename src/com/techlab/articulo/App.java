package src.com.techlab.articulo;
import java.util.ArrayList;
import java.util.Scanner;

import src.com.techlab.articulo.model.Articulo;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Articulo> articulos = new ArrayList<>();

        int opcion;

        do {
            System.out.println("\n==========================================");
            System.out.println("   SISTEMA DE GESTIÓN DE ARTÍCULOS");
            System.out.println("==========================================");
            System.out.println("1 - Ingresar nuevo artículo");
            System.out.println("2 - Listar artículos");
            System.out.println("3 - Consultar un artículo");
            System.out.println("4 - Modificar un artículo");
            System.out.println("5 - Eliminar un artículo");
            System.out.println("0 - Salir");
            System.out.println("==========================================");

            opcion = leerEntero(scanner, "Por favor, ingrese una opción: ");

            switch (opcion) {
                case 1:
                    ingresarArticulo(scanner, articulos);
                    break;
                case 2:
                    listarArticulos(articulos);
                    break;
                case 3:
                    consultarArticulo(scanner, articulos);
                    break;
                case 4:
                    modificarArticulo(scanner, articulos);
                    break;
                case 5:
                    eliminarArticulo(scanner, articulos);
                    break;
                case 0:
                    System.out.println("\nSaliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nError: la opción ingresada no es válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }
    
    public static void ingresarArticulo(Scanner s, ArrayList<Articulo> listaArticulos) {
    
        System.out.println("\n--- INGRESAR ARTÍCULO ---");

        // Pedimos el código utilizando un método auxiliar para validar que sea un número entero.
        int codigo = leerEntero(s, "Ingrese el código del artículo: ");
    
        // Pedimos la descripción utilizando un método auxiliar.
        String nombre = leerTextoNoVacio(s, "Ingrese el nombre del artículo: ");

        // Pedimos el precio utilizando un método auxiliar para validar que sea un número decimal.
        double precio = leerDouble(s, "Ingrese el precio del artículo: ");

        // Antes de agregar el artículo, validamos que no exista ya en la lista.
        if (existeArticulo(listaArticulos, nombre)) {
            System.out.println("Error: el artículo ya existe en el sistema.");
            return;
        }

        Articulo articulo = new Articulo(codigo, nombre, precio);

        System.out.println( "Codigo: "+ articulo.getCodigo());
        articulo.setNombre(nombre);
        articulo.setPrecio(precio);

        // Si no existe, lo agregamos a la lista.
        articulo.add(articulo);
        // listaArticulos.add(nombre);    
    
        System.out.println("Artículo ingresado correctamente.");
    }

    public static void listarArticulos(ArrayList<Articulo> articulos) {

        System.out.println("\n--- LISTADO DE ARTÍCULOS ---");

        // Si la lista está vacía, no hay nada que mostrar.
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        // Recorremos la lista usando un for tradicional para mostrar también la posición.
        for (int i = 0; i < articulos.size(); i++) {
            System.out.println((i + 1) + " - " + articulos.get(i));
        }
    }
    
    public static void consultarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {

        System.out.println("\n--- CONSULTAR ARTÍCULO ---");

        // Si la lista está vacía, no tiene sentido pedir búsqueda.
        if (articulos.isEmpty()) {
            System.out.println("No hay ningún artículo en la base de datos.");
            return;
        }

        String nombreBuscado = leerTextoNoVacio(scanner, "Ingrese el nombre del artículo que desea buscar: ");

        int posicion = buscarPosicionArticulo(articulos, nombreBuscado);

        if (posicion == -1) {
            System.out.println("El artículo no se encuentra en la base de datos.");
        } else {
            System.out.println("Artículo encontrado en la posición: " + (posicion + 1));
            System.out.println("Nombre: " + articulos.get(posicion));
        }
    }
    
    public static void modificarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {

        System.out.println("\n--- MODIFICAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            System.out.println("No hay ningún artículo en la base de datos.");
            return;
        }

        String nombreActual = leerTextoNoVacio(scanner, "Ingrese el nombre del artículo a modificar: ");

        int posicion = buscarPosicionArticulo(articulos, nombreActual);

        if (posicion == -1) {
            System.out.println("El artículo no se encuentra en la base de datos.");
            return;
        }

        String nuevaDescripcion = leerTextoNoVacio(scanner, "Ingrese el nuevo nombre del artículo: ");

        // Si la nueva descripción ya existe y no es exactamente el mismo artículo,
        // no permitimos la modificación.
        if (existeArticulo(articulos, nuevaDescripcion) &&
                !articulos.get(posicion).equalsIgnoreCase(nuevaDescripcion)) {
            System.out.println("Error: ya existe otro artículo con ese nombre.");
            return;
        }

        // Reemplazamos el valor viejo por el nuevo.
        articulos.get(posicion).setNombre(nuevaDescripcion);

        System.out.println("Artículo modificado correctamente.");
    }
    
    public static void eliminarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {

        System.out.println("\n--- ELIMINAR ARTÍCULO ---");

        if (articulos.isEmpty()) {
            System.out.println("No hay ningún artículo en la base de datos.");
            return;
        }

        String nombreAEliminar = leerTextoNoVacio(scanner, "Ingrese el nombre del artículo a eliminar: ");

        int posicion = buscarPosicionArticulo(articulos, nombreAEliminar);

        if (posicion == -1) {
            System.out.println("No se encontró ningún artículo con ese nombre.");
            return;
        }
        // Pedimos confirmación antes de eliminar.
        try {
            confirmarEliminar(scanner, articulos.get(posicion));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return; // Salimos del método sin eliminar nada.
        }
        // Eliminamos el elemento según su posición.
        articulos.remove(posicion);

        System.out.println("Artículo eliminado correctamente.");
    }
    
    public static boolean existeArticulo(ArrayList<Articulo> articulos, String nombre) {
        // buscar una alternativa a este method con algun method de ArrayList, como contains, pero no es case insensitive, entonces lo hacemos a mano
        for (Articulo articulo : articulos) {
            if (articulo.getNombre().equalsIgnoreCase(nombre.trim())){
                return true;
            }
        }

        return false;
    }

    public static void confirmarEliminar(Scanner scanner, String nombre) {
        while (true) {
            System.out.print("¿Está seguro que desea eliminar el artículo '" + nombre + "'? (S/N): ");
            String respuesta = scanner.nextLine().trim().toUpperCase();

            if (respuesta.equals("S")) {
                return; // Confirmación positiva, se procede a eliminar
            } else if (respuesta.equals("N")) {
                throw new RuntimeException("Eliminación cancelada por el usuario."); // Cancelación, se lanza una excepción para salir del proceso
            } else {
                System.out.println("Respuesta no válida. Por favor, ingrese 'S' para sí o 'N' para no.");
            }
        }
    }

    
    public static int buscarPosicionArticulo(ArrayList<Articulo> articulos, String nombre) {

        for (int i = 0; i < articulos.size(); i++) {
            if (articulos.get(i).getNombre().equalsIgnoreCase(nombre.trim())) {
                return i;
            }
        }

        return -1;
    }

    public static int leerEntero(Scanner scanner, String mensaje) {
       // mientras sea true, el programa seguirá pidiendo un número hasta que el usuario ingrese uno válido
        while (true) {
            // manejo de excepcion
            // el bloque try intenta ejecutar el código que puede generar una excepción
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            // el bloque catch captura la excepción específica que se produce cuando el usuario ingresa un texto que no se puede convertir a entero
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero válido.");
            }
        }
    }

    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {

        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine();
            // texto.trim() elimina los espacios en blanco al principio y al final del texto
            // isEmpty() verifica si el texto está vacío después de eliminar los espacios
            // !true -> false
            // !texto.trim().isEmpty() -> true si el texto no está vacío
            if (!texto.trim().isEmpty()) {
                // voy a retornar a donde fue llamada esta funcion, el texto sin espacios al principio ni al final
                return texto.trim();
            }
             // este mensaje solo se mostrara si el texto esta vacio luego de sacarle los espacios
            System.out.println("Error: el texto no puede estar vacío.");
        }
    }

    public static double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número decimal válido.");
            }
        }
    }
}

