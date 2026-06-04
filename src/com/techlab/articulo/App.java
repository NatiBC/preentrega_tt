package com.techlab.articulo;

// import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.articulo.menu.MenuArticulos;
import com.techlab.articulo.menu.MenuCategorias;
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
// import com.techlab.articulo.model.ArticuloAlimenticio;
// import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.repository.Repositorio;

public class App {

     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);

        Repositorio<Articulo> repositorioArticulos = new Repositorio<>();
        Repositorio<Categoria> repositorioCategorias = new Repositorio<>();

        MenuArticulos menuArticulos = new MenuArticulos(scanner, repositorioArticulos, repositorioCategorias);
        MenuCategorias menuCategorias = new MenuCategorias(scanner, repositorioCategorias, repositorioArticulos);

//         // ArrayList<Articulo> articulos = new ArrayList<>();
//         // ArrayList<Categoria> categorias = new ArrayList<>();

        int opcion;

        do {
            System.out.println("\n==========================================");
            System.out.println("   SISTEMA DE GESTIÓN DE CATEGORÍAS Y ARTÍCULOS");
            System.out.println("==========================================");
            System.out.println("\n1 - Menú de artículos");
            System.out.println("2 - Menú de categorías");
            System.out.println("0 - Salir");
            System.out.println("\n==========================================");

            opcion = leerEntero(scanner, "\nPor favor, ingrese una opción: ");

            switch (opcion) {
                case 1:
                    menuArticulos.ejecutar();
                    break;
                case 2:
                    menuCategorias.ejecutar();
                    break;
                case 0:
                    System.out.println("\nSaliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\nERROR: la opción ingresada no es válida.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
     }
//             System.out.println("\n==========================================");
//             System.out.println("   SISTEMA DE GESTIÓN DE CATEGORÍAS Y ARTÍCULOS");
//             System.out.println("==========================================");
//             System.out.println("\n1 - Ingresar nuevo artículo");
//             System.out.println("2 - Listar artículos");
//             System.out.println("3 - Consultar un artículo");
//             System.out.println("4 - Modificar un artículo");
//             System.out.println("5 - Eliminar un artículo");
//             System.out.println("6 - Ingresar categoría");
//             System.out.println("7 - Listar categorías");
//             System.out.println("8 - Consultar una categoría");
//             System.out.println("9 - Modificar una categoría");
//             System.out.println("10 - Eliminar una categoría");
//             System.out.println("0 - Salir");
//             System.out.println("\n==========================================");

//             opcion = leerEntero(scanner, "\nPor favor, ingrese una opción: ");

//             switch (opcion) {
//                 case 1:
//                     ingresarArticulo(scanner, repositorioArticulos, repositorioCategorias);
//                     break;
//                 case 2:
//                     listarArticulos(repositorioArticulos);
//                     break;
//                 case 3:
//                     consultarArticulo(scanner, repositorioArticulos);
//                     break;
//                 case 4:
//                     modificarArticulo(scanner, repositorioArticulos, repositorioCategorias);
//                     break;
//                 case 5:
//                     eliminarArticulo(scanner, repositorioArticulos);
//                     break;
//                 case 6:
//                     ingresarCategoria(scanner, repositorioCategorias);
//                     break;
//                 case 7:
//                     listarCategorias(repositorioCategorias);
//                     break;
//                 case 8:
//                     consultarCategoria(scanner, repositorioCategorias);
//                     break;
//                 case 9:
//                     modificarCategoria(scanner, repositorioCategorias);
//                     break;
//                 case 10:
//                     eliminarCategoria(scanner, repositorioCategorias, repositorioArticulos);
//                     break;
//                 case 0:
//                     System.out.println("\nSaliendo del sistema. ¡Hasta luego!");
//                     break;
//                 default:
//                     System.out.println("\nERROR: la opción ingresada no es válida.");
//             }

//         } while (opcion != 0);

//         scanner.close();
//     }

//     /*
//      * ==================================================
//      * CRUD DE ARTÍCULOS
//      * ==================================================
//      */
    
//     public static void ingresarArticulo(
//             Scanner s,
//             Repositorio<Articulo> repositorioArticulos,
//             Repositorio<Categoria> repositorioCategorias
//         ) {
//         System.out.println("\n==========================================");
//         System.out.println("--- INGRESAR ARTÍCULO ---");
//         System.out.println("==========================================");

//         if (repositorioCategorias.estaVacio()) {
//             System.out.println("No es posible crear artículos porque no hay categorías cargadas.");
//             System.out.println("Primero debe crear al menos una categoría.");
//             return;
//         }        

//         System.out.println("\n1 - Artículo Electrónico");
//         System.out.println("2 - Artículo Alimenticio");

//         int tipo;
//         do{
//             tipo = leerEntero(s, "\nSeleccione el tipo de artículo: ");
//             if (tipo != 1 && tipo != 2) {
//                 System.out.println("ERROR: opción no válida. Por favor, ingrese 1 o 2.");
//             }
//         } while (tipo != 1 && tipo != 2);

//         // PEDIR DATOS
//         int codigo = leerEnteroNoNegativo(s, "\nIngrese el código del artículo: ");

//         if (repositorioArticulos.buscarPorCodigo(codigo) != null) {
//             System.out.println("\nERROR: el código ya existe en el sistema.");
//             return;
//         }

//         String nombre = leerTextoNoVacio(s, "\nIngrese el nombre del artículo: ");
//         double precio = leerDoubleNoNegativo(s, "\nIngrese el precio del artículo: ");

//         listarCategorias(repositorioCategorias);
//         Categoria categoria = pedirCategoriaExistente(s, repositorioCategorias);

//         Articulo articulo;

//         if (tipo == 1) {
//             int garantiaMeses = leerEnteroNoNegativo(s, "\nIngrese la garantía en meses: ");
//             articulo = new ArticuloElectronico(codigo, nombre, precio, categoria, garantiaMeses);
//         } else {
//             int diasVencimiento = leerEnteroNoNegativo(s, "\nIngrese los días para vencimiento: ");
//             articulo = new ArticuloAlimenticio(codigo, nombre, precio, categoria, diasVencimiento);
//         }

//         repositorioArticulos.agregar(articulo);

//         System.out.println("\nArtículo ingresado correctamente.");
//         System.out.println(articulo);
//     }

//         // switch (tipo) {

//         //     case 1:
//         //         // CATEGORÍAS DE ELECTRÓNICOS
//         //         Categoria categoriaElectronico =
//         //             pedirCategoriaExistente(
//         //                 s,
//         //                 ArticuloElectronico.obtenerCategorias()
//         //             );

//         //         int garantiaMeses = leerEntero(
//         //             s,
//         //             "\nIngrese meses de garantía: "
//         //         );

//         //         articulo = new ArticuloElectronico(
//         //             codigo,
//         //             nombre,
//         //             precio,
//         //             categoriaElectronico,
//         //             garantiaMeses
//         //         );

//         //         break;

//         //     case 2:

//         //         // CATEGORÍAS DE ALIMENTOS
//         //         Categoria categoriaAlimento =
//         //             pedirCategoriaExistente(
//         //                 s,
//         //                 ArticuloAlimenticio.obtenerCategorias()
//         //             );

//         //         int diasVencimiento = leerEntero(
//         //             s,
//         //             "\nIngrese días para vencimiento: "
//         //         );

//         //         articulo = new ArticuloAlimenticio(
//         //             codigo,
//         //             nombre,
//         //             precio,
//         //             categoriaAlimento,
//         //             diasVencimiento
//         //         );

//         //         break;

//         //     default:
//         //         System.out.println(
//         //             "ERROR: tipo de artículo no válido."
//         //         );
//         //         return;
//         // }

//         // // AGREGAR A LA LISTA
//         // listaArticulos.add(articulo);

//         // System.out.println(
//         //     "\nArtículo ingresado correctamente."
//         // );
//         // System.out.println(articulo);
//     // }
    
//     public static void listarArticulos(Repositorio<Articulo> repositorioArticulos) {
//         System.out.println("\n==========================================");
//         System.out.println("--- LISTADO DE ARTÍCULOS ---");
//         System.out.println("==========================================");
//         // Si la lista está vacía, no hay nada que mostrar.
//         if (repositorioArticulos.estaVacio()) {
//             System.out.println("\nNo hay artículos cargados.");
//             return;
//         }

//         for (Articulo articulo : repositorioArticulos.listar()) {
//             System.out.println(articulo);
//             System.out.println("Detalle específico: " + articulo.getDetalleEspecifico());
//             System.out.println("Precio final calculado: " + articulo.calcularPrecioFinal());
//             System.out.println("--------------------------------------------");
//         }
//     }
    
//     public static void consultarArticulo(Scanner scanner, Repositorio<Articulo> repositorioArticulos) {
//         System.out.println("\n==========================================");
//         System.out.println("--- CONSULTAR ARTÍCULO ---");
//         System.out.println("==========================================");
//         // Si la lista está vacía, no tiene sentido pedir búsqueda.
//         if (repositorioArticulos.estaVacio()) {
//             System.out.println("\nNo hay ningún artículo en la base de datos.");
//             return;
//         }
        
//         int codigo = leerEnteroNoNegativo(scanner, "Ingrese el código del artículo a consultar: ");
                
//         Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

//         if (articulo == null) {
//             System.out.println("El artículo no se encuentra en la base de datos.");
//         }
//         System.out.println("\n==========================================");
//         System.out.println("---ENCONTRADO---");
//         System.out.println("==========================================");
//         System.out.println(articulo);
//         System.out.println("Detalle específico: " + articulo.getDetalleEspecifico());
//         System.out.println("Precio final calculado: " + articulo.calcularPrecioFinal());
//     }
        
//     public static void modificarArticulo(
//             Scanner scanner,
//             Repositorio<Articulo> repositorioArticulos,
//             Repositorio<Categoria> repositorioCategorias
//     ) {
//         System.out.println("\n--- MODIFICAR ARTÍCULO ---");

//         if (repositorioArticulos.estaVacio()) {
//             System.out.println("\nNo hay artículos cargados.");
//             return;
//         }

//         if (repositorioCategorias.estaVacio()) {
//             System.out.println("\nNo es posible modificar artículos porque no hay categorías cargadas.");
//             return;
//         }

//         int codigo = leerEnteroNoNegativo(scanner, "\nIngrese el código del artículo a modificar: ");

//         Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

//         if (articulo == null) {
//             System.out.println("\nEl artículo no existe.");
//             return;
//         }

//         String nuevoNombre = leerTextoNoVacio(scanner, "\nIngrese el nuevo nombre del artículo: ");
//         double nuevoPrecio = leerDoubleNoNegativo(scanner, "\nIngrese el nuevo precio del artículo: ");

//         listarCategorias(repositorioCategorias);
//         Categoria nuevaCategoria = pedirCategoriaExistente(scanner, repositorioCategorias);

//         articulo.setNombre(nuevoNombre);
//         articulo.setPrecio(nuevoPrecio);
//         articulo.setCategoria(nuevaCategoria);

//         if (articulo instanceof ArticuloElectronico) {
//             ArticuloElectronico electronico = (ArticuloElectronico) articulo;
//             int nuevaGarantia = leerEnteroNoNegativo(scanner, "\nIngrese la nueva garantía en meses: ");
//             electronico.setGarantiaMeses(nuevaGarantia);
//         }

//         if (articulo instanceof ArticuloAlimenticio) {
//             ArticuloAlimenticio alimenticio = (ArticuloAlimenticio) articulo;
//             int nuevosDias = leerEnteroNoNegativo(scanner, "\nIngrese los nuevos días para vencimiento: ");
//             alimenticio.setDiasParaVencimiento(nuevosDias);
//         }

//         System.out.println("\nArtículo modificado correctamente.");
//     }
    
//     public static void eliminarArticulo(Scanner scanner, Repositorio<Articulo> repositorioArticulos) {
        
//         System.out.println("\n==========================================");
//         System.out.println("--- ELIMINAR ARTÍCULO ---");
//         System.out.println("==========================================");
        
//         if (repositorioArticulos.estaVacio()) {
//             System.out.println("\nNo hay ningún artículo en la base de datos.");
//             return;
//         }
        
//         int codigo = leerEnteroNoNegativo(scanner, "\nIngrese el código del artículo a eliminar: ");

//         Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

//         if (articulo == null) {
//             System.out.println("\nEl artículo no existe.");
//             return;
//         }
//         // Pedimos confirmación antes de eliminar.
//         try {
//             confirmarEliminarArticulo(scanner,articulo.getNombre());
//         } catch (RuntimeException e) {
//             System.out.println(e.getMessage());
//             return; // Salimos del método sin eliminar nada.
//         }
//         // Eliminamos el elemento según su posición.
//         repositorioArticulos.eliminar(articulo);
        
//         System.out.println("\nArtículo eliminado correctamente.");
//     }
    
//     /*
//      * ==================================================
//      * CRUD DE CATEGORÍAS
//      * ==================================================
//      */

//     public static void ingresarCategoria(Scanner scanner, Repositorio<Categoria> repositorioCategorias) {
//         System.out.println("\n--- INGRESAR CATEGORÍA ---");

//         int codigo = leerEnteroNoNegativo(scanner, "\nIngrese el código de la categoría: ");

//         if (repositorioCategorias.buscarPorCodigo(codigo) != null) {
//             System.out.println("\nERROR: ya existe una categoría con ese código.");
//             return;
//         }

//         String nombre = leerTextoNoVacio(scanner, "\nIngrese el nombre de la categoría: ");

//         if (existeCategoriaPorNombre(repositorioCategorias, nombre)) {
//             System.out.println("\nERROR: ya existe una categoría con ese nombre.");
//             return;
//         }

//         String descripcion = leerTextoNoVacio(scanner, "\nIngrese la descripción de la categoría: ");

//         Categoria categoria = new Categoria(codigo, nombre, descripcion);
//         repositorioCategorias.agregar(categoria);

//         System.out.println("\nCategoría ingresada correctamente.");
//     }    
    
//     public static void listarCategorias(Repositorio<Categoria> repositorioCategorias) {
//         System.out.println("\n==========================================");
//         System.out.println("--- LISTADO DE CATEGORÍAS ---");
//         System.out.println("==========================================");

//         if (repositorioCategorias.estaVacio()) {
//             System.out.println("\nNo hay categorías cargadas.");
//             return;
//         }

//         for (Categoria categoria : repositorioCategorias.listar()) {
//             System.out.println(categoria);
//         }
//         // System.out.println("\n------------------------------------------");
//         // System.out.println("--- ALIMENTOS ---");
//         // System.out.println("------------------------------------------");

//         // for (Categoria categoria : categorias) {
//         //     if (categoria.getTipo().equalsIgnoreCase("ALIMENTOS")) {
//         //         System.out.println(
//         //             categoria.getCodigo() + " - " +
//         //             categoria.getNombre() + " | " +
//         //             categoria.getDescripcion()
//         //         );
//         //     }
//         // }

//         // System.out.println("\n------------------------------------------");
//         // System.out.println("--- ELECTRÓNICOS ---");
//         // System.out.println("------------------------------------------");
//         // for (Categoria categoria : categorias) {
//         //     if (categoria.getTipo().equalsIgnoreCase("ELECTRONICOS")) {
//         //         System.out.println(
//         //             categoria.getCodigo() + " - " +
//         //             categoria.getNombre() + " | " +
//         //             categoria.getDescripcion()
//         //         );
//         //     }
//         // }
//     }

//     public static void consultarCategoria(Scanner scanner, Repositorio<Categoria> repositorioCategorias) {
//         System.out.println("\n--- CONSULTAR CATEGORÍA ---");

//         if (repositorioCategorias.estaVacio()) {
//             System.out.println("\nNo hay categorías cargadas.");
//             return;
//         }

//         int codigo = leerEnteroNoNegativo(scanner, "\nIngrese el código de la categoría a consultar: ");

//         Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);

//         if (categoria == null) {
//             System.out.println("\nLa categoría no existe.");
//             return;
//         }

//         System.out.println("\nCategoría encontrada:");
//         System.out.println(categoria);
//     }    
    
    
//     public static void modificarCategoria(Scanner scanner, Repositorio<Categoria> repositorioCategorias) {
//         System.out.println("\n--- MODIFICAR CATEGORÍA ---");

//         if (repositorioCategorias.estaVacio()) {
//             System.out.println("\nNo hay categorías cargadas.");
//             return;
//         }

//         int codigo = leerEnteroNoNegativo(scanner, "\nIngrese el código de la categoría a modificar: ");

//         Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);

//         if (categoria == null) {
//             System.out.println("\nLa categoría no existe.");
//             return;
//         }

//         String nuevoNombre = leerTextoNoVacio(scanner, "\nIngrese el nuevo nombre de la categoría: ");

//         // Validamos nombre repetido, pero permitiendo que conserve su propio nombre actual.
//         if (existeCategoriaPorNombre(repositorioCategorias, nuevoNombre) &&
//                 !categoria.getNombre().equalsIgnoreCase(nuevoNombre)) {
//             System.out.println("\nError: ya existe otra categoría con ese nombre.");
//             return;
//         }

//         String nuevaDescripcion = leerTextoNoVacio(scanner, "\nIngrese la nueva descripción de la categoría: ");

//         categoria.setNombre(nuevoNombre);
//         categoria.setDescripcion(nuevaDescripcion);

//         System.out.println("\nCategoría modificada correctamente.");
//     }    
    
//     public static void eliminarCategoria(
//             Scanner scanner,
//             Repositorio<Categoria> repositorioCategorias,
//             Repositorio<Articulo> repositorioArticulos
//     ) {
//         System.out.println("\n--- ELIMINAR CATEGORÍA ---");

//         if (repositorioCategorias.estaVacio()) {
//             System.out.println("\nNo hay categorías cargadas.");
//             return;
//         }

//         int codigo = leerEnteroNoNegativo(scanner, "\nIngrese el código de la categoría a eliminar: ");

//         Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);

//         if (categoria == null) {
//             System.out.println("\nLa categoría no existe.");
//             return;
//         }

//         // Validamos si hay artículos que usan esta categoría.
//         if (categoriaTieneArticulosAsociados(categoria, repositorioArticulos)) {
//             System.out.println("\nNo se puede eliminar la categoría porque tiene artículos asociados.");
//             return;
//         }

//         try {
//             confirmarEliminarCategoria(scanner, categoria.getNombre());
//         } catch (RuntimeException e) {
//             System.out.println(e.getMessage());
//             return; // Salimos del método sin eliminar nada.
//          }

//         repositorioCategorias.eliminar(categoria);
//         System.out.println("\nCategoría eliminada correctamente.");
//     }    
//     /*
//      * ==================================================
//      * MÉTODOS AUXILIARES DE BÚSQUEDA
//      * ==================================================
//      */

//     // public static int buscarPosicionArticulo(Repositorio<Articulo> repositorioArticulos, String nombre) {
        
//     //     for (int i = 0; i < repositorioArticulos.size(); i++) {
//     //         if (repositorioArticulos.get(i).getNombre().equalsIgnoreCase(nombre.trim())) {
//     //             return i;
//     //         }
//     //     }
        
//     //     return -1;
//     // }

//     public static Articulo buscarArticuloPorCodigo(Repositorio<Articulo> repositorioArticulos, int codigo) {
//         for (Articulo articulo : repositorioArticulos.listar()) {
//             if (articulo.getCodigo() == codigo) {
//                 return articulo;
//             }
//         }
//         return null;
//     }

//     public static Categoria buscarCategoriaPorCodigo(Repositorio<Categoria> repositorioCategorias, int codigo) {
//         for (Categoria categoria : repositorioCategorias.listar()) {
//             if (categoria.getCodigo() == codigo) {
//                 return categoria; // Retorna la categoría encontrada
//             }
//         }
//         return null; // Retorna null si no se encuentra la categoría
//     }

//     public static boolean existeCategoriaPorNombre(Repositorio<Categoria> repositorioCategorias, String nombre) {
//         for (Categoria categoria : repositorioCategorias.listar()) {
//             if (categoria.getNombre().equalsIgnoreCase(nombre.trim())) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     public static boolean categoriaTieneArticulosAsociados(Categoria categoria, Repositorio<Articulo> repositorioArticulos) {
//         for (Articulo articulo : repositorioArticulos.listar()) {
//             if (articulo.getCategoria().getCodigo() == categoria.getCodigo()) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     public static Categoria pedirCategoriaExistente(Scanner scanner, Repositorio<Categoria> repositorioCategorias) {
//         while (true) {
//             int codigoCategoria = leerEnteroNoNegativo(scanner, "Ingrese el código de la categoría: ");
//             Categoria categoria = repositorioCategorias.buscarPorCodigo(codigoCategoria);

//             if (categoria != null) {
//                 return categoria;
//             }

//             System.out.println("Error: la categoría no existe.");
//         }
//     }

//     public static boolean existeArticulo(Repositorio<Articulo> repositorioArticulos, String nombre) {
//         // buscar una alternativa a este method con algun method de ArrayList, como contains, pero no es case insensitive, entonces lo hacemos a mano
//         for (Articulo articulo : repositorioArticulos.listar()) {
//             if (articulo.getNombre().equalsIgnoreCase(nombre.trim())){
//                 return true;
//             }
//         }
        
//         return false;
//     }
    
    /*
     * ==================================================
     * MÉTODOS AUXILIARES DE LECTURA Y VALIDACIÓN
     * ==================================================
     */    
    
    // public static void confirmarEliminarArticulo(Scanner scanner, String nombre) {
    //     while (true) {
    //         System.out.print("¿Está seguro que desea eliminar el artículo '" + nombre + "'? (S/N): ");
    //         String respuesta = scanner.nextLine().trim().toUpperCase();
            
    //         if (respuesta.equals("S")) {
    //             return; // Confirmación positiva, se procede a eliminar
    //         } else if (respuesta.equals("N")) {
    //             throw new RuntimeException("Eliminación cancelada por el usuario."); // Cancelación, se lanza una excepción para salir del proceso
    //         } else {
    //             System.out.println("Respuesta no válida. Por favor, ingrese 'S' para sí o 'N' para no.");
    //         }
    //     }
    // }

    // public static void confirmarEliminarCategoria(Scanner scanner, String nombre) {
    //     while (true) {
    //         System.out.print("¿Está seguro que desea eliminar la categoría '" + nombre + "'? (S/N): ");
    //         String respuesta = scanner.nextLine().trim().toUpperCase();
            
    //         if (respuesta.equals("S")) {
    //             return; // Confirmación positiva, se procede a eliminar
    //         } else if (respuesta.equals("N")) {
    //             throw new RuntimeException("Eliminación cancelada por el usuario."); // Cancelación, se lanza una excepción para salir del proceso
    //         } else {
    //             System.out.println("Respuesta no válida. Por favor, ingrese 'S' para sí o 'N' para no.");
    //         }
    //     }
    // }
    
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

    // public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
    //     while (true) {
    //         int valor = leerEntero(scanner, mensaje);

    //         if (valor < 0) {
    //             System.out.println("Error: el valor no puede ser negativo.");
    //             continue;
    //         }

    //         return valor;
    //     }
    // }

    // public static double leerDoubleNoNegativo(Scanner scanner, String mensaje) {
    //     while (true) {
    //         try {
    //             System.out.print(mensaje);
    //             double valor = Double.parseDouble(scanner.nextLine());

    //             if (valor < 0) {
    //                 System.out.println("Error: el precio no puede ser negativo.");
    //                 continue;
    //             }

    //             return valor;
    //         } catch (NumberFormatException e) {
    //             System.out.println("Error: debe ingresar un número decimal válido.");
    //         }
    //     }
    // }
    
    // public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        
    //     while (true) {
    //         System.out.print(mensaje);
    //         String texto = scanner.nextLine();
    //         // texto.trim() elimina los espacios en blanco al principio y al final del texto
    //         // isEmpty() verifica si el texto está vacío después de eliminar los espacios
    //         // !true -> false
    //         // !texto.trim().isEmpty() -> true si el texto no está vacío
    //         if (Validaciones.esTextoNoVacio(texto)) {
    //             // voy a retornar a donde fue llamada esta funcion, el texto sin espacios al principio ni al final
    //             return texto.trim();
    //         }
    //         // este mensaje solo se mostrara si el texto esta vacio luego de sacarle los espacios
    //         System.out.println("ERROR: el texto no puede estar vacío.");
    //     }
    // }
    
    // public static double leerDouble(Scanner scanner, String mensaje) {
    //     while (true) {
    //         try {
    //             System.out.print(mensaje);
    //             return Double.parseDouble(scanner.nextLine());
    //         } catch (NumberFormatException e) {
    //             System.out.println("\nERROR: debe ingresar un número decimal válido.");
    //         }
    //     }
    
    // }
    
    // public static Categoria pedirCategoriaExistente(
    //         Scanner scanner,
    //         ArrayList<Categoria> categorias,
    //         String tipo) {

    //     while (true) {
    //         System.out.println("\n==========================================");
    //         System.out.println("--- CATEGORÍAS DISPONIBLES ---");
    //         System.out.println("==========================================");
    //         System.out.println();
    //         for (Categoria categoria : categorias) {
    //             System.out.println(
    //                 categoria.getCodigo() + " - " + categoria.getNombre()
    //             );
    //         }

    //         int codigoCategoria = leerEntero(
    //             scanner,
    //             "\nIngrese el código de la categoría: "
    //         );

    //         Categoria categoria = buscarCategoriaPorCodigo(categorias, codigoCategoria);

    //         if (categoria != null) {
    //             return categoria;
    //         }

    //         System.out.println(
    //             "Error: la categoría no existe."
    //         );
    //     }
    // }
    

}

