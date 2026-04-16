package com.smartorder.business.domain.entities;
import com.smartorder.business.domain.contracts.IPago;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Pedido {
    private static int contadorIds = 1;
    private int idPedido; private Cliente cliente;
    private List<Producto> productos; private double total;
    private IPago metodoPago; private boolean pagado;

    public Pedido(Cliente cliente) {
        this.idPedido = contadorIds++; this.cliente = cliente;
        this.productos = new ArrayList<>(); this.total = 0.0; this.pagado = false;
    }
    public boolean agregarProducto(Producto producto) {
        if (producto != null && producto.isDisponible()) {
            this.productos.add(producto); this.total += producto.getPrecio(); return true;
        }
        return false;
    }
    public boolean procesarPago(IPago pago) {
        if (pago.procesarPago(this.total)) { this.metodoPago = pago; this.pagado = true; return true; }
        return false;
    }
}