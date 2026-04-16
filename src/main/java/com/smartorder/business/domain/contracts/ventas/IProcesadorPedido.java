package com.smartorder.business.domain.contracts.ventas;
import com.smartorder.business.domain.entities.Pedido;

public interface IProcesadorPedido {
    void registrarPedidoFinalizado(Pedido pedido);
}