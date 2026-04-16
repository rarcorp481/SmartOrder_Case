package com.smartorder.business.infrastructure.persistence;
import com.smartorder.business.domain.entities.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    public static List<Cliente> clientes = new ArrayList<>();
    public static List<Producto> productos = new ArrayList<>();
    public static List<Pedido> pedidos = new ArrayList<>();
}