package com.techlab.articulo.menu;

import java.util.Scanner;

import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.repository.Repositorio;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe heredar de Menu y encargarse del CRUD de categorías.
 *
 * Debe trabajar con:
 * - Repositorio<Categoria>
 * - Repositorio<Articulo>
 *
 * ¿Por qué necesita también artículos?
 * Porque antes de eliminar una categoría debe verificarse si está
 * siendo utilizada por algún artículo.
 *
 * FUNCIONALIDADES ESPERADAS
 * ------------------------------------------------------------
 * 1) Ingresar categoría
 * 2) Listar categorías
 * 3) Consultar una categoría por código
 * 4) Modificar una categoría
 * 5) Eliminar una categoría
 * 0) Volver
 *
 * VALIDACIONES
 * ------------------------------------------------------------
 * - nombre no vacío
 * - descripción no vacía
 * - no permitir categorías repetidas por nombre
 *
 * REGLA DE NEGOCIO IMPORTANTE
 * ------------------------------------------------------------
 * No se puede eliminar una categoría si existe al menos un artículo
 * asociado a ella.
 *
 * SUGERENCIA DE MÉTODOS
 * ------------------------------------------------------------
 * - ingresarCategoria()
 * - listarCategorias()
 * - consultarCategoria()
 * - modificarCategoria()
 * - eliminarCategoria()
 * - categoriaTieneArticulosAsociados(...)
 */
public class MenuCategorias extends Menu {

    private Repositorio<Categoria> repositorioCategorias;
    private Repositorio<Articulo> repositorioArticulos;

    public MenuCategorias(
            Scanner scanner,
            Repositorio<Categoria> repositorioCategorias,
            Repositorio<Articulo> repositorioArticulos
    ) {
        super(scanner);
        this.repositorioCategorias = repositorioCategorias;
        this.repositorioArticulos = repositorioArticulos;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- MENÚ CATEGORÍAS ---");
        System.out.println("1 - Ingresar categoría");
        System.out.println("2 - Listar categorías");
        System.out.println("3 - Consultar categoría");
        System.out.println("4 - Modificar categoría");
        System.out.println("5 - Eliminar categoría");
        System.out.println("0 - Volver");
        System.out.println("--------------------------------------------------");
    }

    @Override
    public void ejecutar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    ingresarCategoria();
                    break;
                case 2:
                    listarCategorias();
                    break;
                case 3:
                    consultarCategoria();
                    break;
                case 4:
                    modificarCategoria();
                    break;
                case 5:
                    eliminarCategoria();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 0);
        // TODO:
        // Implementar el loop del menú y llamar a los métodos correspondientes.
    }

    public void ingresarCategoria() {
        System.out.println("\n--- INGRESAR CATEGORÍA ---");

        int codigo = leerEnteroNoNegativo( "\nIngrese el código de la categoría: ");

        if (repositorioCategorias.buscarPorCodigo(codigo) != null) {
            System.out.println("\nERROR: ya existe una categoría con ese código.");
            return;
        }

        String nombre = leerTextoNoVacio("\nIngrese el nombre de la categoría: ");

        if (existeCategoriaPorNombre(nombre)) {
            System.out.println("\nERROR: ya existe una categoría con ese nombre.");
            return;
        }

        String descripcion = leerTextoNoVacio("\nIngrese la descripción de la categoría: ");

        Categoria categoria = new Categoria(codigo, nombre, descripcion);
        repositorioCategorias.agregar(categoria);

        System.out.println("\nCategoría ingresada correctamente.");
    }    
    
    public void listarCategorias() {
        System.out.println("\n==========================================");
        System.out.println("--- LISTADO DE CATEGORÍAS ---");
        System.out.println("==========================================");

        if (repositorioCategorias.estaVacio()) {
            System.out.println("\nNo hay categorías cargadas.");
            return;
        }

        for (Categoria categoria : repositorioCategorias.listar()) {
            System.out.println(categoria);
            System.out.println("\n--------------------------------------------");

        }
    }

    public void consultarCategoria() {
        System.out.println("\n--- CONSULTAR CATEGORÍA ---");

        if (repositorioCategorias.estaVacio()) {
            System.out.println("\nNo hay categorías cargadas.");
            return;
        }

        int codigo = leerEnteroNoNegativo( "\nIngrese el código de la categoría a consultar: ");

        Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);

        if (categoria == null) {
            System.out.println("\nLa categoría no existe.");
            return;
        }

        System.out.println("\nCategoría encontrada:");
        System.out.println(categoria);
    }    
    
    
    public void modificarCategoria() {
        System.out.println("\n--- MODIFICAR CATEGORÍA ---");

        if (repositorioCategorias.estaVacio()) {
            System.out.println("\nNo hay categorías cargadas.");
            return;
        }

        int codigo = leerEnteroNoNegativo( "\nIngrese el código de la categoría a modificar: ");

        Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);

        if (categoria == null) {
            System.out.println("\nLa categoría no existe.");
            return;
        }

        String nuevoNombre = leerTextoNoVacio("\nIngrese el nuevo nombre de la categoría: ");

        // Validamos nombre repetido, pero permitiendo que conserve su propio nombre actual.
        if (existeCategoriaPorNombre(nuevoNombre) &&
                !categoria.getNombre().equalsIgnoreCase(nuevoNombre)) {
            System.out.println("\nError: ya existe otra categoría con ese nombre.");
            return;
        }

        String nuevaDescripcion = leerTextoNoVacio("\nIngrese la nueva descripción de la categoría: ");

        categoria.setNombre(nuevoNombre);
        categoria.setDescripcion(nuevaDescripcion);

        System.out.println("\nCategoría modificada correctamente.");
    }    
    
    public void eliminarCategoria() {
        System.out.println("\n--- ELIMINAR CATEGORÍA ---");

        if (repositorioCategorias.estaVacio()) {
            System.out.println("\nNo hay categorías cargadas.");
            return;
        }

        int codigo = leerEnteroNoNegativo("\nIngrese el código de la categoría a eliminar: ");

        Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);

        if (categoria == null) {
            System.out.println("\nLa categoría no existe.");
            return;
        }

        // Validamos si hay artículos que usan esta categoría.
        if (categoriaTieneArticulosAsociados(categoria)) {
            System.out.println("\nNo se puede eliminar la categoría porque tiene artículos asociados.");
            return;
        }

        try {
            confirmarEliminarCategoria(categoria.getNombre());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return; // Salimos del método sin eliminar nada.
         }

        repositorioCategorias.eliminar(categoria);
        System.out.println("\nCategoría eliminada correctamente.");
    } 
    
    public boolean existeCategoriaPorNombre(String nombre) {
        for (Categoria categoria : repositorioCategorias.listar()) {
            if (categoria.getNombre().equalsIgnoreCase(nombre.trim())) {
                return true;
            }
        }
        return false;
    }

    public boolean categoriaTieneArticulosAsociados(Categoria categoria) {
        for (Articulo articulo : repositorioArticulos.listar()) {
            if (articulo.getCategoria().getCodigo() == categoria.getCodigo()) {
                return true;
            }
        }
        return false;
    }
    // TODO:
    // Implementar todos los métodos del CRUD de categorías.
}
