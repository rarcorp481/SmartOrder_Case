package com.smartorder.business.domain.entities;
import lombok.*;

@Getter @Setter
public class ProductoFisico extends Producto {
    private String categoria; private int cantidadStock;

    public ProductoFisico(String nombre, double precio, String cat, int stock) {
        super(nombre, precio, stock > 0);
        this.categoria = cat; this.cantidadStock = stock;
    }
    @Override public void actualizarDisponibilidad() { this.disponible = this.cantidadStock > 0; }
    public void reducirStock() {
        if (cantidadStock > 0) { cantidadStock--; actualizarDisponibilidad(); }
    }
    @Override public String toString() { return String.format("%s - $%.2f (Stock: %d)", nombre, precio, cantidadStock); }
}