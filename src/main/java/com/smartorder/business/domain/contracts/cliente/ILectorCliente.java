package com.smartorder.business.domain.contracts.cliente;
import com.smartorder.business.domain.entities.Cliente;

public interface ILectorCliente {
    void consultarClientes();
    Cliente obtenerClientePorIndice(int indice);
}