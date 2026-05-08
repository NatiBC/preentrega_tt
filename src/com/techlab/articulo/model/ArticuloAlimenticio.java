package com.techlab.articulo.model;

import java.util.ArrayList;

public class ArticuloAlimenticio extends Articulo {

    private int diasParaVencimiento;

    public ArticuloAlimenticio(
        int codigo, 
        String nombre, 
        double precio, 
        Categoria categoria, 
        int diasParaVencimiento) {
        
        super(codigo, nombre, precio, categoria);
        this.diasParaVencimiento = diasParaVencimiento;
    }

    // CATEGORÍAS VÁLIDAS
    public static ArrayList<Categoria> obtenerCategorias() {

        ArrayList<Categoria> categorias = new ArrayList<>();

        categorias.add(
            new Categoria(
                1,
                "Lácteos",
                "Productos derivados de leche"
            )
        );

        categorias.add(
            new Categoria(
                2,
                "Bebidas sin alcohol",
                "Aguas, aguas con gas, aguas saborizadas y refrescos"
            )
        );
        
        categorias.add(
            new Categoria(
                3,
                "Bebidas con alchol",
                "Vinos, espumantes, cervezas y licores"
            )
        );
        
        categorias.add(
            new Categoria(
                4,
                "Snacks",
                "Aperitivos y snacks"
            )
        );
        
        categorias.add(
            new Categoria(
                5,
                "Frutas y Verduras",
                "Productos frescos"
            )
        );

        categorias.add(
            new Categoria(
                6,
                "Carnes y Pescados",
                "Productos de origen animal"
            )
        );
        
        categorias.add(
            new Categoria(
                7,
                "Congelados",
                "Productos congelados"
            )
        );

        categorias.add(
            new Categoria(
                8,
                "Panadería y Pastelería",
                "Productos de panadería y pastelería"
            )
        );

        categorias.add(
            new Categoria(
                9,
                "Dulces y Golosinas",
                "Dulces, chocolates y golosinas"
            )
        );

        categorias.add(
            new Categoria(
                10,
                "Harinas y Cereales",
                "Harinas, cereales y productos derivados"
            )
        );
        
        return categorias;
    }    
    
    public int getDiasParaVencimiento() {
        return diasParaVencimiento;
    }
    
    public void setDiasParaVencimiento(int diasParaVencimiento) {
        this.diasParaVencimiento = diasParaVencimiento;
    }

    public double calcularPrecioFinal() {

        // Si vence pronto, aplicamos descuento

        if (diasParaVencimiento <= 3) {
            return getPrecio() * 0.8; // 20% descuento
        }

        return getPrecio();
    }

    @Override
    public String getTipoArticulo() {
        return "Alimenticio";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Dias para vencimiento: " + diasParaVencimiento;
    }
}
