package com.smartorder.business;

import com.smartorder.business.application.*;
import com.smartorder.business.domain.contracts.cliente.IGestorCliente;
import com.smartorder.business.domain.contracts.producto.IGestorProducto;
import com.smartorder.business.domain.contracts.ventas.IGestorVentas;
import com.smartorder.business.domain.contracts.IPago;
import com.smartorder.business.domain.entities.*;
import com.smartorder.business.infrastructure.payments.*;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    // Instanciación usando las Interfaces (Polimorfismo / Abstracción total)
    private static final IGestorCliente serviceCliente = new ServiceCliente();
    private static final IGestorProducto serviceProducto = new ServiceProducto();
    private static final IGestorVentas serviceVentas = new ServiceVentas();

    public static void main(String[] args) {
        // Datos semilla iniciales
        serviceProducto.registrarProducto("Hamburguesa", 8.50, "Comida", 10);
        serviceProducto.registrarProducto("Refresco", 2.00, "Bebida", 5);
        serviceCliente.registrarCliente("Juan Perez", "555-1234", "Calle 1");

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- SMARTORDER: MENU PRINCIPAL ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Ver Clientes");
            System.out.println("3. Registrar Producto Físico");
            System.out.println("4. Crear Nuevo Pedido");
            System.out.println("5. Ver Reportes de Ventas");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1 -> menuRegistrarCliente();
                case 2 -> serviceCliente.consultarClientes();
                case 3 -> menuRegistrarProducto();
                case 4 -> flujoCrearPedido();
                case 5 -> serviceVentas.generarReporteGeneral();
                case 6 -> salir = true;
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void menuRegistrarCliente() {
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono: ");
        String tel = scanner.nextLine();
        System.out.print("Dirección: ");
        String dir = scanner.nextLine();
        serviceCliente.registrarCliente(nombre, tel, dir);
    }

    private static void menuRegistrarProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = leerDouble();
        System.out.print("Categoría: ");
        String cat = scanner.nextLine();
        System.out.print("Cantidad en Stock: ");
        int stock = leerEntero();
        serviceProducto.registrarProducto(nombre, precio, cat, stock);
    }

    private static void flujoCrearPedido() {
        System.out.println("\n-- Seleccione un Cliente --");
        serviceCliente.consultarClientes();
        System.out.print("Índice de cliente (ej. 1): ");
        int indiceCli = leerEntero() - 1;

        Cliente cliente = serviceCliente.obtenerClientePorIndice(indiceCli);
        if (cliente == null) {
            System.out.println("Cliente inválido. Abortando pedido.");
            return;
        }

        Pedido nuevoPedido = new Pedido(cliente);
        boolean armandoPedido = true;

        while (armandoPedido) {
            serviceProducto.listarProductosDisponibles();
            System.out.print("Seleccione producto a agregar (0 para terminar): ");
            int opcionProd = leerEntero();

            if (opcionProd == 0) {
                armandoPedido = false;
            } else {
                Producto prod = serviceProducto.obtenerProductoPorIndice(opcionProd - 1);
                if (nuevoPedido.agregarProducto(prod)) {
                    if (prod instanceof ProductoFisico pf) pf.reducirStock();
                    System.out.println("Agregado. Total parcial: $" + nuevoPedido.getTotal());
                } else {
                    System.out.println("Error: Producto inválido o sin stock suficiente.");
                }
            }
        }

        if (nuevoPedido.getProductos().isEmpty()) {
            System.out.println("Pedido vacío cancelado.");
            return;
        }

        System.out.println("\n-- Pago --");
        System.out.println("Total a pagar: $" + nuevoPedido.getTotal());
        System.out.println("1. Efectivo | 2. Tarjeta");
        int opPago = leerEntero();

        IPago metodoPago = (opPago == 1) ? new PagoEfectivo() : new PagoTarjeta();

        if (nuevoPedido.procesarPago(metodoPago)) {
            serviceVentas.registrarPedidoFinalizado(nuevoPedido);
            System.out.println("Ticket: Pagado con " + metodoPago.obtenerMetodo());
        } else {
            System.out.println("El pago fue rechazado.");
        }
    }

    // Utilidades para evitar que el Scanner cierre el programa por errores de tipeo
    private static int leerEntero() {
        try { return Integer.parseInt(scanner.nextLine()); }
        catch (NumberFormatException e) { return -1; }
    }

    private static double leerDouble() {
        try { return Double.parseDouble(scanner.nextLine()); }
        catch (NumberFormatException e) { return -1.0; }
    }
}