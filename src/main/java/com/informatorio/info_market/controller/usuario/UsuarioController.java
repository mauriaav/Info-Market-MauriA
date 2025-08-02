package com.informatorio.info_market.controller.usuario;

import com.informatorio.info_market.dto.itemCarrito.ItemCarritoDto;
import com.informatorio.info_market.service.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController //Anotacion a nivel de clase
@RequestMapping("/api/v1/usuarios")
@AllArgsConstructor
public class UsuarioController {
    private UsuarioService usuarioService;

    @PutMapping("/{idUsuario}/cerrar-carrito")
    public ResponseEntity<Map<String, Object>> cerrarCarrito(@PathVariable UUID idUsuario){
        Map<String, Object> itemsCarritoDto = usuarioService.cerrarCarritoPorUsuario(idUsuario);
        return  ResponseEntity.ok().
                contentType(MediaType.APPLICATION_JSON)
                .body(itemsCarritoDto);
    }
}
