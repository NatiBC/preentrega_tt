package com.techlab.articulo.utils;

public final class Validaciones {

    private Validaciones() {}

    public static boolean esTextoNoVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean esNoNegativo(int valor) {
        return valor >= 0;
    }

    public static boolean esNoNegativo(double valor) {
        return valor >= 0;
    }

    public static boolean longitudMaxima(String texto, int maximo) {
        return texto != null && texto.length() <= maximo;
    }
}