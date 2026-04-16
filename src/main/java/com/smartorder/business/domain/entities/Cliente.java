package com.smartorder.business.domain.entities;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public abstract class Cliente {
    protected String nombre;
    protected String telefono;
    protected String direccion;

    public abstract String getDetalleCliente();
}