package com.smartorder.business.infrastructure.payments;
import com.smartorder.business.domain.contracts.IPago;

public class PagoEfectivo implements IPago {
    @Override public boolean procesarPago(double monto) { return true; }
    @Override public String obtenerMetodo() { return "Efectivo"; }
}

