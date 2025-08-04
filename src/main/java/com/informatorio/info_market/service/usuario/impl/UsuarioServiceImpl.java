package com.informatorio.info_market.service.usuario.impl;

import com.informatorio.info_market.domain.Usuario;
import com.informatorio.info_market.dto.itemCarrito.ItemCarritoDto;
import com.informatorio.info_market.exception.notfound.NotFoundException;
import com.informatorio.info_market.repository.usuario.UsuarioRepository;
import com.informatorio.info_market.service.carrito.CarritoService;
import com.informatorio.info_market.service.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private CarritoService carritoService;
    private UsuarioRepository usuarioRepository;


    @Override
    public Usuario getUsuarioEntityById(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return  usuario.get() ;
        }else{
            throw new NotFoundException("No se encontro el usuario con id : " + id);
        }
    }

    @Override
    public Map<String, Object> cerrarCarritoPorUsuario(UUID idUsuario) {

        return carritoService.cerrarCarritoUsuario(getUsuarioEntityById(idUsuario));

    }
}
