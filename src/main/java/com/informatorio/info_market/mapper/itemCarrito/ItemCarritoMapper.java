package com.informatorio.info_market.mapper.itemCarrito;

import com.informatorio.info_market.domain.ItemCarrito;
import com.informatorio.info_market.dto.itemCarrito.ItemCarritoDto;

public interface ItemCarritoMapper {
    ItemCarritoDto itemCarritoToItemCarritoDto(ItemCarrito itemCarrito);
}
