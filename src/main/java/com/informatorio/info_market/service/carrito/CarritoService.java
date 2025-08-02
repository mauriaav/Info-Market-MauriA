package com.informatorio.info_market.service.carrito;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.domain.Usuario;
import com.informatorio.info_market.dto.itemCarrito.ItemCarritoDto;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface CarritoService {

    @Transactional()
    void agregarProducto(Usuario usuario, UUID idProducto);

    Optional<Carrito> getCarritoConEstado(EstadoCarritoEnum estadoCarritoEnum, List<Carrito> carritos);

    Carrito getCarritoEntityById(UUID idCarrito);

    Map<String, Object> cerrarCarrito(Carrito carrito);

    Map<String, Object> cerrarCarritoUsuario(Usuario usuario);

}
