package com.informatorio.info_market.service.item;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.domain.ItemCarrito;
import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.dto.itemCarrito.ItemCarritoDto;

import java.util.List;

public interface ItemService {
    ItemCarrito crearItemCarrito(Carrito carrito, Producto producto, int cantidad);
    List<ItemCarritoDto> listarItemsCarrito(List<ItemCarrito> productos);
    Double obtenerValorTotal(List<ItemCarritoDto> productos);
}
