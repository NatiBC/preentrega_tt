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
                "Productos derivados de leche",
                "ALIMENTOS"
            )
        );

        categorias.add(
            new Categoria(
                2,
                "Bebidas sin alcohol",
                "Aguas, aguas con gas, aguas saborizadas y refrescos",
                "ALIMENTOS"
            )
        );
        
        categorias.add(
            new Categoria(
                3,
                "Bebidas con alchol",
                "Vinos, espumantes, cervezas y licores",
                "ALIMENTOS"
            )
        );
        
        categorias.add(
            new Categoria(
                4,
                "Snacks",
                "Aperitivos y snacks",
                "ALIMENTOS"
            )
        );
        
        categorias.add(
            new Categoria(
                5,
                "Frutas y Verduras",
                "Productos frescos",
                "ALIMENTOS"
            )
        );

        categorias.add(
            new Categoria(
                6,
                "Carnes y Pescados",
                "Productos de origen animal",
                "ALIMENTOS"
            )
        );
        
        categorias.add(
            new Categoria(
                7,
                "Congelados",
                "Productos congelados",
                "ALIMENTOS"
            )
        );

        categorias.add(
            new Categoria(
                8,
                "Panadería y Pastelería",
                "Productos de panadería y pastelería",
                "ALIMENTOS"
            )
        );

        categorias.add(
            new Categoria(
                9,
                "Dulces y Golosinas",
                "Dulces, chocolates y golosinas",
                "ALIMENTOS"
            )
        );

        categorias.add(
            new Categoria(
                10,
                "Harinas y Cereales",
                "Harinas, cereales y productos derivados",
                "ALIMENTOS"
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

    @Override
    public double calcularPrecioFinal() {

        // Si vence pronto, aplicamos descuento

        if (diasParaVencimiento <= 5) {
            return getPrecio() * 0.8; // 20% descuento
        }

        if (diasParaVencimiento <= 10) {
            return getPrecio() * 0.9; // 10% descuento
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
