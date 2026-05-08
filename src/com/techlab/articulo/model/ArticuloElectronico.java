package com.techlab.articulo.model;

import java.util.ArrayList;

public class ArticuloElectronico extends Articulo {

    private int garantiaMeses;

    // CONSTRUCTOR
    public ArticuloElectronico(
            int codigo,
            String nombre,
            double precio,
            Categoria categoria,
            int garantiaMeses) {

        super(codigo, nombre, precio, categoria);
        this.garantiaMeses = garantiaMeses;
    }

    // CATEGORÍAS VÁLIDAS
    public static ArrayList<Categoria> obtenerCategorias() {

        ArrayList<Categoria> categorias = new ArrayList<>();

        categorias.add(
            new Categoria(
                1,
                "Computación",
                "Productos informáticos",
                "ELECTRONICOS"
            )
        );

        categorias.add(
            new Categoria(
                2,
                "Electrodomésticos",
                "Artefactos electrónicos para el hogar",
                "ELECTRONICOS"
            )
        );

        categorias.add(
            new Categoria(
                3,
                "Audio",
                "Equipos de sonido",
                "ELECTRONICOS"
            )
        );

        categorias.add(
            new Categoria(
                7,
                "Telefonía",
                "Teléfonos y accesorios",
                "ELECTRONICOS"
            )
        );
        
        categorias.add(
            new Categoria(
                8,
                "Video",
                "Televisores y proyectores",
                "ELECTRONICOS"
            )
        );

        categorias.add(
            new Categoria(
                9,
                "Pequeños electrodomésticos",
                "Pequeños artefactos para el hogar",
                "ELECTRONICOS"
            )
        );

        categorias.add(
            new Categoria(
                10,
                "Otros",
                "Otros productos electrónicos",
                "ELECTRONICOS"
            )
        );

        return categorias;
    }    

    // GETTER
    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    // SETTER
    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }
    
    @Override
    public String getTipoArticulo() {
        return "Electrónico";
    }
    
    public double calcularPrecioFinal() {
        // Si la garantía supera 12 meses,
        // aplicamos 10% de recargo
        if (garantiaMeses > 12) {
            return getPrecio() * 1.10;
        }

        return getPrecio();
    }

    @Override
    public String getDetalleEspecifico() {
        return "Garantía: " + garantiaMeses + " meses";
    }
}
