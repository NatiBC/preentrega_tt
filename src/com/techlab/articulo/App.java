package com.techlab.articulo;
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.utils.Validaciones;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Articulo> articulos = new ArrayList<>();

        int opcion;

        do {
            System.out.println("\n==========================================");
            System.out.println("   SISTEMA DE GESTIÓN DE ARTÍCULOS");
            System.out.println("==========================================");
            System.out.println("\n1 - Ingresar nuevo artículo");
            System.out.println("2 - Listar artículos");
            System.out.println("3 - Consultar un artículo");
            System.out.println("4 - Modificar un artículo");
            System.out.println("5 - Eliminar un artículo");
            System.out.println("6 - Listar categorías");
            System.out.println("0 - Salir");
            System.out.println("\n==========================================");

            opcion = leerEntero(scanner, "\nPor favor, ingrese una opción: ");

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
                case 6:

                    ArrayList<Categoria> todas = new ArrayList<>();

                    todas.addAll(
                        ArticuloElectronico.obtenerCategorias()
                    );

                    todas.addAll(
                        ArticuloAlimenticio.obtenerCategorias()
                    );

                    listarCategoriasExistentes(todas);

                    break;
                case 0:
                    System.out.println("\nSaliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nERROR: la opción ingresada no es válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    
    public static void ingresarArticulo(
            Scanner s,
            ArrayList<Articulo> listaArticulos) {
        System.out.println("\n==========================================");
        System.out.println("--- INGRESAR ARTÍCULO ---");
        System.out.println("==========================================");
        System.out.println("\n1 - Electrónico");
        System.out.println("2 - Alimenticio");
        System.out.println("\n==========================================");

        int tipo = leerEntero(s, "\nSeleccione el tipo de artículo: ");

        // PEDIR DATOS
        int codigo = leerEntero(s, "\nIngrese el código del artículo: ");

        String nombre = leerTextoNoVacio(s, "\nIngrese el nombre del artículo: ");

        double precio = leerDouble(s, "\nIngrese el precio del artículo: ");

        // VALIDAR DUPLICADO
        if (existeArticulo(listaArticulos, nombre)) {
            System.out.println(
                "ERROR: el artículo ya existe en el sistema."
            );
            return;
        }

        // CREAR ARTÍCULO SEGÚN TIPO
        Articulo articulo;

        switch (tipo) {

            case 1:
                // CATEGORÍAS DE ELECTRÓNICOS
                Categoria categoriaElectronico =
                    pedirCategoriaExistente(
                        s,
                        ArticuloElectronico.obtenerCategorias()
                    );

                int garantiaMeses = leerEntero(
                    s,
                    "\nIngrese meses de garantía: "
                );

                articulo = new ArticuloElectronico(
                    codigo,
                    nombre,
                    precio,
                    categoriaElectronico,
                    garantiaMeses
                );

                break;

            case 2:

                // CATEGORÍAS DE ALIMENTOS
                Categoria categoriaAlimento =
                    pedirCategoriaExistente(
                        s,
                        ArticuloAlimenticio.obtenerCategorias()
                    );

                int diasVencimiento = leerEntero(
                    s,
                    "\nIngrese días para vencimiento: "
                );

                articulo = new ArticuloAlimenticio(
                    codigo,
                    nombre,
                    precio,
                    categoriaAlimento,
                    diasVencimiento
                );

                break;

            default:
                System.out.println(
                    "ERROR: tipo de artículo no válido."
                );
                return;
        }

        // AGREGAR A LA LISTA
        listaArticulos.add(articulo);

        System.out.println(
            "\nArtículo ingresado correctamente."
        );
        System.out.println(articulo);
    }
    
    public static void listarArticulos(ArrayList<Articulo> articulos) {
        System.out.println("\n==========================================");
        System.out.println("--- LISTADO DE ARTÍCULOS ---");
        System.out.println("==========================================");
        // Si la lista está vacía, no hay nada que mostrar.
        if (articulos.isEmpty()) {
            System.out.println("\nNo hay artículos cargados.");
            return;
        }
        
        // Recorremos la lista usando un for tradicional para mostrar también la posición.
        for (int i = 0; i < articulos.size(); i++) {
            System.out.println((i + 1) + " - ");
            System.out.println(articulos.get(i));
        }
    }
    
    public static void consultarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        System.out.println("\n==========================================");
        System.out.println("--- CONSULTAR ARTÍCULO ---");
        System.out.println("==========================================");
        // Si la lista está vacía, no tiene sentido pedir búsqueda.
        if (articulos.isEmpty()) {
            System.out.println("\nNo hay ningún artículo en la base de datos.");
            return;
        }
        
        String nombreBuscado = leerTextoNoVacio(scanner, "Ingrese el nombre del artículo que desea buscar: ");
        
        int posicion = buscarPosicionArticulo(articulos, nombreBuscado);
        
        if (posicion == -1) {
            System.out.println("El artículo no se encuentra en la base de datos.");
        } else {
            System.out.println("\n==========================================");
            System.out.println("---ENCONTRADO---");
            System.out.println("==========================================");
            System.out.println(articulos.get(posicion));
        }
    }
    
    public static void modificarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        
        System.out.println("\n==========================================");
        System.out.println("--- MODIFICAR ARTÍCULO ---");
        System.out.println("==========================================");
        
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
        !articulos.get(posicion).getNombre().equalsIgnoreCase(nuevaDescripcion)) {
            System.out.println("Error: ya existe otro artículo con ese nombre.");
            return;
        }
        
        // Reemplazamos el valor viejo por el nuevo.
        articulos.get(posicion).setNombre(nuevaDescripcion);
        
        System.out.println("Artículo modificado correctamente.");
    }
    
    public static void eliminarArticulo(Scanner scanner, ArrayList<Articulo> articulos) {
        
        System.out.println("\n==========================================");
        System.out.println("--- ELIMINAR ARTÍCULO ---");
        System.out.println("==========================================");
        
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
            confirmarEliminar(scanner,articulos.get(posicion).getNombre());
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
                System.out.println("ERROR: debe ingresar un número entero válido.");
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
            if (Validaciones.esTextoNoVacio(texto)) {
                // voy a retornar a donde fue llamada esta funcion, el texto sin espacios al principio ni al final
                return texto.trim();
            }
            // este mensaje solo se mostrara si el texto esta vacio luego de sacarle los espacios
            System.out.println("ERROR: el texto no puede estar vacío.");
        }
    }
    
    public static double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: debe ingresar un número decimal válido.");
            }
        }
    
    }

    public static void listarCategoriasExistentes(ArrayList<Categoria> categorias) {
        System.out.println("\n==========================================");
        System.out.println("--- LISTADO DE CATEGORÍAS ---");
        System.out.println("==========================================");
        System.out.println("\n------------------------------------------");
        System.out.println("--- ALIMENTOS ---");
        System.out.println("------------------------------------------");

        for (Categoria categoria : categorias) {
            if (categoria.getTipo().equalsIgnoreCase("ALIMENTOS")) {
                System.out.println(
                    categoria.getCodigo() + " - " +
                    categoria.getNombre() + " | " +
                    categoria.getDescripcion()
                );
            }
        }

        System.out.println("\n------------------------------------------");
        System.out.println("--- ELECTRÓNICOS ---");
        System.out.println("------------------------------------------");
        for (Categoria categoria : categorias) {
            if (categoria.getTipo().equalsIgnoreCase("ELECTRONICOS")) {
                System.out.println(
                    categoria.getCodigo() + " - " +
                    categoria.getNombre() + " | " +
                    categoria.getDescripcion()
                );
            }
        }
    }
    
    public static Categoria pedirCategoriaExistente(
            Scanner scanner,
            ArrayList<Categoria> categorias) {

        while (true) {
            System.out.println("\n==========================================");
            System.out.println("--- CATEGORÍAS DISPONIBLES ---");
            System.out.println("==========================================");
            System.out.println();
            for (Categoria categoria : categorias) {
                System.out.println(
                    categoria.getCodigo() + " - " + categoria.getNombre()
                );
            }

            int codigoCategoria = leerEntero(
                scanner,
                "\nIngrese el código de la categoría: "
            );

            Categoria categoria =
                buscarCategoriaPorCodigo(categorias, codigoCategoria);

            if (categoria != null) {
                return categoria;
            }

            System.out.println(
                "Error: la categoría no existe."
            );
        }
    }
    
    public static Categoria buscarCategoriaPorCodigo(ArrayList<Categoria> categorias, int codigo) {
        for (Categoria categoria : categorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria; // Retorna la categoría encontrada
            }
        }
        return null; // Retorna null si no se encuentra la categoría
    }
}

