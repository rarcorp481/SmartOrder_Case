package com.smartorder.business.application;

import com.smartorder.business.domain.contracts.producto.IGestorProducto;
import com.smartorder.business.domain.entities.Producto;
import com.smartorder.business.domain.entities.ProductoFisico;
import com.smartorder.business.infrastructure.persistence.DataStorage;

public class ServiceProducto implements IGestorProducto {

    @Override
    public boolean registrarProducto(String nombre, double precio, String categoria, int stock) {
        if (nombre == null || nombre.isBlank() || precio <= 0 || stock < 0) {
            System.out.println("Error: Datos inválidos. Precio debe ser > 0 y stock >= 0.");
            return false;
        }
        DataStorage.productos.add(new ProductoFisico(nombre, precio, categoria, stock));
        System.out.println("Producto registrado con éxito.");
        return true;
    }

    @Override
    public void listarProductosDisponibles() {
        boolean hayDisponibles = false;
        System.out.println("--- PRODUCTOS DISPONIBLES ---");
        for (int i = 0; i < DataStorage.productos.size(); i++) {
            Producto p = DataStorage.productos.get(i);
            if (p.isDisponible()) {
                System.out.println((i + 1) + ". " + p.toString());
                hayDisponibles = true;
            }
        }
        if (!hayDisponibles) System.out.println("No hay productos disponibles.");
    }

    @Override
    public Producto obtenerProductoPorIndice(int indice) {
        if (indice >= 0 && indice < DataStorage.productos.size()) {
            return DataStorage.productos.get(indice);
        }
        return null;
    }
}