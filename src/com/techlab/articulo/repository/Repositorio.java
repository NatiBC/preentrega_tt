package com.techlab.articulo.repository;

import java.util.ArrayList;
import java.util.List;

import com.techlab.articulo.interfaces.Identificable;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe ser GENÉRICA.
 *
 * Debe modelarse así:
 * Repositorio<T extends Identificable>
 *
 * ¿Qué significa eso?
 * Que podrá trabajar con distintos tipos de objetos, siempre que esos
 * objetos tengan código.
 *
 * EJEMPLOS DE USO ESPERADOS
 * ------------------------------------------------------------
 * - Repositorio<Categoria>
 * - Repositorio<Articulo>
 *
 * ESTA CLASE DEBE GUARDAR LOS DATOS EN MEMORIA
 * ------------------------------------------------------------
 * Usando:
 * - ArrayList<T>
 *
 * MÉTODOS MÍNIMOS ESPERADOS
 * ------------------------------------------------------------
 * - agregar(T objeto)
 * - listar()
 * - buscarPorCodigo(int codigo)
 * - eliminar(T objeto)
 * - estaVacio()
 *
 * OBJETIVO DIDÁCTICO
 * ------------------------------------------------------------
 * Esta clase prepara el terreno para entender luego estructuras como:
 * JpaRepository<T, ID> en Spring Boot.
 */
public class Repositorio<T extends Identificable> {

    private ArrayList<T> lista = new ArrayList<>();
    
    // TODO:
    // Implementar método agregar.

    public void agregar(T objeto) {
        lista.add(objeto);
    }
    
    // TODO:
    // Implementar método listar.

    public List<T> listar(){
        return new ArrayList<>(lista); // Devolvemos una copia para evitar modificaciones externas.
    }
    
    // TODO:
    // Implementar método buscarPorCodigo.

    public T buscarPorCodigo(int codigo) {
        for (T objeto : lista) {
            if (objeto.getCodigo() == codigo) {
                return objeto;
            }
        }
        return null; // Si no encontramos el objeto, devolvemos null.
    }
    
    // TODO:
    // Implementar método eliminar.

    public void eliminar(T objeto) {
        lista.remove(objeto);
    }
    
    // TODO:
    // Implementar método estaVacio.

    public boolean estaVacio() {
        return lista.isEmpty();
    }
}
