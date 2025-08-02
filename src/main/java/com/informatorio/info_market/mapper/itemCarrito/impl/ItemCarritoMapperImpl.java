package com.informatorio.info_market.mapper.itemCarrito.impl;

import com.informatorio.info_market.domain.ItemCarrito;
import com.informatorio.info_market.dto.itemCarrito.ItemCarritoDto;
import com.informatorio.info_market.mapper.itemCarrito.ItemCarritoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ItemCarritoMapperImpl implements ItemCarritoMapper {

    @Override
    public ItemCarritoDto itemCarritoToItemCarritoDto(ItemCarrito itemCarrito) {
        ItemCarritoDto item = new ItemCarritoDto();
        item.setNombreProduco(itemCarrito.getProducto().getNombre());
        item.setCantidadProducto(itemCarrito.getCantidad());
        item.setPrecioTotal(itemCarrito.getProducto().getPrecio() * itemCarrito.getCantidad());
        return item;
    }
}
