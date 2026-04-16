package com.smartorder.business.application;

import com.smartorder.business.domain.contracts.ventas.IGestorVentas;
import com.smartorder.business.domain.entities.Pedido;
import com.smartorder.business.infrastructure.persistence.DataStorage;

public class ServiceVentas implements IGestorVentas {

    @Override
    public void registrarPedidoFinalizado(Pedido pedido) {
        if (pedido != null && pedido.isPagado() && !pedido.getProductos().isEmpty()) {
            DataStorage.pedidos.add(pedido);
            System.out.println("Pedido #" + pedido.getIdPedido() + " guardado exitosamente.");
        } else {
            System.out.println("Error: Pedido inválido o sin pagar.");
        }
    }

    @Override
    public void generarReporteGeneral() {
        System.out.println("\n=== REPORTE DE VENTAS ===");
        if (DataStorage.pedidos.isEmpty()) {
            System.out.println("Aún no hay ventas registradas.");
            return;
        }

        int totalPedidos = DataStorage.pedidos.size();
        double gananciasTotales = 0.0;

        for (Pedido p : DataStorage.pedidos) {
            gananciasTotales += p.getTotal();
        }

        System.out.println("Total de pedidos finalizados: " + totalPedidos);
        System.out.printf("Ingresos totales: $%.2f\n", gananciasTotales);
        System.out.println("=========================");
    }
}