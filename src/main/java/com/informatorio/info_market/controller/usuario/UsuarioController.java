package com.informatorio.info_market.controller.usuario;

import com.informatorio.info_market.service.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController //Anotacion a nivel de clase
@RequestMapping("/api/v1/usuarios")
@AllArgsConstructor
public class UsuarioController {
    private UsuarioService usuarioService;

    @PutMapping("/{idUsuario}/cerrar-carrito")
    public ResponseEntity cerrarCarrito(@PathVariable UUID idUsuario){
        usuarioService.cerrarCarritoPorUsuario(idUsuario);
        return  ResponseEntity.ok().build();
    }
}
