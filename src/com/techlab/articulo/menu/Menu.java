package com.techlab.articulo.menu;

import java.util.Scanner;

import com.techlab.articulo.utils.Validaciones;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta debe ser la clase base de todos los menús.
 *
 * Objetivo:
 * centralizar la lógica común para no repetir código.
 *
 * Esta clase debe:
 * - guardar un Scanner compartido
 * - declarar el método mostrarMenu()
 * - declarar el método ejecutar()
 *
 * Además, podés agregar métodos protegidos reutilizables, por ejemplo:
 * - leerEntero(String mensaje)
 * - leerDouble(String mensaje)
 * - leerTexto(String mensaje)
 * - leerSiNo(String mensaje)
 *
 * IMPORTANTE:
 * Esta clase debe ser abstracta, porque no tiene sentido crear un
 * "menú genérico" instanciable. Solo debe servir como base para:
 * - MenuArticulos
 * - MenuCategorias
 */
public abstract class Menu {
    

    protected Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    // TODO:
    // Declarar método abstracto para mostrar el menú.
    public abstract void mostrarMenu();

    // TODO:
    // Declarar método abstracto para ejecutar el menú.
    public abstract void ejecutar();

    // TODO:
    // Agregar métodos auxiliares de lectura segura si querés reutilizar lógica.


    public void confirmarEliminarArticulo(String nombre) {
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

    public void confirmarEliminarCategoria(String nombre) {
        while (true) {
            System.out.print("¿Está seguro que desea eliminar la categoría '" + nombre + "'? (S/N): ");
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
    
    public int leerEntero(String mensaje) {
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

    public int leerEnteroNoNegativo(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);

            if (valor < 0) {
                System.out.println("Error: el valor no puede ser negativo.");
                continue;
            }

            return valor;
        }
    }

    public double leerDoubleNoNegativo(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine());

                if (valor < 0) {
                    System.out.println("Error: el precio no puede ser negativo.");
                    continue;
                }

                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número decimal válido.");
            }
        }
    }
    
    public String leerTextoNoVacio(String mensaje) {
        
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
    
    public double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: debe ingresar un número decimal válido.");
            }
        }
    
    }
    
}
