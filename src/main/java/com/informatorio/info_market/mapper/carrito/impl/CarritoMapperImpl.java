package com.informatorio.info_market.mapper.carrito.impl;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.dto.carrito.CarritoDto;
import com.informatorio.info_market.mapper.carrito.CarritoMapper;
import com.informatorio.info_market.mapper.itemCarrito.ItemCarritoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarritoMapperImpl implements CarritoMapper {

    private ItemCarritoMapper itemCarritoMapper;

    @Override
    public CarritoDto carritoToCarritoDto(Carrito carrito) {
        CarritoDto carritoDto = new CarritoDto();
        carritoDto.setId(carrito.getId());
        carritoDto.setId_usuario(carrito.getUsuario().getId());
        carritoDto.setEstadoCarritoEnum(carrito.getEstadoCarrito());
        carritoDto.setFechaDeCreacion(carrito.getFechaDeCreacion());
        carritoDto.setFechaActualizacion(carrito.getFechaActualizacion());
        carritoDto.setItemsCarrito(
                carrito.getItemsCarritos().stream()
                        .map(itemCarrito -> itemCarritoMapper.itemCarritoToItemCarritoDto(itemCarrito))
                        .toList()
        );
        return carritoDto;
    }
}
