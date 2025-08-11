package com.informatorio.info_market.mapper.carrito;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.dto.carrito.CarritoDto;

public interface CarritoMapper {
    CarritoDto carritoToCarritoDto(Carrito carrito);
}
