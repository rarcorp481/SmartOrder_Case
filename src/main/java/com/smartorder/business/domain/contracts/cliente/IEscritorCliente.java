package com.smartorder.business.domain.contracts.cliente;

public interface IEscritorCliente {
    boolean registrarCliente(String nombre, String telefono, String direccion);
}