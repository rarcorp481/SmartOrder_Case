package com.smartorder.business.domain.entities;

public class ClienteRegular extends Cliente {

    public ClienteRegular(String n, String t, String d) { super(n, t, d); }

    @Override
    public String getDetalleCliente() { return nombre + " | Tel: " + telefono; }
}