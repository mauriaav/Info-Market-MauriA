package com.informatorio.info_market.dto.carrito;

import com.informatorio.info_market.dto.itemCarrito.ItemCarritoDto;
import com.informatorio.info_market.dto.producto.ProductoDto;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarritoDto {
    private UUID id;
    private UUID id_usuario;
    private EstadoCarritoEnum estadoCarritoEnum;
    private LocalDate fechaDeCreacion;
    private LocalDate fechaActualizacion;
    private List<ItemCarritoDto> itemsCarrito;
}
