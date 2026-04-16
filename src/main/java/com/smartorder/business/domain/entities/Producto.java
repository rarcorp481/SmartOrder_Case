package com.smartorder.business.domain.entities;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public abstract class Producto {
    protected String nombre;
    protected double precio;
    protected boolean disponible;

    public abstract void actualizarDisponibilidad();
}