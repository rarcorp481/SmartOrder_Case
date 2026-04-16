package com.smartorder.business.application;

import com.smartorder.business.domain.contracts.cliente.IGestorCliente;
import com.smartorder.business.domain.entities.Cliente;
import com.smartorder.business.domain.entities.ClienteRegular;
import com.smartorder.business.infrastructure.persistence.DataStorage;

public class ServiceCliente implements IGestorCliente {

    @Override
    public boolean registrarCliente(String nombre, String telefono, String direccion) {
        if (nombre == null || nombre.isBlank() || telefono == null || telefono.isBlank()) {
            System.out.println("Error: Nombre y teléfono son obligatorios.");
            return false;
        }
        DataStorage.clientes.add(new ClienteRegular(nombre, telefono, direccion));
        System.out.println("Cliente registrado con éxito.");
        return true;
    }

    @Override
    public void consultarClientes() {
        if (DataStorage.clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        System.out.println("--- LISTA DE CLIENTES ---");
        for (int i = 0; i < DataStorage.clientes.size(); i++) {
            System.out.println((i + 1) + ". " + DataStorage.clientes.get(i).getDetalleCliente());
        }
    }

    @Override
    public Cliente obtenerClientePorIndice(int indice) {
        if (indice >= 0 && indice < DataStorage.clientes.size()) {
            return DataStorage.clientes.get(indice);
        }
        return null;
    }
}