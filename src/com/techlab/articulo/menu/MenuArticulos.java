package com.techlab.articulo.menu;

import java.util.Scanner;

import com.techlab.articulo.model.Articulo;
import com.techlab. articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe heredar de Menu y encargarse del CRUD de artículos.
 *
 * Debe trabajar con:
 * - Repositorio<Articulo>
 * - Repositorio<Categoria>
 *
 * ¿Por qué necesita también categorías?
 * Porque un artículo debe asociarse a una categoría ya existente.
 *
 * FUNCIONALIDADES ESPERADAS
 * ------------------------------------------------------------
 * 1) Ingresar artículo
 * 2) Listar artículos
 * 3) Consultar un artículo por código
 * 4) Modificar un artículo
 * 5) Eliminar un artículo
 * 0) Volver
 *
 * REQUISITOS IMPORTANTES
 * ------------------------------------------------------------
 * - Antes de crear un artículo, debe verificarse que existan categorías.
 * - Debe preguntarse qué tipo de artículo se quiere crear:
 *   - electrónico
 *   - alimenticio
 * - Debe pedirse:
 *   - nombre
 *   - precio
 *   - categoría por código
 * - Si es electrónico:
 *   - garantía en meses
 * - Si es alimenticio:
 *   - días para vencimiento
 *
 * VALIDACIONES
 * ------------------------------------------------------------
 * - nombre no vacío
 * - precio no negativo
 * - categoría existente
 * - garantía no negativa
 * - días para vencimiento no negativos
 *
 * SUGERENCIA DE MÉTODOS
 * ------------------------------------------------------------
 * - ingresarArticulo()
 * - listarArticulos()
 * - consultarArticulo()
 * - modificarArticulo()
 * - eliminarArticulo()
 * - pedirCategoriaExistente()
 * - pedirNombreArticulo()
 * - pedirPrecioArticulo()
 * - pedirGarantia()
 * - pedirDiasParaVencimiento()
 */
public class MenuArticulos extends Menu {

    private Repositorio<Articulo> repositorioArticulos;
    private Repositorio<Categoria> repositorioCategorias;

    public MenuArticulos(Scanner scanner, Repositorio<Articulo> repositorioArticulos, Repositorio<Categoria> repositorioCategorias) {
        super(scanner);
        this.repositorioArticulos = repositorioArticulos;
        this.repositorioCategorias = repositorioCategorias;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- MENÚ ARTÍCULOS ---");
        System.out.println("1 - Ingresar artículo");
        System.out.println("2 - Listar artículos");
        System.out.println("3 - Consultar artículo");
        System.out.println("4 - Modificar artículo");
        System.out.println("5 - Eliminar artículo");
        System.out.println("0 - Volver");
        System.out.print("---------------------------------------------------");
    }

    @Override
    public void ejecutar() {
        int opcion;

            do {
                mostrarMenu();
                opcion = leerEntero( "\nSeleccione una opción: ");
    
                switch (opcion) {
                    case 1:
                        ingresarArticulo();
                        break;
                    case 2:
                        listarArticulos();
                        break;
                    case 3:
                        consultarArticulo();
                        break;
                    case 4:
                        modificarArticulo();
                        break;
                    case 5:
                        eliminarArticulo();
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, intente nuevamente.");
                }
            } while (opcion != 0);
        // TODO:
        // Implementar el loop del menú y llamar a los métodos correspondientes.
    }

    public void ingresarArticulo() {
        System.out.println("\n==========================================");
        System.out.println("--- INGRESAR ARTÍCULO ---");
        System.out.println("==========================================");

        if (repositorioCategorias.estaVacio()) {
            System.out.println("No es posible crear artículos porque no hay categorías cargadas.");
            System.out.println("Primero debe crear al menos una categoría.");
            return;
        }        

        System.out.println("\n1 - Artículo Electrónico");
        System.out.println("2 - Artículo Alimenticio");

        int tipo;
        do{
            tipo = leerEntero("\nSeleccione el tipo de artículo: ");
            if (tipo != 1 && tipo != 2) {
                System.out.println("ERROR: opción no válida. Por favor, ingrese 1 o 2.");
            }
        } while (tipo != 1 && tipo != 2);

        // PEDIR DATOS
        int codigo = leerEnteroNoNegativo("\nIngrese el código del artículo: ");

        if (repositorioArticulos.buscarPorCodigo(codigo) != null) {
            System.out.println("\nERROR: el código ya existe en el sistema.");
            return;
        }

        String nombre = leerTextoNoVacio("\nIngrese el nombre del artículo: ");
        double precio = leerDoubleNoNegativo( "\nIngrese el precio del artículo: ");

        listarCategoriasInterno();
        Categoria categoria = pedirCategoriaExistente();

        Articulo articulo;

        if (tipo == 1) {
            int garantiaMeses = leerEnteroNoNegativo("\nIngrese la garantía en meses: ");
            articulo = new ArticuloElectronico(codigo, nombre, precio, categoria, garantiaMeses);
        } else {
            int diasVencimiento = leerEnteroNoNegativo("\nIngrese los días para vencimiento: ");
            articulo = new ArticuloAlimenticio(codigo, nombre, precio, categoria, diasVencimiento);
        }

        repositorioArticulos.agregar(articulo);

        System.out.println("\nArtículo ingresado correctamente.");
        System.out.println(articulo);
    }

    public void listarArticulos() {
        System.out.println("\n==========================================");
        System.out.println("--- LISTADO DE ARTÍCULOS ---");
        System.out.println("==========================================");
        // Si la lista está vacía, no hay nada que mostrar.
        if (repositorioArticulos.estaVacio()) {
            System.out.println("\nNo hay artículos cargados.");
            return;
        }

        for (Articulo articulo : repositorioArticulos.listar()) {
            System.out.println(articulo);
            System.out.println("\n--------------------------------------------");
        }
    }
    
    public void consultarArticulo() {
        System.out.println("\n==========================================");
        System.out.println("--- CONSULTAR ARTÍCULO ---");
        System.out.println("==========================================");
        // Si la lista está vacía, no tiene sentido pedir búsqueda.
        if (repositorioArticulos.estaVacio()) {
            System.out.println("\nNo hay ningún artículo en la base de datos.");
            return;
        }
        
        int codigo = leerEnteroNoNegativo( "\nIngrese el código del artículo a consultar: ");
                
        Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

        if (articulo == null) {
            System.out.println("El artículo no se encuentra en la base de datos.");
        }
        System.out.println("\n==========================================");
        System.out.println("---ENCONTRADO---");
        System.out.println("==========================================");
        System.out.println(articulo);
    }
        
    public void modificarArticulo() {
        System.out.println("\n--- MODIFICAR ARTÍCULO ---");

        if (repositorioArticulos.estaVacio()) {
            System.out.println("\nNo hay artículos cargados.");
            return;
        }

        if (repositorioCategorias.estaVacio()) {
            System.out.println("\nNo es posible modificar artículos porque no hay categorías cargadas.");
            return;
        }

        int codigo = leerEnteroNoNegativo( "\nIngrese el código del artículo a modificar: ");

        Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

        if (articulo == null) {
            System.out.println("\nEl artículo no existe.");
            return;
        }

        String nuevoNombre = leerTextoNoVacio("\nIngrese el nuevo nombre del artículo: ");
        double nuevoPrecio = leerDoubleNoNegativo("\nIngrese el nuevo precio del artículo: ");

        listarCategoriasInterno();
        Categoria nuevaCategoria = pedirCategoriaExistente();

        articulo.setNombre(nuevoNombre);
        articulo.setPrecio(nuevoPrecio);
        articulo.setCategoria(nuevaCategoria);

        if (articulo instanceof ArticuloElectronico) {
            ArticuloElectronico electronico = (ArticuloElectronico) articulo;
            int nuevaGarantia = leerEnteroNoNegativo( "\nIngrese la nueva garantía en meses: ");
            electronico.setGarantiaMeses(nuevaGarantia);
        }

        if (articulo instanceof ArticuloAlimenticio) {
            ArticuloAlimenticio alimenticio = (ArticuloAlimenticio) articulo;
            int nuevosDias = leerEnteroNoNegativo( "\nIngrese los nuevos días para vencimiento: ");
            alimenticio.setDiasParaVencimiento(nuevosDias);
        }

        System.out.println("\nArtículo modificado correctamente.");
    }
    
    public void eliminarArticulo() {
        
        System.out.println("\n==========================================");
        System.out.println("--- ELIMINAR ARTÍCULO ---");
        System.out.println("==========================================");
        
        if (repositorioArticulos.estaVacio()) {
            System.out.println("\nNo hay ningún artículo en la base de datos.");
            return;
        }
        
        int codigo = leerEnteroNoNegativo("\nIngrese el código del artículo a eliminar: ");

        Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

        if (articulo == null) {
            System.out.println("\nEl artículo no existe.");
            return;
        }
        // Pedimos confirmación antes de eliminar.
        try {
            confirmarEliminarArticulo(articulo.getNombre());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return; // Salimos del método sin eliminar nada.
        }
        // Eliminamos el elemento según su posición.
        repositorioArticulos.eliminar(articulo);
        
        System.out.println("\nArtículo eliminado correctamente.");
    }

    private void listarCategoriasInterno() {
        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");

        for (Categoria categoria : repositorioCategorias.listar()) {
            System.out.println(categoria);
        }
    }

    public Categoria pedirCategoriaExistente() {

        while (true) {
            int codigoCategoria = leerEnteroNoNegativo("\nIngrese el código de la categoría: ");

            Categoria categoria = repositorioCategorias.buscarPorCodigo(codigoCategoria);

            if (categoria != null) {
                return categoria;
            }

            System.out.println(
                "Error: la categoría no existe."
            );
        }
    }

    // TODO:
    // Implementar todos los métodos del CRUD de artículos.
}
