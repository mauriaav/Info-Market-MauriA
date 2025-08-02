package com.informatorio.info_market.dto.itemCarrito;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ItemCarritoDto {
    private String nombreProducto;
    private int cantidadProducto;
    private double precioTotal;
}
