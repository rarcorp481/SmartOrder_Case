package com.smartorder.business.domain.contracts.producto;
import com.smartorder.business.domain.entities.Producto;

public interface ILectorProducto {
    void listarProductosDisponibles();
    Producto obtenerProductoPorIndice(int indice);
}