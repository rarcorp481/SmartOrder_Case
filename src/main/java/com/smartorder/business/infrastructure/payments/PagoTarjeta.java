package com.smartorder.business.infrastructure.payments;

import com.smartorder.business.domain.contracts.IPago;

public class PagoTarjeta implements IPago {

    @Override
    public boolean procesarPago(double monto) {
        if (monto <= 0) {
            System.out.println("Error: El monto a cobrar debe ser mayor a cero.");
            return false;
        }
        System.out.println("Conectando con terminal bancaria... Cobro aprobado por: $" + monto);
        return true;
    }

    @Override
    public String obtenerMetodo() {
        return "Tarjeta de Crédito/Débito";
    }
}