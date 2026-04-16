package com.smartorder.business.domain.contracts;
public interface IPago {
    boolean procesarPago(double monto);
    String obtenerMetodo();
}