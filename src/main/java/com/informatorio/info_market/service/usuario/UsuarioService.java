package com.informatorio.info_market.service.usuario;

import com.informatorio.info_market.domain.Usuario;
import com.informatorio.info_market.dto.itemCarrito.ItemCarritoDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UsuarioService {
    Usuario getUsuarioEntityById(UUID id);
    Map<String, Object> cerrarCarritoPorUsuario(UUID idUsuario);
}
