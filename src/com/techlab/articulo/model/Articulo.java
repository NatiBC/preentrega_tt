package com.techlab.articulo.model;

import com.techlab.articulo.interfaces.Calculable;
import com.techlab.articulo.interfaces.Identificable;

public abstract class Articulo 
    implements Calculable, Identificable {

    private int codigo;
    private String nombre;
    private double precio;
    private Categoria categoria;
    
    public Articulo(int codigo, String nombre, double precio, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public abstract String getTipoArticulo();
    
    public abstract String getDetalleEspecifico();

    public abstract double calcularPrecioFinal();
    
        @Override
        public String toString() {
            return 
                "Artículo: " + 
                "\nCódigo: " + codigo + 
                "\nProducto: " + nombre +  
                "\nPrecio final: $" + String.format("%.2f", calcularPrecioFinal()) +
                "\nCategoría: " + categoria.getNombre() +
                "\nDetalle: " + getDetalleEspecifico();
                }
}
