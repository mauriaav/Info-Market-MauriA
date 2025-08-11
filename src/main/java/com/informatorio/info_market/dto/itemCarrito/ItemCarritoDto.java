package com.informatorio.info_market.dto.itemCarrito;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ItemCarritoDto {
    private String nombreProducto;
    private int cantidadProducto;
    private double precioUnitario;
    private double precioTotal;
}
